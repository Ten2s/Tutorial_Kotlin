package com.example.androidproject.Fragment.MarketInfoA


import android.content.Intent
import android.net.wifi.rtt.WifiRttManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.androidproject.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_my_comin.*
import kotlinx.android.synthetic.main.fragment_review.view.*

/**
 * A simple [Fragment] subclass.
 */
class ReviewFragment : Fragment() {

    private lateinit var auth : FirebaseAuth

    private val db = FirebaseFirestore.getInstance()

    private val nickname_array = ArrayList<String>()
    private val content_array = ArrayList<String>()
    private val rating_array = ArrayList<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        auth = FirebaseAuth.getInstance()
        val view : View = inflater.inflate(R.layout.fragment_review, container, false)


        //adpater 연결

        val review_adapter = ReviewListAdapter(requireContext(), nickname_array, content_array, rating_array)
        view.listview_review.adapter = review_adapter


        //

        db.collection("review")
            .get()
            .addOnSuccessListener { result->
                for(document in result){
                    rating_array.add(document.get("rating") as String)
                    nickname_array.add(document.get("write") as String)
                    content_array.add(document.get("test") as String)

                }
                review_adapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
            }



        view.write_button.setOnClickListener {

            if(auth.currentUser == null)
            {
                Toast.makeText(requireContext(), "회원가입 or 로그인 해주세요", Toast.LENGTH_LONG ).show()
            }
            else{
                val intent = Intent(requireContext(), WriteActivity::class.java)

                startActivity(intent)
            }
        }
        return view
    }


}
