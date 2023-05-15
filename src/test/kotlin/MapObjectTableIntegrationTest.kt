import configuration.DatabaseConnectionConfig
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import repository.model.MapObjectTypeTable
import repository.model.Ship

//import repository.model.Ship
// Интеграционный тест
class MapObjectTableIntegrationTest {
   /* companion object {
    @BeforeAll
    fun init() {
        DatabaseConnectionConfig.connect
        }
    }*/

    @Test
    fun shouldFindObject() {
        DatabaseConnectionConfig.connect
        transaction {
            Assertions.assertEquals(2, MapObjectTypeTable.selectAll().count())
        }

        transaction {
            val foundRaw = MapObjectTypeTable.select { MapObjectTypeTable.type eq "new_string" }.first()

            Assertions.assertEquals("😅", foundRaw[MapObjectTypeTable.biliberda])
        }
    }

    @Test
    fun shouldJoin() {
        DatabaseConnectionConfig.connect
        transaction {
            Ship.join(MapObjectTypeTable, JoinType.LEFT).selectAll().forEach{
                println(it)
            }
            Assertions.assertEquals(1, Ship.join(MapObjectTypeTable, JoinType.LEFT).select { Ship.map_object_id eq 2 }.count())
            // добавил руками ущу запись - у него ULTIMATE
            Assertions.assertEquals(1, Ship.join(MapObjectTypeTable, JoinType.LEFT).selectAll().count())
        }
    }


}
