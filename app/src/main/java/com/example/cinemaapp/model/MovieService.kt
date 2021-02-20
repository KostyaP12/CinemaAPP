package com.example.cinemaapp.model

import android.app.IntentService
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

private const val REQUEST_GET = "GET"
private const val REQUEST_TIMEOUT = 10000

class MovieService : IntentService("MovieService") {
    private val broadcastIntent = Intent(DETAILS_INTENT_FILTER)
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onHandleIntent(intent: Intent?) {
        loadMovie()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadMovie() {
        try {
            val uri = URL("$API_LINK$API_KEY$API_LANGUAGE$API_PAGE")
            lateinit var urlConnection: HttpsURLConnection
            try {
                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.apply {
                    requestMethod = REQUEST_GET
                    readTimeout = REQUEST_TIMEOUT
                }

                val movie: Movie =
                    Gson().fromJson(
                        getLines(BufferedReader(InputStreamReader(urlConnection.inputStream))),
                        Movie::class.java
                    )
                onResponse(movie)
            }
            catch (e: Exception){
                e.message
            } finally {
                urlConnection.disconnect()
            }
        }catch (e: Exception){
            e.message
        }
    }

    private fun onResponse(movie: Movie) {
        broadcastIntent.apply {
            putExtra(TITLE_EXTRA, movie.original_title)
            putExtra(OVERVIEW, movie.overview)
            putExtra(POSTER_PATH, movie.poster_path)
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    /*private fun getLines(reader: BufferedReader): String {
        val rawData = StringBuilder(1024);
        var tempVariable: String;
        while (true) {
            try {
                tempVariable = reader.readLine();
                if (tempVariable == null) break;
                rawData.append(tempVariable).append("\n");
            } catch (e: IOException) {
                e.printStackTrace();
            }
        }
        try {
            reader.close();
        } catch (e: IOException) {
            e.printStackTrace();
        }
        println(rawData.toString())
        return rawData.toString();
    }*/
}
