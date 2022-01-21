package com.example.btuclassroom.Tabs

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.btuclassroom.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FirstProfile: Fragment(R.layout.profile_first) {
    private lateinit var image: ImageView
    private lateinit var photoLink: EditText
    private lateinit var name: EditText
    private lateinit var id: EditText
    private lateinit var mail: EditText
    private lateinit var saveInfo: Button
    private lateinit var name2: TextView
    private lateinit var id2: TextView
    private lateinit var mail2: TextView

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference("UserInfo")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        init()
        profile()
        saveInfo()

        db.child(auth.currentUser?.uid!!).addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userInfo = snapshot.getValue(com.example.btuclassroom.UserInfo::class.java) ?: return
                Glide.with(this@FirstProfile).load(userInfo.photo).placeholder(R.drawable.ic_baseline_person_24).into(image)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun init(){
        photoLink = requireView().findViewById(R.id.photoLink)
        name = requireView().findViewById(R.id.name)
        id = requireView().findViewById(R.id.idNum)
        mail = requireView().findViewById(R.id.email)
        saveInfo = requireView().findViewById(R.id.buttonSave)
        image = requireView().findViewById(R.id.imageView)
        name2 = requireView().findViewById(R.id.name2)
        id2 = requireView().findViewById(R.id.idNum2)
        mail2 = requireView().findViewById(R.id.email2)


        saveInfo.setOnClickListener{
            val name = name.text.toString()

        }
    }

    private fun profile(){

        val idNumber = id.text.toString()
        val email = mail.text.toString()

        if (!email.contains("@btu.edu.ge")) {
            mail.error = "შეიყვანეთ მეილი სწორად"
        }
        if(idNumber.isEmpty() || (idNumber.length != 12 ) || (!idNumber.contains(Regex("[0-9]")))){
            id.error = "პირადი ნომერი არ რეგისტრირდება"
        }

    }
    private fun saveInfo(){

        val sharedPreferances = requireActivity()
            .getSharedPreferences("pref", Context.MODE_PRIVATE)

        saveInfo.setOnClickListener(){
            val photoLink = photoLink.text.toString()
            val name = name.text.toString()
            val id = id.text.toString()
            val mail = mail.text.toString()
            val name22 =  name2.text.toString()
            val id22 = id2.text.toString()
            val mail22 = mail2.text.toString()

            val result1 = name + name22
            name2.text =result1

            val result2 = id +id22
            id2.text = result2

            val result3 = mail + mail22
            mail2.text = result3

            sharedPreferances.edit().putString("note", result1,).apply()
            sharedPreferances.edit().putString("note", result2,).apply()
            sharedPreferances.edit().putString("note", result3,).apply()



            val userInfo = com.example.btuclassroom.UserInfo(name, photoLink, id, mail,)
            db.child(auth.currentUser?.uid!!).setValue(userInfo)
        }


    }

}