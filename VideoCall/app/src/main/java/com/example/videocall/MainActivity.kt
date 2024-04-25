package com.example.videocall

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val edt_room = findViewById<EditText>(R.id.edt_room)
        val btn_join = findViewById<Button>(R.id.btn_join)

        btn_join.setOnClickListener(){
            if(edt_room.text.toString().isEmpty()){
                Toast.makeText(this, "Please Enter Call Id code",Toast.LENGTH_SHORT).show()
            }
            else{
                val vInt = Intent(this@MainActivity,VideoCalling::class.java)
                vInt.putExtra("callId",edt_room.text.toString())
                startActivity(vInt)
            }
        }
    }
}