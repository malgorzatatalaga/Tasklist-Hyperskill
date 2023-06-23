package tasklist

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val listOfTasks = mutableListOf<String>()
    var end = false

    while (!end) {
        println("Input an action (add, print, end):")
        when (scanner.nextLine().lowercase().trimIndent()) {
            "end" -> {
                println("Tasklist exiting!")
                end = true
            }

            "add" -> {
                println("Input a new task (enter a blank line to end):")
                input(listOfTasks)
            }

            "print" -> {
                print(listOfTasks)
            }

            else -> {
                println("The input action is invalid")
            }
        }
    }

}

fun input(listOfTasks: MutableList<String>) {
    val scanner = Scanner(System.`in`)
    var strings = ""
    val firstInput = scanner.nextLine().trimIndent()
    when {
        firstInput.isNullOrBlank() -> {
            println("The task is blank")
        }

        else -> {
            strings += firstInput
            strings += "$"
            do {
                val multiTask = scanner.nextLine().trimIndent()
                if (multiTask.isNotEmpty()) {
                    strings += multiTask
                    strings += "$"
                }
            } while (multiTask.isNotEmpty())
            listOfTasks.add(strings)
        }
    }
}

fun print(listOfTasks: MutableList<String>) {
    when {
        listOfTasks.isEmpty() -> {
            println("No tasks have been input")
        }

        else -> {
            for (i in 0 until listOfTasks.size) {
                val list = listOfTasks[i].split("$")
                val firstTask = list[0]
                if (i <= 8) {
                    println("${i + 1}  $firstTask")
                } else {
                    println("${i + 1} $firstTask")
                }
                for (i in 1 until list.size) {
                    println("   ${list[i]}")
                }
                println()
            }
        }
    }
}



