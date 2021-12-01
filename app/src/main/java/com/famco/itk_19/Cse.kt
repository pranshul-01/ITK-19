package com.famco.itk_19

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import android.widget.TextView
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import android.widget.Toast
import androidx.annotation.RequiresApi
import android.os.Build
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import com.famco.itk_19.attandence.AttendanceDashboardActivity

class Cse : AppCompatActivity(), View.OnClickListener {
    private lateinit var image: ImageButton
    private lateinit var study: ImageView
    private lateinit var attend: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cse)
        image = findViewById(R.id.imageButton)
        image.setOnClickListener(this)
        study = findViewById(R.id.study)
        study.setOnClickListener(this)
        attend = findViewById(R.id.attend)

        attend.setOnClickListener { view ->
            if(view == attend) {
                val intent = Intent(this,AttendanceDashboardActivity::class.java)
                 startActivity(intent)
            }
        }

        val user = FirebaseAuth.getInstance().currentUser
        val reference = FirebaseDatabase.getInstance().getReference("Student")
        assert(user != null)
        val userID = user!!.uid
        val email1 = findViewById<TextView>(R.id.email)
        val it = findViewById<TextView>(R.id.item)
        val it1 = findViewById<TextView>(R.id.item1)
        val name1 = findViewById<TextView>(R.id.name)
        reference.child(userID).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userprofile = snapshot.getValue(Student::class.java)
                if (userprofile != null) {
                    val email = userprofile.email
                    val item = userprofile.item
                    val item1 = userprofile.item1
                    val name = userprofile.name
                    email1.text = "Email: $email"
                    it.text = "Branch: $item"
                    it1.text = "Sem:$item1"
                    name1.text = "Name: $name"
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Cse, "Something went wrong!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onClick(v: View) {
        if (v === study) {
            val intent = Intent(this@Cse, Electrical::class.java)
            startActivity(intent)
        }
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) pickImage() else requestPermissions(
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE
            ), 123
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 123) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) pickImage() else Toast.makeText(
                this,
                "please allow the Storage permission",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun pickImage() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        val chooser = Intent.createChooser(galleryIntent, "Select Image")
        startActivityForResult(chooser, 123)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123 && resultCode == RESULT_OK && data != null) {
            val uri = data.data
            image!!.setImageURI(uri)
        }
    }
}