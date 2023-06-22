package tasklist

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val listOfTasks = mutableListOf<String>()
    var end = false

    while (!end) {
        println("Input an action (add, print, end):")
        when (scanner.nextLine().lowercase()) {
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
    do {
        val task = scanner.nextLine().trimIndent()
        if (!task.isNullOrEmpty()) {
            listOfTasks.add(task)
        }
    } while (!task.isNullOrEmpty())
}

fun print(listOfTasks: MutableList<String>) {
    if (listOfTasks.isEmpty()) {
        println("No tasks have been input.")
    } else {
        for (i in 0 until listOfTasks.size) {
            if (i > 8) {
                val result = "${i + 1} ${listOfTasks[i]}"
                println(result)
            } else {
                val result = "${i + 1}  ${listOfTasks[i]}"
                println(result)
            }

        }
    }
}


