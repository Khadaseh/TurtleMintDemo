package com.turtlemint.turtlemintproject.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.turtlemint.turtlemintproject.view.R
import dagger.hilt.android.AndroidEntryPoint
// entry point activity

@AndroidEntryPoint
class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}

