package com.example.androidproject.Fragment.MarketInfoA

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import com.example.androidproject.R
import com.example.androidproject.Utils.FirebaseUtils
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.android.synthetic.main.activity_market_info.*
import kotlinx.android.synthetic.main.activity_market_info.view.*

class MarketInfoActivity : AppCompatActivity() {

    private var auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_market_info)

        lecture_text.text = intent.getStringExtra("title")


        FirebaseUtils.getUid()
        FirebaseUtils.db

        figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25f)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_area, ContentFragment()).commit()


        figure_1.setOnClickListener {
            figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25f)
            figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15f)
            figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15f)


            supportFragmentManager.beginTransaction().replace(R.id.fragment_area, ContentFragment()).commit()
        }
        figure_2.setOnClickListener {
            figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15f)
            figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25f)
            figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15f)
            supportFragmentManager.beginTransaction().replace(R.id.fragment_area, InfoFragment()).commit()

        }

        figure_3.setOnClickListener {
            figure_1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15f)
            figure_2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15f)
            figure_3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25f)
            supportFragmentManager.beginTransaction().replace(R.id.fragment_area, ReviewFragment()).commit()
        }

        val lecture = hashMapOf(
            "lecture_title" to intent.getStringExtra("title")
        )

        val data = intent.getStringExtra("title")
        zzim.setOnClickListener{

            FirebaseUtils.db.collection("users").document(FirebaseUtils.getUid())
                .update(data, true )
                .addOnSuccessListener {
                    zzim_header.text = "찜 하신 상품입니다."
                    zzim_header.setTextColor(Color.BLUE)
                    Toast.makeText(this, "성공", Toast.LENGTH_LONG).show()
                }
                .addOnSuccessListener {
                    Toast.makeText(this, "실패", Toast.LENGTH_LONG).show()
                }
        }
    }
}
