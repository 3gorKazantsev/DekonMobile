package org.egorkazantsev.dekonmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataTextView: TextView = findViewById(R.id.data_TextView)
        val receiveButton: Button = findViewById(R.id.receive_Button)
        val dataEditText: EditText = findViewById(R.id.data_EditText)
        val sendButton: Button = findViewById(R.id.send_Button)

        receiveButton.setOnClickListener {

        }

        sendButton.setOnClickListener {

        }
    }
}