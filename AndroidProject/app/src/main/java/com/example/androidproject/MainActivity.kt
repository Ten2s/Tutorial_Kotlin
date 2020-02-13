package com.example.androidproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.androidproject.Auth.LoginActivity
import com.example.androidproject.Auth.MyCominActivity
import com.example.androidproject.Zzim.ZzimActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom.*
import java.net.Inet4Address

class MainActivity : AppCompatActivity() {


    internal lateinit var viewPager: ViewPager
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()


        val gridviewAdapter = GridviewAdapter(this) //adapter class 생성
        gridview.adapter = GridviewAdapter(this)

        gridview.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, LectureActivity::class.java)
            startActivity(intent)
        }


        viewPager = findViewById(R.id.viewpager) as ViewPager
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        my_page.setOnClickListener {

            if(auth.currentUser == null)
            {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            else
            {
                val intent = Intent(this, MyCominActivity::class.java)
                startActivity(intent)
            }
        }

        reserve.setOnClickListener {
            val intent = Intent(this, ZzimActivity::class.java)
            startActivity(intent)
        }
    }
}
