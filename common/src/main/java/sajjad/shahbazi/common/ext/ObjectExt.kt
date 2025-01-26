package sajjad.shahbazi.common.ext

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


fun String.readableFormatDate(): String {
    return try {
        val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
        inputFormatter.timeZone = java.util.TimeZone.getTimeZone("UTC")
        val outputFormatter = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.ENGLISH)
        val date = inputFormatter.parse(this) ?: return "Unknown Date"
        outputFormatter.format(date)
    } catch (e: Exception) {
        "Unknown Date"
    }
}



enum class DaysWeek {
    Yesterday, Today
}

fun DaysWeek.getDate(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val calendar = Calendar.getInstance()
    var date = ""
    when (this) {

        DaysWeek.Today -> {
            date = dateFormat.format(calendar.time)
        }

        DaysWeek.Yesterday -> {
            calendar.add(Calendar.DAY_OF_MONTH, -5)
            date = dateFormat.format(calendar.time)
        }
    }
    return date
}