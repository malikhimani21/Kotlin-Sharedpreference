package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputName = findViewById<EditText>(R.id.editName)
        val inputEmail = findViewById<EditText>(R.id.editEmail)
        val inputContact = findViewById<EditText>(R.id.editContact)

        val outputName = findViewById<TextView>(R.id.textViewShowName)
        val outputEmail = findViewById<TextView>(R.id.textViewShowEmail)
        val outputContact = findViewById<TextView>(R.id.textViewShowContact)

        val btnSave = findViewById<Button>(R.id.save)
        val btnView = findViewById<Button>(R.id.view)


        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)


        btnSave.setOnClickListener(View.OnClickListener {

            val name: String = inputName.text.toString()
            val email: String = inputEmail.text.toString()
            val contact: String = inputContact.text.toString()

            val editor: SharedPreferences.Editor = sharedPreferences.edit()

            editor.putString("name_key", name)
            editor.putString("email_key", email)
            editor.putString("contact_key", contact)

            editor.apply()
            editor.commit()
        })


        btnView.setOnClickListener {

            val sharedNameValue = sharedPreferences.getString("name_key", "defaultname")
            val sharedEmailValue = sharedPreferences.getString("email_key", "defaultEmail")
            val sharedContactValue = sharedPreferences.getString("contact_key", "defaultContact")

            if (sharedNameValue.equals("defaultname")) {
                outputName.setText("default name: ${sharedNameValue}").toString()
            } else {
                outputName.setText(sharedNameValue).toString()
                outputEmail.setText(sharedEmailValue).toString()
                outputContact.setText(sharedContactValue).toString()
            }
        }

    }
}