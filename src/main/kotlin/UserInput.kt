import java.util.Scanner

class UserInput {
    private val scanner = Scanner(System.`in`)

    fun readString(title: String? = null): String {
        while(true) {
            if (title != null) println(title)

            val input = scanner.nextLine()

            if (input.isEmpty()) {
                println("Ввод не может быть пустым!")
            }
            else return input
        }
    }
}