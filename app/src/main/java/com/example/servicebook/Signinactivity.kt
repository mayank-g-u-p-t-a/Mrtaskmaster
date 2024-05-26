package com.example.servicebook

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.servicebook.databinding.ActivitySigninactivityBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase

class Signinactivity : AppCompatActivity() {

    private lateinit var binding:ActivitySigninactivityBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var processdialog: ProgressDialog
    private lateinit var signInClient: GoogleSignInClient
    private val RC_SIGN_IN=66

    private lateinit var database: FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySigninactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)



        database=FirebaseDatabase.getInstance()
        auth=FirebaseAuth.getInstance()
        processdialog=ProgressDialog(this)
        processdialog.setTitle("Logging in")
        processdialog.setMessage("We are trying to log you in")
        binding.signintext.setOnClickListener {
            val i=Intent(this,Signupactivity::class.java)
            startActivity(i)

        }
        binding.btnsignin.setOnClickListener{
            processdialog.show()
            if(binding.signinetemail.text.toString().isEmpty() || binding.signinetpassword.text.toString().isEmpty()){
                Toast.makeText(this,"Entry field is empty", Toast.LENGTH_SHORT).show()
                processdialog.dismiss()
            }

            else{
                auth.signInWithEmailAndPassword(binding.signinetemail.text.toString(),binding.signinetpassword.text.toString()).addOnCompleteListener(this){task->
                    processdialog.dismiss()
                    if(task.isSuccessful){
                        binding.signinetemail.text.clear()
                        binding.signinetpassword.text.clear()
                        val intent= Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        binding.signinetemail.text.clear()
                        binding.signinetpassword.text.clear()
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                    }

                }
            }

        }

        binding.btngooglesignin.setOnClickListener {
            signinwithgoogle()
        }

    }
    private fun signinwithgoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestProfile()
            .requestEmail()

            .build()
        signInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = signInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)


            try {

                val account = task.getResult(ApiException::class.java)!!


                val credentials=GoogleAuthProvider.getCredential(account.idToken,null)

                auth.signInWithCredential(credentials).addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {
                        Toast.makeText(this,"dfghjk,",Toast.LENGTH_SHORT).show()
                        // Sign in success
                        val user = FirebaseAuth.getInstance().currentUser
                        val i = Intent(this, MainActivity::class.java)
                        startActivity(i)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(this, "Authentication Failed.", Toast.LENGTH_SHORT).show()
                    }
                }

                } catch (e: ApiException) {
                Toast.makeText(this, "Google Sign In Failed", Toast.LENGTH_SHORT).show()

            }
        }
    }



}