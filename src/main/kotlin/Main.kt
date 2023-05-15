import configuration.DatabaseConnectionConfig
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import repository.model.MapObjectTypeTable
import repository.model.Ship

fun main() {
    DatabaseConnectionConfig.connect

    transaction {
        SchemaUtils.create(MapObjectTypeTable, Ship)
    }

    transaction {
        val mapObjectId = MapObjectTypeTable.insertAndGetId {
            it[biliberda] = "\uD83D\uDE05"
            it[type] = "new_string"
        }

        Ship.insert {
            it[map_object_id] = mapObjectId
            it[position] = "1,3,5"
        }
    }

    transaction {
        print(MapObjectTypeTable.selectAll().count())
    }
}
