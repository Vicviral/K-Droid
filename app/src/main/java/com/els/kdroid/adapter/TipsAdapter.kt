package com.els.kdroid.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.els.kdroid.R
import kotlinx.android.synthetic.main.tips_card_item.view.*

class TipsAdapter (private val context: Context, private val tipsModelArrayList: ArrayList<TipsModel>): PagerAdapter() {
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return tipsModelArrayList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        //inflate layout tips_card_item
        val view = LayoutInflater.from(context).inflate(R.layout.tips_card_item, container, false)

        //get data
        val model = tipsModelArrayList[position]
        val title = model.title
        val description = model.description
        val image = model.image

        //set data for ui views
        view.bannerImageView.setImageResource(image)
        view.titleTv.text = title
        view.descriptionTv.text = description

        //handle click listener
        view.setOnClickListener {
//            Toast.makeText(context, "$title", Toast.LENGTH_SHORT).show()
        }

        //add view to container
        container.addView(view, position)


        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}