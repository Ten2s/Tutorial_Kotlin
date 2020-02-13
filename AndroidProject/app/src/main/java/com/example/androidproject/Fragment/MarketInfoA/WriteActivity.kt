package com.example.androidproject.Fragment.MarketInfoA

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.androidproject.MainActivity
import com.example.androidproject.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_my_comin.*
import kotlinx.android.synthetic.main.activity_write.*
import kotlinx.android.synthetic.main.fragment_review.*

class WriteActivity : AppCompatActivity() {


    private lateinit var  rating_num : String

    private lateinit var auth : FirebaseAuth

    private val db = FirebaseFirestore.getInstance()

    private lateinit var nickname : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)

        auth = FirebaseAuth.getInstance()

        //rating

        rating_area.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            rating_num = rating.toString()


        }

        //nickname 받아오기

        val docRef = db.collection("users").document(auth.currentUser?.uid.toString())

        docRef.get().addOnSuccessListener { documentSnapshot ->
            nickname = documentSnapshot.get("nickname") as String
        }



        success.setOnClickListener {

            val form = hashMapOf(

                "test" to text_input_area.text.toString(),
                "write" to nickname,
                "rating" to rating_num
            )

            db.collection("review").add(form)
                .addOnSuccessListener { Toast.makeText(this, "성공", Toast.LENGTH_LONG).show()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    finish()
                }
                .addOnFailureListener { Toast.makeText(this, "실패", Toast.LENGTH_LONG).show() }
        }
    }
}
