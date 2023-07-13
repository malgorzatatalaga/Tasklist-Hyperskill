package tasklist

import kotlinx.datetime.*
import kotlinx.datetime.TimeZone
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
                val dueTag = addDueTag(date)
                val task = addTask()
                listOfTaskClass.add(Task(priority, date.toString(), time, dueTag, task))

            }

            "print" -> {
                printListOfTasks(listOfTaskClass)
            }

            "delete" -> {
                deleteTask(listOfTaskClass)
            }

            "edit" -> {
                editTaskClassObject(listOfTaskClass)
            }

            else -> {
                println("The input action is invalid")
            }
        }
    }
}

class Task(var priority: String, var date: String, var time: String, var dueTag: String, var task: String) {
    fun printInfo() {
        println("$date $time $priority $dueTag")
    }

    fun printTasks() {
        val listOfTasks = task.split("$")
        for (singleTask in listOfTasks) {
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
            val localTime = LocalTime(timeList[0].toInt(), timeList[1].toInt())
            time += localTime.toString()
            validTime = true
        } catch (e: Exception) {
            println("The input time is invalid")
        }
    }
    return time
}

fun addDueTag(localDate: LocalDate): String {
    var dueTag = ""
    val currentDate = Clock.System.now().toLocalDateTime(TimeZone.of("UTC+0")).date
    val numberOfDays = currentDate.daysUntil(localDate)
    when {
        numberOfDays == 0 -> dueTag = "T"
        numberOfDays > 0 -> dueTag = "I"
        numberOfDays < 0 -> dueTag = "O"
    }
    return dueTag
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

fun printListOfTasks(listOfTaskClass: MutableList<Task>) {
    if (listOfTaskClass.isEmpty()) {
        println("No tasks have been input")
    } else {
        for ((i, task) in listOfTaskClass.withIndex()) {
            if (i <= 8) {
                print("${i + 1}  ") //2
                task.printInfo()
            } else {
                print("${i + 1} ") //1
                task.printInfo()
            }
            task.printTasks()
        }
    }
}

fun deleteTask(listOfTaskClass: MutableList<Task>) {
    if (listOfTaskClass.isEmpty()) {
        println("No tasks have been input")
    } else {
        println("Input the task number (1-${listOfTaskClass.size}):")
        while (true) {
            when (val taskNumber = readln().toInt()) {
                !in 1..listOfTaskClass.size -> {
                    println("Invalid task number")
                }

                else -> {
                    listOfTaskClass.removeAt(taskNumber - 1)
                    println("The task is deleted")
                    break
                }
            }
        }
    }
}

fun editPriority(taskNumber: Int, listOfTaskClass: MutableList<Task>) {
    val newPriority = inputPriority()
    val oldTask = listOfTaskClass[taskNumber]
    val newTask = Task(newPriority, oldTask.date, oldTask.time, oldTask.dueTag, oldTask.task)
    listOfTaskClass.removeAt(taskNumber)
    listOfTaskClass.add(taskNumber, newTask)
    println("The task is changed")
}

fun editDate(taskNumber: Int, listOfTaskClass: MutableList<Task>) {
    val newDate = inputDate()
    val newDueTag = addDueTag(newDate)
    val oldTask = listOfTaskClass[taskNumber]
    val newTask = Task(oldTask.priority, newDate.toString(), oldTask.time, newDueTag, oldTask.task)
    listOfTaskClass.removeAt(taskNumber)
    listOfTaskClass.add(taskNumber, newTask)
    println("The task is changed")
}

fun editTime(taskNumber: Int, listOfTaskClass: MutableList<Task>) {
    val newTime = inputTime()
    val oldTask = listOfTaskClass[taskNumber]
    val newTask = Task(oldTask.priority, oldTask.date, newTime, oldTask.dueTag, oldTask.task)
    listOfTaskClass.removeAt(taskNumber)
    listOfTaskClass.add(taskNumber, newTask)
    println("The task is changed")
}

fun editTask(taskNumber: Int, listOfTaskClass: MutableList<Task>) {
    val newTask = addTask()
    val oldTask = listOfTaskClass[taskNumber]
    val newTaskClassObject = Task(oldTask.priority, oldTask.date, oldTask.time, oldTask.dueTag, newTask)
    listOfTaskClass.removeAt(taskNumber)
    listOfTaskClass.add(taskNumber, newTaskClassObject)
    println("The task is changed")
}

fun editTaskClassObject(listOfTaskClass: MutableList<Task>) {
    if (listOfTaskClass.isEmpty()) {
        println("No tasks have been input")
    } else {
        println("Input the task number (1-${listOfTaskClass.size}):")
        loop@ while (true) {
            var taskNumber = readln().toInt()
            if (taskNumber !in 1..listOfTaskClass.size) {
                println("Invalid task number")
            } else {
                taskNumber--
                while (true) {
                    println("Input a field to edit (priority, date, time, task):")
                    when (readln()) {
                        "priority" -> {
                            editPriority(taskNumber, listOfTaskClass)
                            break@loop
                        }

                        "date" -> {
                            editDate(taskNumber, listOfTaskClass)
                            break@loop
                        }

                        "time" -> {
                            editTime(taskNumber, listOfTaskClass)
                            break@loop
                        }

                        "task" -> {
                            editTask(taskNumber, listOfTaskClass)
                            break@loop
                        }

                        else -> {
                            println("Invalid field")
                        }
                    }
                }
            }
        }
    }
}
