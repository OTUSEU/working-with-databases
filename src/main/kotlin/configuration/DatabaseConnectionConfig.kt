package configuration

import org.jetbrains.exposed.sql.Database
// можем получить соединение несколькими способами
object DatabaseConnectionConfig {
    // по хорошему надо бы через laxy by НО МОЖНО И ТАК
    val connect = Database.connect("jdbc:sqlite:./sea-battle.db", "org.sqlite.JDBC")
   // val connectTest = Database.connect("jdbc:sqlite:memory:./sea-battle.db", "org.sqlite.JDBC")  // jdbc:sqlite:memory:myDb

}