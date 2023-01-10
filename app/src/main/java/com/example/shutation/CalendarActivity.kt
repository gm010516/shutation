package com.example.shutation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import com.example.shutation.databinding.ActivityCalendarBinding
import com.google.firebase.auth.FirebaseAuth
import java.io.FileInputStream
import java.io.FileOutputStream
import java.time.Month
import java.time.MonthDay
import java.time.Year

class CalendarActivity : AppCompatActivity() {
    val binding by lazy{ ActivityCalendarBinding.inflate(layoutInflater) }
    var userID=FirebaseAuth.getInstance().currentUser.toString()
    lateinit var fname: String
    lateinit var str: String
    lateinit var calendarView: CalendarView
    lateinit var updateBtn: Button
    lateinit var deleteBtn: Button
    lateinit var saveBtn: Button
    lateinit var CalendarTextView: TextView
    lateinit var CalendarContent: TextView
    lateinit var title: TextView
    lateinit var contextEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        calendarView=findViewById(R.id.calendarView)
        CalendarTextView=findViewById(R.id.CalendarTextView)
        saveBtn=findViewById(R.id.saveBtn)
        deleteBtn=findViewById(R.id.deleteBtn)
        updateBtn=findViewById(R.id.updateBtn)
        CalendarContent=findViewById(R.id.CalendarContent)
        title=findViewById(R.id.title)
        contextEditText=findViewById(R.id.contextEditText)

        title.text = "일정관리"

        calendarView.setOnDateChangeListener {view, year, month, dayOfMonth ->
            CalendarTextView.visibility = View.VISIBLE
            saveBtn.visibility = View.VISIBLE
            contextEditText.visibility = View.VISIBLE
            CalendarContent.visibility = View.INVISIBLE
            updateBtn.visibility = View.INVISIBLE
            deleteBtn.visibility = View.INVISIBLE
            CalendarTextView.text = String.format("%d / %d / %d ", year, month +1 ,dayOfMonth)
            contextEditText.setText("")
            checkDay(year, month, dayOfMonth, userID)
        }

        saveBtn.setOnClickListener {
            saveCalendar(fname)
            contextEditText.visibility = View.INVISIBLE
            saveBtn.visibility = View.INVISIBLE
            updateBtn.visibility = View.VISIBLE
            deleteBtn.visibility = View.VISIBLE
            str = contextEditText.text.toString()
            CalendarContent.text = str
            CalendarContent.visibility = View.VISIBLE
        }
    }

    // 달력 내용 조회, 수정
    fun checkDay(cYear: Int, cMonth: Int, cDay: Int, userID: String) {
        // 저장할 파일 이름설정
        fname = "" + userID + cYear + "-" + (cMonth + 1) + "" + "-" + cDay + ".txt"

        var fileInputStream: FileInputStream
        try {
            fileInputStream = openFileInput(fname)
            val fileDate = ByteArray(fileInputStream.available())
            fileInputStream.read(fileDate)
            fileInputStream.close()
            str = String(fileDate)
            contextEditText.visibility = View.INVISIBLE
            CalendarContent.visibility = View.VISIBLE
            CalendarContent.text = str
            saveBtn.visibility = View.INVISIBLE
            updateBtn.visibility = View.VISIBLE
            deleteBtn.visibility = View.VISIBLE
            updateBtn.setOnClickListener {
                contextEditText.visibility = View.VISIBLE
                CalendarContent.visibility = View.INVISIBLE
                contextEditText.setText(str)
                saveBtn.visibility = View.VISIBLE
                updateBtn.visibility = View.INVISIBLE
                deleteBtn.visibility = View.INVISIBLE
                CalendarContent.text = contextEditText.text
            }
            deleteBtn.setOnClickListener {
                CalendarContent.visibility = View.INVISIBLE
                updateBtn.visibility = View.INVISIBLE
                deleteBtn.visibility = View.INVISIBLE
                contextEditText.setText("")
                contextEditText.visibility = View.VISIBLE
                saveBtn.visibility = View.VISIBLE
                removeCalendar(fname)
            }
            if(CalendarContent.text == null) {
                CalendarContent.visibility = View.INVISIBLE
                updateBtn.visibility = View.INVISIBLE
                deleteBtn.visibility = View.INVISIBLE
                CalendarTextView.visibility = View.VISIBLE
                saveBtn.visibility = View.VISIBLE
                contextEditText.visibility = View.VISIBLE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // 달력 내용 제거
    @SuppressLint("WrongConstant")
    fun removeCalendar(readDay: String?) {
        var fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS)
            val content = ""
            fileOutputStream.write(content.toByteArray())
            fileOutputStream.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    // 달력 내용 추가
    @SuppressLint("WrongConstant")
    fun saveCalendar(readDay: String?) {
        var fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput(readDay, MODE_NO_LOCALIZED_COLLATORS)
            val content = contextEditText.text.toString()
            fileOutputStream.write(content.toByteArray())
            fileOutputStream.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
}
