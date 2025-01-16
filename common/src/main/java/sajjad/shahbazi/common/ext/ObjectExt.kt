package sajjad.shahbazi.common.ext

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

enum class DaysWeek {
    Yesterday, Today
}

fun DaysWeek.getDate(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val calendar = Calendar.getInstance()
    var date = ""
    when (this) {

        DaysWeek.Yesterday -> {
            date = dateFormat.format(calendar.time)
        }

        DaysWeek.Today -> {
            calendar.add(Calendar.DAY_OF_MONTH, -1)
            date = dateFormat.format(calendar.time)
        }
    }
    return date
}