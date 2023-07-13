package tasklist

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val listOfTaskClass = mutableListOf<Task>()

    while (true) {
        println("Input an action (add, print, edit, delete, end):")
        when (scanner.nextLine().lowercase().trimIndent()) {
            "end" -> {
                println("Tasklist exiting!")
                break
            }

            "add" -> {
                val priority = inputPriority()
                val date = inputDate()
                val time = inputTime()
                val task = addTask()

            }

            "print" -> {

            }

            "delete" -> {

            }

            "edit" -> {

            }

            else -> {
                println("The input action is invalid")
            }
        }
    }
}

class Task (var priority: String, var date: String, var time: String, var dueTag: String, var task: String) {
    fun printInfo() {
        println("$date $time $priority $dueTag")
    }

    fun printTasks() {
        val listOfTasks = task.split("$")
        for (singleTask in listOfTasks){
            println("   $singleTask")
            println()
        }
    }
}

fun inputPriority(): String {
    var priority: String
    while (true) {
        println("Input the task priority (C, H, N, L):")
        priority = readln().uppercase().trimIndent()
        if (priority == "C" || priority == "H" || priority == "N" || priority == "L") {
            break
        }
    }
    return priority
}

fun inputDate(): LocalDate {
    var date: LocalDate
    while (true) {
        println("Input the date (yyyy-mm-dd):")
        try {
            val dateList = readln().split("-")
            date = LocalDate(dateList[0].toInt(), dateList[1].toInt(), dateList[2].toInt())
            break
        } catch (e: IllegalArgumentException) {
            println("The input date is invalid")
        }
    }
    return date
}

fun inputTime(): String {
    var time = ""
    var validTime = false
    while (!validTime) {
        println("Input the time (hh:mm):")
        try {
            val timeList = readln().split(":")
            if (timeList[0].toInt() > 23 || timeList[0].toInt() < 0 || timeList[1].toInt() > 59 || timeList[0].toInt() < 0) {
                println("The input time is invalid")
            } else {
                time += "${timeList[0]}:${timeList[1]}"
                validTime = true
            }
        } catch (e: Exception) {
            println("The input time is invalid")
        }

    }
    return time
}

fun addTask(): String {
    println("Input a new task (enter a blank line to end):")
    var taskInput = ""
    val firstInput = readln().trimIndent().trimEnd()
    if (firstInput.isBlank()) {
        println("The task is blank")
    } else {
        taskInput += firstInput
        do {
            val singleTask = readln().trimIndent().trimEnd()
            if (singleTask.isBlank()) continue
            taskInput += "$$singleTask"
        } while (singleTask.isNotEmpty())
    }
    return taskInput
}

fun printListOfTasks(listOfTasks: MutableList<String>) {
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

fun deleteTask(listOfTasks: MutableList<String>) {
    if (listOfTasks.isEmpty()) {
        println("No tasks have been input")
    } else {
        println("Input the task number (1-${listOfTasks.size}):")
        while (true) {
            when (val taskNumber = readln().toInt()) {
                !in 1..listOfTasks.size -> {
                    println("Invalid task number")
                }

                else -> {
                    listOfTasks.removeAt(taskNumber - 1)
                    println("The task is deleted")
                    break
                }
            }
        }
    }
}