package tasklist

import java.util.*

fun main() {
    println("Input an action (add, print, end):")
    val scanner = Scanner(System.`in`)
    val listOfTasks = mutableListOf<String>()

    when (scanner.nextLine()) {
        "End" -> println("Tasklist exiting!")
        "Add" -> {
            println("Input a new task (enter a blank line to end):")
            input(listOfTasks)
        }
        "Print" -> print(listOfTasks)
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
            if (i > 8){
                val result = "${i+1} ${listOfTasks[i]}"
                println(result)
            } else {
                val result = "${i+1}  ${listOfTasks[i]}"
                println(result)
            }

        }
    }
}


