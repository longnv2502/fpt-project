package edu.fpt.assignment.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.fpt.assignment.domain.MovieInfoDomain;

@Dao
public interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(MovieInfoDomain... movieInfoDomains);

    @Update
    void updateAll(MovieInfoDomain... movieInfoDomains);

    @Delete
    void delete(MovieInfoDomain movieInfoDomain);

    @Query("SELECT * FROM movies")
    List<MovieInfoDomain> getAll();

    @Query("SELECT * FROM movies " +
            "INNER JOIN movie_users on movies.id LIKE movie_id " +
            "WHERE movie_users.user_id LIKE :userId")
    List<MovieInfoDomain> getAllWithUserId(String userId);
}
