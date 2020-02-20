package com.project100pi.vinsolassignment.model

/**
 * Created by Sachin Verma on 2019-12-08.
 */

data class MeetingSchedule(var start_time: String,
                           var end_time: String,
                           var description: String,
                           var participants: ArrayList<String>)