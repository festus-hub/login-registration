package com.example.login_registration_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    lateinit var EdtEmail:EditText
    lateinit var Edtpassword:EditText
    lateinit var Edtconfpassword:EditText
    lateinit var Btn_signup:Button
    lateinit var TVDirectlogin:TextView
   private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EdtEmail=findViewById(R.id.etSEmailAddress)
        Edtpassword=findViewById(R.id.etSPassword)
        Edtconfpassword=findViewById(R.id.etSConfPassword)
        Btn_signup=findViewById(R.id.btnSSigned)
        TVDirectlogin=findViewById(R.id.tvRedirectLogin)
        auth=Firebase.auth


        Btn_signup.setOnClickListener {
           signupuser()
        }
        TVDirectlogin.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)

        }
    }
   private fun signupuser() {
       val email = EdtEmail.text.toString()
       val password = Edtpassword.text.toString()
       val confpassword = Edtconfpassword.text.toString()

       if (email.isBlank() or password.isBlank() or confpassword.isBlank()) {
           Toast.makeText(this, "Please input email and password", Toast.LENGTH_LONG).show()
           return

   }
       if(password!=confpassword){
            Toast.makeText(this,"Password cannot match",Toast.LENGTH_LONG).show()
            return
        }
      auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){
          if (it.isSuccessful){
              Toast.makeText(this,"Signed successfully",Toast.LENGTH_LONG).show()
              finish()
          } else {
              Toast.makeText(this,"Failed to create",Toast.LENGTH_LONG).show()
          }
      }

    }
}