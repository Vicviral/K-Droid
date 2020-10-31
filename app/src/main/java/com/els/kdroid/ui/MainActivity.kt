package com.els.kdroid.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.els.kdroid.R
import com.els.kdroid.adapter.TipsAdapter
import com.els.kdroid.adapter.TipsModel
import com.els.kdroid.botDoctor.BotDoctor
import com.els.kdroid.covid.CovidTracker
import com.els.kdroid.find.MapsActivity
import com.els.kdroid.steps.StepTracker
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var myModelList: ArrayList<TipsModel>
    private lateinit var myAdapter: TipsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //menu
        menu.setOnClickListener {
            startActivity(Intent(this, Menu::class.java))
            overridePendingTransition(
                R.anim.exit_in,
                R.anim.exit_out
            )
        }

        loadCards()

        //add page change listener
        tipsViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                //change page description
                val description = myModelList[position].description
                tipsDescription.text = description
            }

            override fun onPageSelected(position: Int) {
            }

        })

    }

    private fun loadCards() {
        //init list
        myModelList = ArrayList()

        //add items
        myModelList.add(
            TipsModel(
                "Social Distance",
                "Send friends and family love from afar",
                R.drawable.social_distance
            )
        )
        myModelList.add(
            TipsModel(
                "Wash Hands",
                "Wash your hands for at least 20 seconds",
                R.drawable.wash_hands
            )
        )
        myModelList.add(
            TipsModel(
                "Meditate",
                "Meditation can help in reducing anxiety",
                R.drawable.meditation
            )
        )
        myModelList.add(
            TipsModel(
                "Health Food",
                "Boast your immune system with healthy diet",
                R.drawable.food
            )
        )
        myModelList.add(
            TipsModel(
                "Exercise",
                "Keeping fit is one way we can survive this hard time",
                R.drawable.yoga
            )
        )

        //set up adapter
        myAdapter = TipsAdapter(this, myModelList)

        //set adapter to view pager
        tipsViewPager.adapter = myAdapter

        //set default padding
        tipsViewPager.setPadding(100, 0, 100, 0)

    }

    fun viewCovidTracker(view: View) {
        startActivity(Intent(this, CovidTracker::class.java))
        overridePendingTransition(
            R.anim.slide_down,
            R.anim.exit_out
        )
    }

    fun viewSteps(view: View) {
        startActivity(Intent(this, StepTracker::class.java))
        overridePendingTransition(
            R.anim.slide_down,
            R.anim.exit_out
        )
    }

    fun viewChatDoctor(view: View) {
        startActivity(Intent(this, BotDoctor::class.java))
        overridePendingTransition(
            R.anim.exit_in,
            R.anim.exit_out
        )
    }

    fun viewFind(view: View) {
        startActivity(Intent(this, MapsActivity::class.java))
        overridePendingTransition(
            R.anim.slide_down,
            R.anim.exit_out
        )
    }

    fun viewRest(view: View) {}
}