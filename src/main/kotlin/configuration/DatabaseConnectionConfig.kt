package configuration

import org.jetbrains.exposed.sql.Database

object DatabaseConnectionConfig {
    val connect = Database.connect("jdbc:sqlite:./sea-battle.db", "org.sqlite.JDBC")

}