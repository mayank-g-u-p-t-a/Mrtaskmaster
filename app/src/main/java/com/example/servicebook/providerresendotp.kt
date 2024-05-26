package com.example.servicebook

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class providerresendotp : AppCompatActivity() {
    private lateinit var getotpbackend:String
    private lateinit var phoneauthcred:PhoneAuthCredential
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_providerresendotp)

        val number = findViewById<TextView>(R.id.textView30)
        val resendotp = findViewById<TextView>(R.id.textView28)
        val submitbtn = findViewById<Button>(R.id.button7)

        val progressbar=findViewById<ProgressBar>(R.id.progressbar2)


        val otpno1 = findViewById<EditText>(R.id.textView20)
        val otpno2 = findViewById<EditText>(R.id.textView21)
        val otpno3 = findViewById<EditText>(R.id.textView22)
        val otpno4 = findViewById<EditText>(R.id.textView23)
        val otpno5 = findViewById<EditText>(R.id.textView24)
        val otpno6 = findViewById<EditText>(R.id.textView26)

        val str = "+91" + intent.getStringExtra("number").toString()
        number.setText(str)
getotpbackend=intent.getStringExtra("backendotp").toString()
        otpno1.addTextChangedListener{otpno2.requestFocus()}
        otpno2.addTextChangedListener{otpno3.requestFocus()}
        otpno3.addTextChangedListener{otpno4.requestFocus()}
        otpno4.addTextChangedListener{otpno5.requestFocus()}
        otpno5.addTextChangedListener{otpno6.requestFocus()}

        otpno2.setOnKeyListener { v, keyCode, event -> handleBackPress(otpno1, keyCode, event) }
        otpno3.setOnKeyListener { v, keyCode, event -> handleBackPress(otpno2, keyCode, event) }
        otpno4.setOnKeyListener { v, keyCode, event -> handleBackPress(otpno3, keyCode, event) }
        otpno5.setOnKeyListener { v, keyCode, event -> handleBackPress(otpno4, keyCode, event) }
        otpno6.setOnKeyListener { v, keyCode, event -> handleBackPress(otpno5, keyCode, event) }


        submitbtn.setOnClickListener {
            if (otpno1.text.toString().trim().isEmpty() || otpno2.text.toString().trim()
                    .isEmpty() || otpno3.text.toString().trim().isEmpty() || otpno4.text.toString()
                    .trim().isEmpty() || otpno5.text.toString().trim()
                    .isEmpty() || otpno6.text.toString().trim().isEmpty()
            ) {
                Toast.makeText(this, "otp verify", Toast.LENGTH_SHORT).show()
            }

         else {

                val entercodeotp=otpno1.text.toString()+otpno2.text.toString()+otpno3.text.toString()+otpno4.text.toString()+otpno5.text.toString()+otpno6.text.toString()
                if(getotpbackend!=null){
                    progressbar.visibility=View.VISIBLE
                    submitbtn.visibility=View.INVISIBLE
                    phoneauthcred=PhoneAuthProvider.getCredential(getotpbackend,entercodeotp)
                    FirebaseAuth.getInstance().signInWithCredential(phoneauthcred).addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign-in is successful, you can get the signed-in

                            progressbar.visibility=View.INVISIBLE
                            submitbtn.visibility=View.VISIBLE

                            val i= Intent(this,entriesofprovider::class.java)
                            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                            startActivity(i)
                            // Proceed with the authenticated user
                        } else {
                            progressbar.visibility=View.INVISIBLE
                            submitbtn.visibility=View.VISIBLE

                            Toast.makeText(this,"Enter Correct oTP",Toast.LENGTH_SHORT).show()
                            // Sign-in failed
                            // Handle the failure, such as displaying an error message to the user

                        }
                    }
                }else{
                    Toast.makeText(this,"Please check your internet connection",Toast.LENGTH_SHORT).show()
                }

            }
            }
        }

    private fun handleBackPress(prevet: EditText?, keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_DEL && event?.action == KeyEvent.ACTION_DOWN) {
            if (prevet != null) {
                if (prevet.text.isEmpty()) {
                    prevet.requestFocus()
                    return true
                }
            }
        }
        return false


    }


}





