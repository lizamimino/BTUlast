package com.example.btuclassroom.fragments

import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.btuclassroom.R


class CalendarFragment: Fragment(R.layout.fragment_calendar) {

    private lateinit var calendarView: CalendarView
    private lateinit var dateView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        calendarView.setOnDateChangeListener(CalendarView.OnDateChangeListener { _, year, month, dayOfMonth ->
            val date = dayOfMonth.toString() + "-" + (month + 1) + "-" + year
            dateView.text = date
        })
    }

    private fun init(){
        calendarView = requireView().findViewById(R.id.calendarView)
        dateView = requireView().findViewById(R.id.dateView)
    }

}