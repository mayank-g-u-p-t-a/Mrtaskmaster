package com.example.servicebook

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

class updateddata : AppCompatActivity()
{
    private lateinit var db: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var storageReference: FirebaseStorage
    private lateinit var selectedImageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_updateddata)

        val updatebtn = findViewById<Button>(R.id.button10)
        val uploadImageButton = findViewById<Button>(R.id.button9)
        val imageView = findViewById<ImageView>(R.id.imageView5)
        val editname = findViewById<EditText>(R.id.editTextText)
        val editpincode = findViewById<EditText>(R.id.editTextPhone2)
        val editaddress = findViewById<EditText>(R.id.editTextTextPostalAddress2)
        val editprice = findViewById<EditText>(R.id.editTextNumber)
        val savenumber = findViewById<TextView>(R.id.textView37)

        val no = number.dataSet
        savenumber.text = no
        val spinner1 = findViewById<Spinner>(R.id.spinner2)
        val spinner2 = findViewById<Spinner>(R.id.spinner3)
        val items1 = arrayOf("Ghaziabad", "Delhi", "Muradnagar", "Mumbai", "Etawah")
        var loc = ""
        var work = ""
        val items2 = arrayOf("Cleaning Service", "House help", "Chef", "Barber", "Masseuse", "Electrician", "Plumber", "Beautician")
        val arrayadapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items1)
        spinner1.adapter = arrayadapter
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                loc = items1[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
        val arrayAdapter1 = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items2)
        spinner2.adapter = arrayAdapter1
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                work = items2[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        uploadImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            startActivityForResult(intent, 100)
        }

        updatebtn.setOnClickListener {
            val name = editname.text.toString()
            val pincode = editpincode.text.toString()
            val price = editprice.text.toString()
            val address = editaddress.text.toString()

            if (name.isNotEmpty() && loc.isNotEmpty() && work.isNotEmpty()) {
                if (::selectedImageUri.isInitialized) {
                    uploadImageToFirebase(selectedImageUri) { imageUrl ->
                        val users = usermoddel(name, no, loc, work, price, imageUrl)
                        db = FirebaseDatabase.getInstance()
                        reference = db.getReference("Users")
                        if (no != null) {
                            val userRef = reference.child(no)
                            userRef.child("name").setValue(name)
                            userRef.child("number").setValue(no)
                            userRef.child("location").setValue(loc)
                            userRef.child("work").setValue(work)
                            userRef.child("price").setValue(price)
                            userRef.child("image").setValue(imageUrl)
                            editaddress.setText("")
                            editname.setText("")
                            editpincode.setText("")
                            editprice.setText("")

                            val i = Intent(this, entriesofprovider::class.java)
                            startActivity(i)
                        }
                    }
                } else {
                    // Image is not selected, upload only other data
                    val users = usermoddel(name, no, loc, work, price, "")
                    db = FirebaseDatabase.getInstance()
                    reference = db.getReference("Users")
                    if (no != null) {
                        val userRef = reference.child(no)
                        userRef.child("name").setValue(name)
                        userRef.child("number").setValue(no)
                        userRef.child("location").setValue(loc)
                        userRef.child("work").setValue(work)
                        userRef.child("price").setValue(price)
                        editaddress.setText("")
                        editname.setText("")
                        editpincode.setText("")
                        editprice.setText("")

                        val i = Intent(this, entriesofprovider::class.java)
                        startActivity(i)
                    }
                }
            } else {
                Toast.makeText(applicationContext, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data!!
            val imageView = findViewById<ImageView>(R.id.imageView5)
            imageView.setImageURI(selectedImageUri)
        }
    }

    private fun uploadImageToFirebase(fileUri: Uri?, callback: (String) -> Unit) {
        if (fileUri == null) {
            Toast.makeText(applicationContext, "No image selected", Toast.LENGTH_SHORT).show()
            return
        }

        storageReference = FirebaseStorage.getInstance()
        val ref = storageReference.reference.child("uploads/" + UUID.randomUUID().toString())
        ref.putFile(fileUri)
            .addOnSuccessListener {
                ref.downloadUrl.addOnSuccessListener {
                    callback(it.toString())
                }
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Failed to upload image", Toast.LENGTH_SHORT).show()
            }
    }

}