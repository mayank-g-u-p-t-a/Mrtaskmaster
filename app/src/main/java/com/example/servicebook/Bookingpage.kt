package com.example.servicebook

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.net.Uri
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.servicebook.databinding.ActivityBookingpageBinding

class Bookingpage : AppCompatActivity() {
    private lateinit var binding:ActivityBookingpageBinding
    private val REQUEST_SMS_PERMISSION = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityBookingpageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val empname=intent.getStringExtra("name").toString()
        val empnumber=intent.getStringExtra("number").toString()
        val empprice=intent.getStringExtra("empprice").toString()+"Rs"
        val etdate=findViewById<EditText>(R.id.editTextDate2)
        val ettime=findViewById<EditText>(R.id.editTextTime)
        val etaddress=findViewById<EditText>(R.id.editTextTextPostalAddress)
        binding.textView17.setText(empname)
        binding.textView18.setText(empnumber)
        binding.textView19.setText(empprice)
        binding.textView18.setOnClickListener {
            val phoneNumber =empnumber // Replace this with the desired phone number
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(intent)
        }
        val sendbtm=findViewById<Button>(R.id.button3)
        sendbtm.setOnClickListener {
            if(empnumber.isNotEmpty()){
                if(checkSmsPermission()){
                    sendSms(empnumber, "I want your assistance in this job on "+etdate.text.toString() +" at "+ettime.text.toString()+" My address is "+etaddress.text.toString()+" For any query contact me on this number")
                }
                else {
                    requestSmsPermission()
                }
            }else {
                Toast.makeText(this, "Please enter both phone number and message", Toast.LENGTH_SHORT).show()
            }

        }

    }
    private fun checkSmsPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }
    private fun requestSmsPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.SEND_SMS),
            REQUEST_SMS_PERMISSION
        )
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_SMS_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, send the SMS
                val phoneNumber =intent.getStringExtra("number").toString()
                val message="jfhsdjhcs"
                sendSms(phoneNumber, message)
            } else {
                Toast.makeText(this, "SMS permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun sendSms(phoneNumber: String, message: String) {
        try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            Toast.makeText(this, "SMS sent successfully to "+phoneNumber, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Toast.makeText(this, "Failed to send SMS", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }


}