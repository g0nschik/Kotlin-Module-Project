data class Note(val title: String, val content: String)
data class Archive(val title: String, val notes: MutableList<Note> = mutableListOf())

class Notes {
    private val archives = mutableListOf<Archive>()

    private var level: Int = 1
    private var currentArchive: Archive? = null

    private val userInput = UserInput()
    private val menu = Menu(this)

    private var exit: Boolean = false

    fun start() {
        while (!exit) {
            val txt = if (level == 1) "архивов" else "заметок в архиве `${currentArchive?.title}`"
            menu.run(txt, level)
        }

        println("До встречи!")
    }

    fun createItem(isArchive: Boolean) {
        val title = userInput.readString("Введите название:")

        if (isArchive) {
            archives.add(Archive(title))
            println("Архив '$title' создан")
        }
        else {
            val content = userInput.readString("Введите содержание заметки:")
            currentArchive?.notes?.add(Note(title, content))
            println("Заметка '$title' в архиве `${currentArchive?.title}` создана")
        }
    }

    fun selectItem(archive: Archive?, note: Note?) {
        if (note != null) {
            println("Просмотр заметки: '${note.title}'")
            println("Содержимое:\n${note.content}\n")
        }
        else if (archive != null) {
            currentArchive = archive
            level = 2
        }
    }

    fun goOut() {
        if (level == 1) {
            exit = true
        }
        else {
            currentArchive = null
            level = 1
        }
    }

    fun getArchives(): List<Archive> {
        return archives
    }

    fun getCurrentArchive(): Archive? {
        return currentArchive
    }
}

fun main() {
    val notes = Notes()
    notes.start()
}