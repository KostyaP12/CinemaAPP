package com.example.cinemaapp.model

import android.app.IntentService
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import javax.net.ssl.HttpsURLConnection

private const val REQUEST_GET = "GET"
private const val REQUEST_TIMEOUT = 10000

class MovieService : IntentService("MovieService") {
    private val broadcastIntent = Intent(DETAILS_INTENT_FILTER)
    override fun onHandleIntent(intent: Intent?) {
        intent?.let { loadMovie() }
    }

    private fun loadMovie() {
        try {
            val uri = URL("$API_KEY $API_KEY $API_LANGUAGE $API_PAGE")
            val urlConnection: HttpsURLConnection
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

    fun getLines(reader: BufferedReader): String {
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
        return rawData.toString();
    }
}
