package com.els.kdroid.steps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.els.kdroid.R

class StepTracker : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step_tracker)


    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.exit_in, R.anim.exit_out)
    }
}