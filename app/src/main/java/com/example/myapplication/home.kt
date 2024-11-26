package com.example.myapplication

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Optional: Finish the current activity to prevent going back to it on back press

        },5000)//10000

        // Find ImageViews by their IDs
        val imageView1 = findViewById<View>(R.id.imageView22)
        val imageView2 = findViewById<View>(R.id.imageView211)
        val imageView3 = findViewById<View>(R.id.imageView0)
       // val imageView4 = findViewById<View>(R.id.imageView)
        val imageView5 = findViewById<View>(R.id.imageView2)

        // Animate each ImageView with a different delay
        animateImageView(imageView1, 500L)
        animateImageView(imageView2, 700L)
        animateImageView(imageView3, 900L)
      //  animateImageView(imageView4, 1100L)
        animateImageView(imageView5, 1300L)

        // Apply padding to handle system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun animateImageView(view: View, delay: Long) {
        val translationX = 200f // adjust this value as needed
        val translationY = 200f // adjust this value as needed

        // Create ObjectAnimator for translation in X direction
        ObjectAnimator.ofFloat(view, View.TRANSLATION_X, translationX)
            .apply {
                startDelay = delay
                duration = 1000 // adjust this value as needed
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.REVERSE
                start()
            }

        // Create ObjectAnimator for translation in Y direction
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, translationY)
            .apply {
                startDelay = delay
                duration = 1000 // adjust this value as needed
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.REVERSE
                start()
            }
    }
}


/*package com.example.myapplication

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class home : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Optional: Finish the current activity to prevent going back to it on back press

        }, 9000) // 5000 milliseconds delay

        val imageView1 = findViewById<ImageView>(R.id.imageView22)
        val imageView2 = findViewById<ImageView>(R.id.imageView211)
        val imageView3 = findViewById<ImageView>(R.id.imageView0)
        val imageView5 = findViewById<ImageView>(R.id.imageView2)


        // Custom animation for each ImageView
        animateImageView(imageView1, 0)
        animateImageView(imageView2, 300)
        animateImageView(imageView3, 600)
        animateImageView(imageView5, 900)

        // Apply padding to handle system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun animateImageView(view: ImageView, delay: Long) {
        val translationX = 100f
        val translationY = 100f
        val rotation = 360f

        // ObjectAnimator for translation in X direction
        ObjectAnimator.ofFloat(view, View.TRANSLATION_X, translationX)
            .apply {
                startDelay = delay
                duration = 1000
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.REVERSE
                start()
            }

        // ObjectAnimator for translation in Y direction
        ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, translationY)
            .apply {
                startDelay = delay
                duration = 1000
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.REVERSE
                start()
            }

        // ObjectAnimator for rotation
        ObjectAnimator.ofFloat(view, View.ROTATION, rotation)
            .apply {
                startDelay = delay
                duration = 2000
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.REVERSE
                interpolator = AccelerateDecelerateInterpolator()
                start()
            }

        // ObjectAnimator for scaling
        ObjectAnimator.ofFloat(view, View.SCALE_X, 0.5f, 1.0f)
            .apply {
                startDelay = delay
                duration = 1000
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.REVERSE
                interpolator = AccelerateDecelerateInterpolator()
                start()
            }

        ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.5f, 1.0f)
            .apply {
                startDelay = delay
                duration = 1000
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.REVERSE
                interpolator = AccelerateDecelerateInterpolator()
                start()
            }
    }
}*/
