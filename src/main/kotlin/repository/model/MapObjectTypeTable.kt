package repository.model

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

// map_object_type" - название таблицы - прописывать вручную (Reflecsions)
// Два подхода или class Лучше Object - статический

// соответственно эти поля val - не хпанят значения, а хранят просто описание колонки
// С первой строкой с IntIdTable можно не задавать val id:
object MapObjectTypeTable : IntIdTable("map_object_type") { // окончательный вариант
//object MapObjectTypeTable : Table("map_object_type") {
    // теперь описываем ее поля

//    val id: Column<Int> = integer("id").uniqueIndex().autoIncrement()  // стоит в комментах не нужно при IntIdTable
    val type: Column<String> = varchar("my_super_type", 10)
    //val symbol: Column<String> = varchar("symbol", 10)
    //val symbol: Column<String> = varchar("my_symbol", 10)
    val biliberda: Column<String> = varchar("my_symbol", 10)

//    override val primaryKey: PrimaryKey = PrimaryKey(id)  // стоит в комментах не нужно при IntIdTable
}
// окончательный вариант

object Ship : IntIdTable("ship") {
    //    val id: Column<Int> = integer("id").uniqueIndex().autoIncrement()
    val position: Column<String> = varchar("position", 10)
    // для Table необходимо указывать колонку
    //val map_object_id = reference("map_object_id", MapObjectTypeTable.type)
    // для IntIdTable сам возьмет id
    val map_object_id = reference("map_object_id", MapObjectTypeTable)

}
