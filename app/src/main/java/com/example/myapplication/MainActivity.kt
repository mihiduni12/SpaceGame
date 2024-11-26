
package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity(), GameTask {

    private lateinit var highScoreViewModel: HighScoreViewModel
    private lateinit var rootLayout: LinearLayout
    private lateinit var starBtn: Button
    private lateinit var mGameView: GameView
    private lateinit var gameOver: TextView
    private lateinit var score: TextView
    private lateinit var highScoreTextView: TextView
    private var gameRunning = false



    //initializes the views, sets up the high score ViewModel, and sets a click listener on the start button.
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // retriev the high score data using the ViewModel pattern.
        highScoreViewModel = ViewModelProvider(this).get(HighScoreViewModel::class.java)

        starBtn = findViewById(R.id.startBtn)
        rootLayout = findViewById(R.id.rootLayout)
        score = findViewById(R.id.score)
        highScoreTextView = findViewById(R.id.highScoreTextView)
        gameOver= findViewById(R.id.gameOver)

        // Initialize high score text
        val highScore = highScoreViewModel.getHighScore(this)
        highScoreTextView.text = "High Score: $highScore"
        gameOver.visibility=View.GONE

        starBtn.setOnClickListener {
            if (!gameRunning) {
                startGame()
            }
        }
    }

    //Called when the user clicks on the start button.
    private fun startGame() {
        mGameView = GameView(this, this)
        mGameView.setBackgroundResource(R.drawable.space)
        rootLayout.addView(mGameView)
        gameOver.visibility= View.GONE
        starBtn.visibility = View.GONE
        score.visibility = View.GONE
        gameRunning = true
    }

    //Called when the game ends.
    override fun closeGame(mScore: Int) {
        score.text = "Score : $mScore"
        rootLayout.removeView(mGameView)
        gameOver.visibility=View.VISIBLE
        starBtn.visibility = View.VISIBLE
        score.visibility = View.VISIBLE
        gameRunning = false

        // Update high score
        highScoreViewModel.updateHighScore(this, mScore)
        val newHighScore = highScoreViewModel.getHighScore(this)
        highScoreTextView.text = "High Score: $newHighScore"
    }
}

