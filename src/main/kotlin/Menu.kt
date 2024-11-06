class Menu(private val notes: Notes) {
    private val userInput = UserInput()
    
    fun run(txt: String, level: Int) {
        val menuItems = createOptions(level)

        println("Список $txt:")
        menuItems.forEachIndexed { index, pair ->
            println("${index + 1}. ${pair.first}")
        }

        while(true) {
            val input = userInput.readString("Выберите пункт меню...").toIntOrNull()
            if (input != null && input in 1..menuItems.size) {
                println("Выбран пункт `${input}. ${menuItems[input-1].first}`")
                menuItems[input-1].second.invoke()
                break
            }
            else {
                println("Введите номер одного из пунктов!")
            }
        }
    }

    private fun createOptions(level: Int): List<Pair<String, () -> Unit>> {
        val options = mutableListOf<Pair<String, () -> Unit>>()

        if (level == 1) {
            options.add("Создать архив" to { notes.createItem(true) })
            notes.getArchives().forEach { archive ->
                options.add(archive.title to { notes.selectItem(archive, null) })
            }
            options.add("Выход" to { notes.goOut() })
        }
        else {
            options.add("Создать заметку" to { notes.createItem(false) })
            notes.getCurrentArchive()?.notes?.forEach { note ->
                options.add(note.title to { notes.selectItem(null, note) })
            }
            options.add("Назад" to { notes.goOut() })
        }

        return options
    }
}