package com.example.simonsaysgame

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Menu buttons variables
        val startButton : Button = findViewById(R.id.startButton)
        val exitButton : Button = findViewById(R.id.exitButton)
        val highScoreButton : Button = findViewById(R.id.highScoreButton)



        //Colored buttons variables
        val yellowButton : Button = findViewById(R.id.yellowButton)
        val blueButton : Button = findViewById(R.id.blueButton)
        val redButton : Button = findViewById(R.id.redButton)
        val greenButton : Button = findViewById(R.id.greenButton)

        //When start is pushed, change button text to show that user is playing
            startButton.setOnClickListener {
                Toast.makeText(this, "Playing", Toast.LENGTH_SHORT).show()
                play(yellowButton, blueButton, redButton, greenButton)
            }

            exitButton.setOnClickListener {
                //close app
                finish()
            }

    }
}


fun play(yButton : Button, bButton : Button, rButton : Button, gButton : Button) {
    var win = true
    val buttonOrder = mutableListOf<String>()
    val playerOrder = mutableListOf<String>()
    var level = 1

    val randomColor = (1..4).random()
    when (randomColor) {
        1 -> {
            buttonOrder.add("red")
        } //add this string
        2 -> {
            buttonOrder.add("blue")
        }
        3 -> {
            buttonOrder.add("yellow")
        }
        4 -> {
            buttonOrder.add("green")
        }
    }

    //get player's button guesses
    playerOrder.clear()
    //when player hits a button, add button to list of player's button order
    rButton.setOnClickListener {
        playerOrder.add("red")
        if (playerOrder.size == buttonOrder.size) {
            for (i in 0..playerOrder.size-1){
                if (playerOrder[i] != buttonOrder[i]){
                    win = false
                    break
                }
            }
            level++
            cpuTurn(level,buttonOrder,rButton,bButton,yButton,gButton)
        }
    }
    bButton.setOnClickListener {
        playerOrder.add("blue")
        if (playerOrder.size == buttonOrder.size) {
            for (i in 0..playerOrder.size-1){
                if (playerOrder[i] != buttonOrder[i]){
                    win = false
                    break
                }
            }
            level++
            cpuTurn(level,buttonOrder,rButton,bButton,yButton,gButton)
        }
    }
    yButton.setOnClickListener {
        playerOrder.add("yellow")
        if (playerOrder.size == buttonOrder.size) {
            for (i in 0..playerOrder.size-1){
                if (playerOrder[i] != buttonOrder[i]){
                    win = false
                    break
                }
            }
            level++
            cpuTurn(level,buttonOrder,rButton,bButton,yButton,gButton)
        }
    }
    gButton.setOnClickListener {
        playerOrder.add("green")
        if (playerOrder.size == buttonOrder.size) {
            for (i in 0..playerOrder.size-1){
                if (playerOrder[i] != buttonOrder[i]){
                    win = false
                    break
                }
            }
            level++
            cpuTurn(level,buttonOrder,rButton,bButton,yButton,gButton)
        }
    }

    //if player lost, game over
    if (!win) {
        //Toast.makeText(this, "Game Over! Lost at level: $level", Toast.LENGTH_SHORT).show()
        //adds the current level to the high score list, then sorts it
        //highScores.add(level)
        //highScores.sort()
        return
    }


}

fun cpuTurn(level : Int, buttonOrder : MutableList<String>, rButton : Button, bButton : Button, yButton : Button, gButton : Button) {
    for (i in 1..level) {
        //only adds to list when iterator is at the end of the list
        if (i > buttonOrder.size) {
            //chooses a number which represents a color
            val randomColor = (1..4).random()

            //adds a color to the list that the player has to match
            when (randomColor) {
                1 -> {
                    buttonOrder.add("red")
                } //add this string
                2 -> {
                    buttonOrder.add("blue")
                }
                3 -> {
                    buttonOrder.add("yellow")
                }
                4 -> {
                    buttonOrder.add("green")
                }
            }
        }
    }

    for (button in buttonOrder) {
        when(button) {
            "red" -> {
                rButton.performLongClick()
                Thread.sleep(500)
            }
            "blue" -> {
                bButton.performLongClick()
                Thread.sleep(500)
            }
            "yellow" -> {
                yButton.performLongClick()
                Thread.sleep(500)
            }
            "green" -> {
                gButton.performLongClick()
                Thread.sleep(500)
            }
        }
    }
}