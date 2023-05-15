import configuration.DatabaseConnectionConfig
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import repository.model.MapObjectTypeTable
import repository.model.Ship

// Создание и заполнение БД
fun main() {
    // присоединиться к бахе данных - инициирование
    DatabaseConnectionConfig.connect
    // все выполнять в transaction
    // Создание простейшей таблицы:
/*    transaction {
        // прежде чем мы создадим таблицу надо ее описать в MapObjectTypeTable
        SchemaUtils.create(MapObjectTypeTable)
    }
    transaction {
        MapObjectTypeTable.insert {
            // это доступ к переменной - безопасное присвоение
           // it[MapObjectTypeTable.symbol] = "\uD830\uDE85"
           // it[symbol] = "\uD830\uDE85"
            it[biliberda] = "😅"
            it[type] = "new_string"
        }
    }
*/

    transaction {
        // прежде чем мы создадим таблицы надо ее описать в MapObjectTypeTable
        SchemaUtils.create(MapObjectTypeTable, Ship)
    }

    transaction {
        val mapObjectId = MapObjectTypeTable.insertAndGetId {
            it[biliberda] = "😅"
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
