import configuration.DatabaseConnectionConfig
import org.jetbrains.exposed.sql.JoinType
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import repository.model.MapObjectTypeTable
import repository.model.Ship

class MapObjectTableIntegrationTest {

    @Test
    fun shouldFindObject() {
        DatabaseConnectionConfig.connect
        transaction {
            Assertions.assertEquals(1, MapObjectTypeTable.selectAll().count())
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
            Assertions.assertEquals(2, Ship.join(MapObjectTypeTable, JoinType.LEFT).select { Ship.map_object_id eq 2 }.count())
            Assertions.assertEquals(1, Ship.join(MapObjectTypeTable, JoinType.LEFT).selectAll().count())
        }
    }
}
