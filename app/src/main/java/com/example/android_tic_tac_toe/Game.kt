package com.example.android_tic_tac_toe

class Game {

    private val board: Array<Array<String>> = Array(3) { Array(3) { " " } }
    var currentPlayer: String = "X"
        private set

    fun makeMove(row: Int, col: Int): Boolean {
        if (row !in 0..2 || col !in 0..2) {
            return false
        }
        if (board[row][col] != " ") {
            return false
        }

        board[row][col] = currentPlayer
        return true
    }


    fun switchPlayer() {
        currentPlayer = if (currentPlayer == "X") "O" else "X"
    }

    fun checkWinner(): String? {

        for (i in 0..2) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != " ") {
                return board[i][0]
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != " ") {
                return board[0][i]
            }
        }

        return null
    }

    fun isBoardFull(): Boolean {
        for (row in board) {
            if (row.contains(" ")) {
                return false
            }
        }
        return true
    }

    fun resetGame() {
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j] = " "
            }
        }
        currentPlayer = "X"
    }

    fun getBoard(): Array<Array<String>> {
        return board
    }


}