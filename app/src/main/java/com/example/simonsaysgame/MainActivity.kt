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

        var exitPressed = false

        //When start is pushed, change button text to show that user is playing
        while (exitPressed == false) {
            startButton.setOnClickListener {
                Toast.makeText(this, "Playing", Toast.LENGTH_SHORT).show()
                play(yellowButton, blueButton, redButton, greenButton)
            }

            exitButton.setOnClickListener {
                //close app
                exitPressed = true
                finish()
            }
        }

    }
}


fun play(yButton : Button, bButton : Button, rButton : Button, gButton : Button) {
    var win = true
    val buttonOrder = mutableListOf<String>()
    val playerOrder = mutableListOf<String>()
    var level = 1

    //until the player loses
    while (win) {
        println("Simon Says...")
        for (i in 1..level) {
            //only adds to list when iterator is at the end of the list
            if (i > buttonOrder.size) {
                //chooses a number which represents a color
                val randomColor = (1..4).random()

                //adds a color to the list that the player has to match
                when (randomColor) {
                    1 -> {
                        buttonOrder.add("red")
                        rButton.performClick()
                        Thread.sleep(800)
                        } //add this string, blink button
                    2 -> {
                        buttonOrder.add("blue")
                        bButton.performClick()
                        Thread.sleep(800)
                    }
                    3 -> {
                        buttonOrder.add("yellow")
                        yButton.performClick()
                        Thread.sleep(800)
                    }
                    4 -> {
                        buttonOrder.add("green")
                        gButton.performClick()

                    }
                }
            }
            Thread.sleep(1000)
        }

        //println("Your turn!")
        //Toast.makeText(Context.this, "Your Turn!", Toast.LENGTH_SHORT).show()

        //get player's button guesses
        playerOrder.clear()
        for (i in 1..level) {
            //val playerButton = readln()
            //playerOrder.add(playerButton)
            //println("added item $playerButton to index $i")

            //when player hits a button, add button to list of player's button order
            rButton.setOnClickListener {
                playerOrder.add("red")
            }
            bButton.setOnClickListener {
                playerOrder.add("blue")
            }
            yButton.setOnClickListener {
                playerOrder.add("yellow")
            }
            gButton.setOnClickListener {
                playerOrder.add("green")
            }
        }

        //iterates through comp's button order and player's and compares to see if player wins level
        for (i in 1..level) {
            //if player's button order does not match the CPU's
            if (playerOrder[i-1] != buttonOrder[i-1]) {
                //end game
                win = false
                break
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

        //else, increase level and play again
        level += 1
    }

}