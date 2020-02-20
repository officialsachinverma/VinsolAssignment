package com.project100pi.vinsolassignment.network

import android.accounts.NetworkErrorException
import android.content.Context
import com.google.gson.Gson
import com.project100pi.vinsolassignment.model.MeetingSchedule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by Sachin Verma on 2019-12-08.
 */

class NetworkManager(val context: Context, val onNetworkTask: OnNetworkTask, val showDialog: Boolean) {

    private val API = "http://fathomless-shelf-5846.herokuapp.com/api/schedule?date="


    /**
     * Allows to cancel all assigned jobs for this ViewModel
     */
    private val coroutineJob = Job()
    /**
     * we are pass [viewModelJob], any coroutine started in this coroutineScope can be cancelled
     * by calling viewModelJob.cancel()
     */
    private val coroutineScope = CoroutineScope(Dispatchers.IO + coroutineJob)

    init {
        getScheduledMeetings()
    }

    private fun getScheduledMeetings() {
        try {
            val url = URL(API)
            val urlConnection = url.openConnection() as HttpURLConnection
            try {
                val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                var stringBuilder = StringBuilder()
                var line = ""
                while ((line == bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n")
                }
                bufferedReader.close();
                val _gson = Gson()
                val _resPojo = _gson.fromJson(stringBuilder.toString(), MeetingSchedule::class.java)
//                if (_resPojo.getSuccess()) {
//                    _reponse = "" + _resPojo.getSuccess();
//                    List<Result> _resultArrayList = _resPojo . getResult ();
//                    for (Result _result: _resultArrayList){
//                        _database.saveCurrencyData(_result);
//                    }
//                } else {
//                    _reponse = null;
//                }
            } finally {
                urlConnection.disconnect()
            }
        } catch (e: NetworkErrorException) {
            e.printStackTrace()
        }
    }

}