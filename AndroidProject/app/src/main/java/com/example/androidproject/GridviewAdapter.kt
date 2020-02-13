package com.example.androidproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.gridview_item.view.*

class GridviewAdapter(val context: Context) : BaseAdapter(){

    val img = arrayOf(
        R.drawable.ai,
        R.drawable.css,
        R.drawable.html,
        R.drawable.id,
        R.drawable.jpg,
        R.drawable.js,
        R.drawable.mp4,
        R.drawable.pdf,
        R.drawable.php,
        R.drawable.png,
        R.drawable.profile,
        R.drawable.tiff
    )

    val text = arrayOf(
        "ai",
        "css",
        "html",
        "id",
        "jpg",
        "js",
        "mp4",
        "pdf",
        "php",
        "png",
        "profile",
        "tiff"
    )
    override fun getView(position: Int, p1: View?, p2 : ViewGroup?): View {

        val view = LayoutInflater.from(context).inflate(R.layout.gridview_item, null)

        view.gridview_text.text = text[position]
        view.gridview_img.setImageResource(img[position])

        return view
    }

    override fun getItem(position: Int): Any {
        return 0
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return img.size
    }

}