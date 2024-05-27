package edu.fpt.assignment.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import edu.fpt.assignment.domain.MovieInfoDomain;
import edu.fpt.assignment.domain.UserDomain;

@Dao
public interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(UserDomain... userDomains);

    @Update
    void updateAll(UserDomain... userDomains);

    @Delete
    void delete(UserDomain userDomain);

    @Query("SELECT * FROM users " +
            "WHERE users.email LIKE :email AND users.password LIKE :password")
    UserDomain getByEmailAndPassword(String email, String password);

    @Query("SELECT EXISTS(SELECT * FROM users WHERE users.email LIKE :email)")
    boolean isExistsEmail(String email);

    @Query("SELECT * FROM users")
    List<UserDomain> getAll();
}
