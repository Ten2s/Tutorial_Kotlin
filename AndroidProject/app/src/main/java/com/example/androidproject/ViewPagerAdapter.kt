package com.example.androidproject

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.viewpager_activity.view.*

class ViewPagerAdapter(private val context: Context) : PagerAdapter()
{
    private var layoutInflater : LayoutInflater? = null

    val Image = arrayOf(
        R.drawable.ai,
        R.drawable.css,
        R.drawable.html
    )
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return 1000000
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        //layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v : View = LayoutInflater.from(context).inflate(R.layout.viewpager_activity, null)
        v.imageview.setImageResource(Image[position % 3])

        container.addView(v, 0)
        return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val v = `object` as View
        vp.removeView(v)
    }
}