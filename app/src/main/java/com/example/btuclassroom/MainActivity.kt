package com.example.btuclassroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.ActionBar
import com.example.btuclassroom.fragments.ForgotPasswordDialog
import com.example.btuclassroom.fragments.RegistrationDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var resetPass: TextView
    private lateinit var buttonLogin: Button
    private lateinit var buttonRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        init()
        login()
        register()

        resetPass.setOnClickListener {
            val dialog = ForgotPasswordDialog(this)
            dialog.show()
        }

    }

    private fun init(){
        imageView = findViewById(R.id.imageView)
        editEmail = findViewById(R.id.editEmail)
        editPassword = findViewById(R.id.editPassword)
        resetPass = findViewById(R.id.resetPass)
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonRegister = findViewById(R.id.buttonRegister)
    }

    private fun login(){
        buttonLogin.setOnClickListener{

            val email = editEmail.text.toString()
            val password = editPassword.text.toString()

            if (!email.contains("@btu.edu.ge")) {
                editEmail.error = "შეიყვანეთ მეილი სწორად!"
            }

            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful){
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }
                }
        }
    }
    private fun register(){
        buttonRegister.setOnClickListener {
            val dialog = RegistrationDialog(this)
            dialog.show()
        }
    }


}