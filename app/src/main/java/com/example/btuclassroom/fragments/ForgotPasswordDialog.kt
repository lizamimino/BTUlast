package com.example.btuclassroom.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.btuclassroom.R
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordDialog(context: Context): Dialog(context) {

    private lateinit var editEmailAddress: EditText
    private lateinit var buttonSend: Button
    init {
        setCanceledOnTouchOutside(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_forgot_password)

        init()

        buttonSend.setOnClickListener {

            val email = editEmailAddress.text.toString()

            FirebaseAuth.getInstance()
                .sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        dismiss()
                    }
                }
            }
        }

    private fun init(){
        editEmailAddress = findViewById(R.id.editEmailAddress)
        buttonSend = findViewById(R.id.buttonSend)
    }

}