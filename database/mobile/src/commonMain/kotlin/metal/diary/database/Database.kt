package metal.diary.database

import metal.diary.databsase.AppDatabase

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {

    private val cache = AppDatabase(databaseDriverFactory.createDriver())

    private val queries = cache.appDatabaseQueries

    internal fun getAllNotes(): List<Note> {
        return queries.getAllNotes().executeAsList()
    }
}
