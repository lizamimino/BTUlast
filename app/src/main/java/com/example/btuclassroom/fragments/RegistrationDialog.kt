package com.example.btuclassroom.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.btuclassroom.R
import com.google.firebase.auth.FirebaseAuth

class RegistrationDialog(context: Context): Dialog(context) {

    private lateinit var textView: TextView
    private lateinit var editName: EditText
    private lateinit var editId: EditText
    private lateinit var editPassword: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var editEmail: EditText
    private lateinit var buttonRegister: Button
    init {
        setCanceledOnTouchOutside(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_registration)

        init()

        buttonRegister.setOnClickListener {

            val email = editEmail.text.toString()
            val id = editId.text.toString()
            val password = editPassword.text.toString()
            val confirmPass = confirmPassword.text.toString()

            if (!email.contains("@btu.edu.ge")) {
                editEmail.error = "შეიყვანეთ მეილი სწორად"
            }
            if(id.isEmpty() || (id.length != 12 ) || (!id.contains(Regex("[0-9]")))){
                editId.error = "პირადი ნომერი არ რეგისტრირდება"
            }
            if ((password.length < 9) || !password.contains(Regex("[0-9]")) || !password.matches(".*[!@#$%^&*()_+].*".toRegex())) {
                editPassword.error = "შეიყვანეთ შესაბამისი პაროლი"
            }
            if (confirmPass.isEmpty() || (confirmPass != password)) {
                confirmPassword.error = "არ ემთხვევა პაროლები"
            }

            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        dismiss()
                    }

                }
        }
    }
    private fun init(){
        textView = findViewById(R.id.textView)
        editName = findViewById(R.id.editName)
        editId = findViewById(R.id.editId)
        editPassword = findViewById(R.id.editPassword)
        confirmPassword = findViewById(R.id.confirmPassword)
        editEmail = findViewById(R.id.editEmail)
        buttonRegister = findViewById(R.id.buttonRegister)
    }
}