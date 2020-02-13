package com.example.androidproject.Zzim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidproject.R
import com.example.androidproject.Utils.FirebaseUtils

class ZzimActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zzim)

        val array_list = ArrayList<String>()

        FirebaseUtils.db
            .collection("users")
            .document(FirebaseUtils.getUid())
            .get()
            .addOnSuccessListener {
                documentSnapshot ->

                if(documentSnapshot.get("Lang1") != ""){
                    array_list.add("채현욱")
                }
                else  if(documentSnapshot.get("Lang1") != ""){
                    array_list.add("채마스")
                }
                else  if(documentSnapshot.get("Lang1") != ""){
                    array_list.add("함의진")
                }
                else  if(documentSnapshot.get("Lang1") != ""){
                    array_list.add("박재현")
                } else  if(documentSnapshot.get("Lang1") != ""){
                    array_list.add("허승")
                } else  if(documentSnapshot.get("Lang1") != ""){
                    array_list.add("날개없는주희")
                } else  if(documentSnapshot.get("Lang1") != ""){
                    array_list.add("김현희")
                } else  if(documentSnapshot.get("Lang1") != ""){
                    array_list.add("김경민")
                } else  if(documentSnapshot.get("Lang1") != ""){
                    array_list.add("Lang1")
                } else  if(documentSnapshot.get("Lang1") != ""){
                    array_list.add("Lang1")
                }
            }
    }
}
