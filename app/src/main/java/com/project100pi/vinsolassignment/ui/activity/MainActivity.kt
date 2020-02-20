package com.project100pi.vinsolassignment.ui.activity

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.project100pi.vinsolassignment.R
import com.project100pi.vinsolassignment.ui.viewmodel.MainViewModel
import com.project100pi.vinsolassignment.utils.Util


class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var rvScheduledMeetingList: RecyclerView

    lateinit var btnScheduleMeeting: Button

    lateinit var tvPrev: TextView

    lateinit var tvNoMeetingsScheduledMsg: TextView

    lateinit var tvNext: TextView

    lateinit var tvDate: TextView

    private val mainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        rvScheduledMeetingList = findViewById(R.id.rv_scheduled_meeting_list)
        btnScheduleMeeting = findViewById(R.id.btn_schedule_meeting)
        tvPrev = findViewById(R.id.tv_prev)
        tvNoMeetingsScheduledMsg = findViewById(R.id.tv_no_meetings_scheduled)
        tvNext = findViewById(R.id.tv_next)
        tvDate = findViewById(R.id.tv_date)

        btnScheduleMeeting.setOnClickListener(this)
        tvPrev.setOnClickListener(this)
        tvNext.setOnClickListener(this)

        tvDate.text = Util.getCurrentDate()
    }

    private fun isWifiConnected(): Boolean {
        var isWifiConnected = false
        try {
            val connManager =
                getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            val mMobile = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            if (mWifi.isConnected || mMobile.isConnected) {
                isWifiConnected = true
            }
        } catch (e: Exception) {
            isWifiConnected = false
        } finally {
            return isWifiConnected
        }
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_schedule_meeting -> {
                ScheduleMeeting.start(this)
            }
            R.id.tv_prev -> {

            }
            R.id.tv_next -> {

            }
        }
    }

}
