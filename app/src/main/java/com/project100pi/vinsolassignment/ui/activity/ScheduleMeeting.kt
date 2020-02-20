package com.project100pi.vinsolassignment.ui.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.project100pi.vinsolassignment.R
import com.project100pi.vinsolassignment.utils.Util
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Sachin Verma on 2019-12-08.
 */

class ScheduleMeeting : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    lateinit var btnSaveMeeting: Button
    lateinit var tvBack: TextView
    lateinit var tvMeetingDate: TextView
    lateinit var tvMeetingStartTime: TextView
    lateinit var tvMeetingEndTime: TextView
    lateinit var tvDescription: EditText

    val calendar: Calendar = Calendar.getInstance()
    var startTime = true
    var startHour = 0
    var endHour = 0

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ScheduleMeeting::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schdule_meeting)

        init()
    }

    private fun init() {
        btnSaveMeeting = findViewById(R.id.btn_save_meeting)
        tvBack = findViewById(R.id.tv_back)
        tvMeetingDate = findViewById(R.id.tv_meeting_date)
        tvMeetingStartTime = findViewById(R.id.tv_start_time)
        tvMeetingEndTime = findViewById(R.id.tv_end_time)
        tvDescription = findViewById(R.id.ed_description)

        btnSaveMeeting.setOnClickListener(this)
        tvMeetingDate.setOnClickListener(this)
        tvMeetingStartTime.setOnClickListener(this)
        tvMeetingEndTime.setOnClickListener(this)
        tvDescription.setOnClickListener(this)
        tvBack.setOnClickListener(this)

        tvMeetingDate.text = Util.getCurrentDate()
        tvMeetingStartTime.text = Util.getCurrentTime()
        tvMeetingEndTime.text = Util.getExtraHourTime()

        startHour = calendar.get(Calendar.HOUR_OF_DAY)
        endHour = calendar.get(Calendar.HOUR_OF_DAY) + 1
    }

    override fun onClick(v: View?) {

        when (v?.id){
            R.id.tv_back -> {
                finish()
            }
            R.id.btn_schedule_meeting -> {

            }
            R.id.tv_meeting_date -> {
                DatePickerDialog(this, this,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
            }
            R.id.tv_start_time -> {
                startTime = true
                TimePickerDialog(this, this,
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true).show()
            }
            R.id.tv_end_time -> {
                startTime = false
                TimePickerDialog(this, this,
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true).show()
            }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        if (calendar.get(Calendar.YEAR) > year ||
            calendar.get(Calendar.MONTH) > month ||
            calendar.get(Calendar.DAY_OF_MONTH) > dayOfMonth) {
            Toast.makeText(this@ScheduleMeeting, "Cannot schedule meeting in past date.", Toast.LENGTH_SHORT).show()
        } else
            tvMeetingDate.text = "$dayOfMonth/$month/$year"
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        if (startTime) {
            startHour = hourOfDay
            tvMeetingStartTime.text = "$hourOfDay:$minute"
        } else {
            endHour = hourOfDay
            if (startHour <= endHour)
                tvMeetingEndTime.text = "$hourOfDay:$minute"
            else
                Toast.makeText(this@ScheduleMeeting, "Cannot end a meeting before starting it.", Toast.LENGTH_SHORT).show()
        }
    }


}