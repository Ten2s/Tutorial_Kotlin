package com.example.androidproject.Fragment.ListFragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidproject.Fragment.MarketInfoA.MarketInfoActivity
import com.example.androidproject.R
import com.example.androidproject.Utils.FirebaseUtils
import kotlinx.android.synthetic.main.fragment_first.view.*

/**
 * A simple [Fragment] subclass.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val img = arrayOf(
            R.drawable.list1,
            R.drawable.list2,
            R.drawable.list3,
            R.drawable.list4,
            R.drawable.list5,
            R.drawable.list6,
            R.drawable.list7,
            R.drawable.list8,
            R.drawable.list9
        )
        val view : View = inflater.inflate(R.layout.fragment_first, container, false)

        var list_array = arrayListOf<ContentsListModel>(
            ContentsListModel(
                img[0],
                "채현욱",
                1,
                "안녕하세요"
            ),
            ContentsListModel(
                img[1],
                "채마스",
                1,
                "잘부탁드립니다."
            ),
            ContentsListModel(
                img[2],
                "함의진",
                1,
                "박재현헤헤헤"
            ),
            ContentsListModel(
                img[3],
                "박재현",
                1,
                "허승밖에 없엉 ㅎ"
            ),
            ContentsListModel(
                img[4],
                "허승",
                1,
                "재현씨 어디있어요"
            ),
            ContentsListModel(
                img[5],
                "날개없는주희",
                1,
                "천사에요~ 채현욱 여자친구"
            ),
            ContentsListModel(
                img[6],
                "김현희",
                1,
                "바빠용~"
            ),
            ContentsListModel(
                img[7],
                "김경민",
                1,
                "안녕하세요"
            )
            )
        val list_adapter = FirstFragAdapter(
            requireContext(),
            list_array
        )
        view.listview_first_fragment.adapter = list_adapter


        FirebaseUtils.db
            .collection("users")
            .document(FirebaseUtils.getUid())
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if(documentSnapshot.exists() == true)
                {

                }
                else{
                    val lecture = hashMapOf(
                        "채현욱" to "",
                        "채마스" to "",
                        "함의진" to "",
                        "박재현" to "",
                        "허승" to "",
                        "날개없는주희" to "",
                        "김현희" to "",
                        "김경민" to ""
                    )

                    FirebaseUtils.db
                        .collection("users")
                        .document(FirebaseUtils.getUid())
                        .collection("zzim")
                        .document("zzim")
                        .set(lecture)
                        .addOnSuccessListener {

                        }
                        .addOnFailureListener {

                        }
                }
            }
            .addOnFailureListener {

            }

        view.listview_first_fragment.setOnItemClickListener{ adapterView, view, i, l ->
            val intent = Intent(requireContext(), MarketInfoActivity::class.java)
            intent.putExtra("title", list_array.get(i).title)
            startActivity(intent)
        }

        return view
    }


}
