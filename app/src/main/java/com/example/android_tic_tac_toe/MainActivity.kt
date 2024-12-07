package com.example.android_tic_tac_toe

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.android_tic_tac_toe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.playAgain.visibility = View.INVISIBLE
        binding.endGameText.visibility = View.INVISIBLE
        game = Game()
        initCells()
        updateTurnButton()
    }

    private fun updateTurnButton() {
        binding.turnButton.text =
            resources.getString(R.string.player_turn, game.currentPlayer.value)
    }

    private fun initCells() {
        val grid = binding.grid

        for (i in 0..<grid.childCount) {
            val cell = grid.getChildAt(i) as ImageView
            
            cell.setImageDrawable(null)
            cell.id = i
            cell.setOnClickListener(::onclick)
        }
    }

    private fun onclick(view: View) {
        val row = view.id / Game.GRID_SIZE
        val col = view.id % Game.GRID_SIZE

        val isMoveValid = game.makeMove(row, col)

        if (!isMoveValid) return

        val imageId =
            if (game.currentPlayer == Game.CellState.X) R.drawable.x else R.drawable.circle

        (view as ImageView).setImageResource(imageId)

        val winner = game.checkWinner()

        if (winner != null) {
            handleGameEnd("Player ${game.currentPlayer.value} won")
        } else if (game.isBoardFull()) {
            handleGameEnd("It's a draw")
        } else {
            game.switchPlayer()
            updateTurnButton()
        }
    }

    private fun handleGameEnd(title: String) {
        binding.playAgain.visibility = View.VISIBLE
        binding.endGameText.visibility = View.VISIBLE
        binding.turnButton.visibility = View.INVISIBLE
        binding.endGameText.text = title
        binding.playAgain.setOnClickListener {
            game.resetGame()
            initCells()
            binding.playAgain.visibility = View.INVISIBLE
            binding.endGameText.visibility = View.INVISIBLE
            binding.turnButton.visibility = View.VISIBLE
            updateTurnButton()
        }
    }
}
