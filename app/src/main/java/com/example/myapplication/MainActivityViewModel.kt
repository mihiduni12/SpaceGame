
//HighScoreViewModel.kt
package com.example.myapplication

import android.content.Context
import androidx.lifecycle.ViewModel

class HighScoreViewModel : ViewModel() {
    private var _highScore = 0

    // retrieves the high score from shared preferences.
    fun getHighScore(context: Context): Int {
        val sharedPref = context.getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE)
        _highScore = sharedPref.getInt("highScore", 0)
        return _highScore
    }

    // updates the high score if the new score is higher than the current high score.
    fun updateHighScore(context: Context, newScore: Int) {
        val sharedPref = context.getSharedPreferences("MyPrefsFile", Context.MODE_PRIVATE)
        if (newScore > _highScore) {
            _highScore = newScore
            with(sharedPref.edit()) {
                putInt("highScore", _highScore)
                apply()
            }
        }
    }
}
