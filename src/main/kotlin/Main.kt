package tasklist

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val listOfTasks = mutableListOf<String>()

    while (true) {
        println("Input an action (add, print, edit, delete, end):")
        when (scanner.nextLine().lowercase().trimIndent()) {
            "add" -> {
                val priority = inputPriorityAndDateTime()
                println("Input a new task (enter a blank line to end):")
                addTask(listOfTasks, priority)
            }

            "print" -> printListOfTasks(listOfTasks)


            "delete" -> deleteTask(listOfTasks)


            "end" -> {
                println("Tasklist exiting!")
                break
            }

            else -> println("The input action is invalid")

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

fun inputDate(): String {
    var date = ""
    var validDate = false
    while (!validDate) {
        println("Input the date (yyyy-mm-dd):")
        try {
            val dateList = readln().split("-")
            val localDate = LocalDate(dateList[0].toInt(), dateList[1].toInt(), dateList[2].toInt())
            date += localDate.toString().trimIndent()
            validDate = true
        } catch (e: IllegalArgumentException) {
            println("The input date is invalid")
        }
    }
    return date
}

fun inputTime(): String {
    var time = ""
    while (true) {
        println("Input the time (hh:mm):")
        try {
            val timeList = readln().split(":")
            val localTime = LocalTime(timeList[0].toInt(), timeList[1].toInt())
            time += "${localTime.hour}:${localTime.minute}"
            break
        } catch (e: IllegalArgumentException) {
            println("The input time is invalid")
        } catch (e: NumberFormatException) {
            println("The input time is invalid")
        }
    }
    return time
}

fun inputPriorityAndDateTime(): String {
    val priority = inputPriority()
    val date = inputDate()
    val time = inputTime()

    return "$date $time $priority"
}

fun addTask(listOfTasks: MutableList<String>, priorityAndDateTime: String) {
    var strings = ""
    val scanner = Scanner(System.`in`)
    strings += "$priorityAndDateTime$"

    val firstInput = scanner.nextLine().trimIndent()
    when {
        firstInput.isBlank() -> {
            println("The task is blank")
        }

        else -> {
            strings += "$firstInput$"
            do {
                val multiTask = scanner.nextLine().trimIndent()
                if (multiTask.isNotEmpty()) {
                    strings += "$multiTask$"
                }
            } while (multiTask.isNotEmpty())
            listOfTasks.add(strings)
        }
    }
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