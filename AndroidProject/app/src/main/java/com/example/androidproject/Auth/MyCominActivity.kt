package com.example.androidproject.Auth

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.androidproject.MainActivity
import com.example.androidproject.R
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_my_comin.*
import java.io.File
import io.grpc.internal.ReadableBuffers.openStream
import android.graphics.BitmapFactory
import android.graphics.Bitmap

import android.net.Uri


class MyCominActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_comin)


        auth = FirebaseAuth.getInstance()

        val docRef = db.collection("users").document(auth.currentUser?.uid.toString())

        docRef.get().addOnSuccessListener { documentSnapshot ->

            nickname_area.setText(documentSnapshot.get("nickname").toString())
        }


        logout.setOnClickListener {

            auth.signOut()

            val intent = Intent(this, MainActivity::class.java)

            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        changeimg.setOnClickListener {
            ImagePicker.with(this)
                .galleryOnly()
                .crop(1f, 1f)               //Crop Square image(Optional)
                .compress(1024)         //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)  //Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }


//        imagedownload.setOnClickListener {
//            val ref = FirebaseStorage.getInstance().getReference("이미지 이름")
//
//            ref.downloadUrl
//                .addOnCompleteListener(OnCompleteListener {
//                    task ->
//
//                    if(task.isSuccessful){
//                        Glide.with(this)
//                            .load(task.result)
//                            .into(profile_img)
//                    }else
//                    {
//                        Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
//                    }
//                })
//
//
//            //시발 업로드
//
//
//        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val fileUri = data?.data

            profile_img.setImageURI(fileUri)
            //You can also get File Path from intent
            val filePath : String? = ImagePicker.getFilePath(data)
            println(filePath)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }
}


