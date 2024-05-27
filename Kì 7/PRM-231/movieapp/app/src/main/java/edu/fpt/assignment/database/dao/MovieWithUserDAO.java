package edu.fpt.assignment.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Update;

import java.util.List;

import edu.fpt.assignment.domain.MovieWithUserDomain;

@Dao
public interface MovieWithUserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(MovieWithUserDomain... movieWithUserDomains); // When insert id of MovieContinueDomain must null because id autoGenerate=true

    @Update
    void updateAll(MovieWithUserDomain... movieWithUserDomains);

    @Delete
    void delete(MovieWithUserDomain movieWithUserDomain);

    @Query("SELECT * FROM `movie_users`")
    List<MovieWithUserDomain> getAll();

    @Query("SELECT * FROM `movie_users` WHERE user_id LIKE :userID AND movie_id LIKE :movieID")
    MovieWithUserDomain getByUserIDAndMovieId(String userID, String movieID);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT * FROM movie_users " +
            "INNER JOIN users ON users.id = movie_users.user_id " +
            "INNER JOIN movies on movies.id = movie_users.movie_id " +
            "WHERE users.id LIKE :userID"
    )
    LiveData<List<MovieWithUserDomain>> findAllWithUserID(String userID);
}
