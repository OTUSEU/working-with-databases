package repository.model

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object MapObjectTypeTable : IntIdTable("map_object_type") {
//    val id: Column<Int> = integer("id").uniqueIndex().autoIncrement()
    val type: Column<String> = varchar("my_super_type", 10)
    val biliberda: Column<String> = varchar("my_symbol", 10)

//    override val primaryKey: PrimaryKey = PrimaryKey(id)
}


object Ship : IntIdTable("ship") {
    //    val id: Column<Int> = integer("id").uniqueIndex().autoIncrement()
    val position: Column<String> = varchar("position", 10)
    val map_object_id = reference("map_object_id", MapObjectTypeTable)

}
