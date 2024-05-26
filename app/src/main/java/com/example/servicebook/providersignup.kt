package com.example.servicebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class providersignup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_providersignup)
        val progressbar=findViewById<ProgressBar>(R.id.pg1)
        val editnumber=findViewById<EditText>(R.id.editTextPhone)
        val otpbtn=findViewById<Button>(R.id.button6)
        val auth=FirebaseAuth.getInstance()
        otpbtn.setOnClickListener {
            progressbar.visibility= View.VISIBLE
            otpbtn.visibility=View.INVISIBLE
            val pnnumber="+91"+editnumber.text.toString()
            if(pnnumber.isNotBlank()){
                number.dataSet=pnnumber
            }
            
            val options=PhoneAuthOptions.newBuilder(auth).setPhoneNumber(pnnumber).setTimeout(60L,TimeUnit.SECONDS).setActivity(this).setCallbacks(object:PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    progressbar.visibility= View.INVISIBLE
                    otpbtn.visibility=View.VISIBLE


                }

                override fun onVerificationFailed(e: FirebaseException) {
                    progressbar.visibility= View.INVISIBLE
                    otpbtn.visibility=View.VISIBLE
                }

                override fun onCodeSent(backendotp: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(backendotp, p1)
                    progressbar.visibility= View.INVISIBLE
                    otpbtn.visibility=View.VISIBLE
                    val i= Intent(this@providersignup,providerresendotp::class.java)
                    i.putExtra("number",pnnumber.toString())
                    i.putExtra("backendotp",backendotp)
                    startActivity(i)
                }
            }).build()
            PhoneAuthProvider.verifyPhoneNumber(options)

        }

    
    }




}