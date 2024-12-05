package com.example.android_tic_tac_toe

class Game {
    enum class CellState {
        X,
        O,
        EMPTY
    }

    private val board: Array<Array<CellState>> = Array(3) { Array(3) { CellState.EMPTY} }
    private var currentPlayer: CellState=CellState.X


    fun makeMove(row: Int, col: Int): Boolean {
        if (row !in 0..2 || col !in 0..2) {
            return false
        }
        if (board[row][col] != CellState.EMPTY) {
            return false
        }

        board[row][col] = currentPlayer
        return true
    }


    fun switchPlayer() {
        currentPlayer = if (currentPlayer == CellState.X) CellState.O else CellState.X
    }

    fun checkWinner(): CellState? {

        for (i in 0..2) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != CellState.EMPTY) {
                return board[i][0]
            }
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != CellState.EMPTY) {
                return board[0][i]
            }
        }


        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != CellState.EMPTY) {
            return board[0][0]
        }
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != CellState.EMPTY) {
            return board[0][2]
        }
        return null
    }



    fun isBoardFull(): Boolean {
        for (row in board) {
            if (row.contains(CellState.EMPTY)) {
                return false
            }
        }
        return true
    }

    fun resetGame() {
        for (i in 0..2) {
            for (j in 0..2) {
                board[i][j] = CellState.EMPTY
            }
        }
        currentPlayer = CellState.X
    }

    fun getBoard(): Array<Array<CellState>> {
        return board
    }


}