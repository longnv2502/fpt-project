package edu.fpt.assignment.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
@Entity(tableName = "movie_users",
        foreignKeys = {
                @ForeignKey(entity = UserDomain.class,
                        parentColumns = "id",
                        childColumns = "user_id",
                        onDelete = ForeignKey.CASCADE),

                @ForeignKey(entity = MovieInfoDomain.class,
                        parentColumns = "id",
                        childColumns = "movie_id",
                        onDelete = ForeignKey.CASCADE)
        })
public class MovieWithUserDomain {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "time_update")
    private long timeUpdate;

    @ColumnInfo(name = "user_id", index = true)
    private String userID;

    @ColumnInfo(name = "movie_id", index = true)
    private String movieID;

    @ColumnInfo(name = "type")
    private String type;

    @Ignore
    public MovieWithUserDomain() {
    }
}
