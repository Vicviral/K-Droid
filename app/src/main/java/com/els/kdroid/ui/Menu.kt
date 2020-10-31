package com.els.kdroid.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.els.kdroid.R

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    fun home(view: View) {
        finish()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(
            R.anim.exit_in,
            R.anim.exit_out
        )
    }
}