package examen2.examen2.examen2.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import examen2.examen2.examen2.constants.Constants

@Entity(tableName = Constants.TABLE_NAME_OFFLINE_DATA)
data class OfflineData (
    @ColumnInfo(name = "idmovie")
    val idmovie: String? = null,
    @ColumnInfo(name = "original_title")
    val original_title: String? = null,
    @ColumnInfo(name = "overview")
    val overview: String? = null,
    @ColumnInfo(name = "release_date")
    val release_date: String? = null,
    @ColumnInfo(name = "vote_average")
    var vote_average: String? = null){
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    var id: Int? = null
}