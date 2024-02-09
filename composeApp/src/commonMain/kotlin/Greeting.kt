import java.util.Date
import java.text.SimpleDateFormat
import java.time.chrono.SouthAmericanDate

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        val date = Date()
        val format = SimpleDateFormat("dd-MM-yyyy")
        val gregoriandate = format.format(date)
        val casuldate = SouthAmericanDate()

        //return "Hello, ${platform.name}!"
        return "Olá. A data no calendário gregoriano é ${gregoriandate}.\n" +
                "A data no calendário sulamericano é ${casuldate}."
        }
    }