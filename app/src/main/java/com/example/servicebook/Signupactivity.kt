package com.example.servicebook

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.servicebook.databinding.ActivitySignupactivityBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase

class Signupactivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupactivityBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var processdialog: ProgressDialog
    private lateinit var signInClient: GoogleSignInClient
    private val RC_SIGN_IN = 63
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        processdialog = ProgressDialog(this)
        processdialog.setTitle("Creating Account")
        processdialog.setMessage("We are creating your account")
        database = FirebaseDatabase.getInstance()

        binding.btnsignup.setOnClickListener {
            processdialog.show()
            auth.createUserWithEmailAndPassword(
                binding.signupetemail.text.toString(),
                binding.signupetpassword.text.toString()
            ).addOnCompleteListener(this) { task ->
                processdialog.dismiss()
                if (binding.signupetemail.text.toString()
                        .isEmpty() || binding.signupetpassword.text.toString()
                        .isEmpty() || binding.etusername.text.toString().isEmpty()
                ) {
                    Toast.makeText(this, "Entry field is empty", Toast.LENGTH_SHORT).show()
                    processdialog.dismiss()
                } else {
                    if (task.isSuccessful) {
                        val user = User(
                            binding.etusername.text.toString(),
                            binding.signupetemail.text.toString(),
                            binding.signupetpassword.text.toString()
                        )
                        val id = task.getResult().user?.uid
                        if (id != null) {
                            database.getReference().child("User").child(id).setValue(user)
                        }

                        Toast.makeText(this, "User created succesfully", Toast.LENGTH_SHORT).show()
                        val i = Intent(this, MainActivity::class.java)
                        startActivity(i)
                    } else {
                        Toast.makeText(this, task.exception?.message, Toast.LENGTH_SHORT).show()
                        binding.signupetemail.text.clear()
                    }

                }
            }
        }
        binding.signintext.setOnClickListener {
            val i = Intent(this, Signinactivity::class.java)
            startActivity(i)


        }


        binding.btngooglesignup.setOnClickListener {
            signinwithgoogle()
        }

    }

    private fun signinwithgoogle() {
        Toast.makeText(this, "dfghjk,", Toast.LENGTH_SHORT).show()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
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
                Toast.makeText(this,"jcwjfgwfb",Toast.LENGTH_SHORT).show()

                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Toast.makeText(this, "Google Sign In Failed", Toast.LENGTH_SHORT).show()

            }
        }
    }


    private fun firebaseAuthWithGoogle(idToken: String) {

        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {

                    // Sign in success, update UI with the signed-in user's information

                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)

                    // You can now use 'user' to get user information or perform other tasks
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
                    Snackbar.make(binding.root, "Authentication Failed", Snackbar.LENGTH_SHORT)
                }
            }
    }

}