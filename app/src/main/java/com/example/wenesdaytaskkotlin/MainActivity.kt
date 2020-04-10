package com.example.wenesdaytaskkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wenesdaytaskkotlin.Fragments.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.frame,
            HomeFragment()
        ).addToBackStack(null).commit()

    }

}
