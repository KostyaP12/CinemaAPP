package com.example.cinemaapp.model

import android.app.IntentService
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.reflect.Type
import java.net.URL
import java.util.ArrayList
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

private const val REQUEST_GET = "GET"
private const val REQUEST_TIMEOUT = 10000

class MovieService : IntentService("MovieService") {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onHandleIntent(intent: Intent?) {
        if (intent == null) {
            println("INTENT = NULL")
        } else {
            loadMovie()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun loadMovie() {
        try {
            val uri = URL("$API_LINK$API_KEY$API_LANGUAGE$API_PAGE")
            lateinit var urlConnection: HttpsURLConnection
            try {
                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.apply {
                    requestMethod = REQUEST_GET
                    readTimeout = REQUEST_TIMEOUT
                }
                val turns = Gson().fromJson<List<Movie>>(
                    getLines(
                        BufferedReader(
                            InputStreamReader(urlConnection.inputStream)
                        )
                    )
                )
                println(turns[1].title)
            } catch (e: Exception) {
                println(e.message)
            } finally {
                urlConnection.disconnect()
            }
        } catch (e: Exception) {
            e.message
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    inline fun <reified T> Gson.fromJson(json: String) =
        fromJson<List<Movie>>(json, object : TypeToken<List<Movie>>() {}.type)
}
