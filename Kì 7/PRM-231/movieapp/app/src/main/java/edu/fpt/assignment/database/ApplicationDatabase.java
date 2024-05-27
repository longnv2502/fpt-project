package edu.fpt.assignment.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import edu.fpt.assignment.database.dao.MovieDAO;
import edu.fpt.assignment.database.dao.MovieWithUserDAO;
import edu.fpt.assignment.database.dao.UserDAO;
import edu.fpt.assignment.domain.MovieWithUserDomain;
import edu.fpt.assignment.domain.MovieInfoDomain;
import edu.fpt.assignment.domain.UserDomain;

@Database(entities = {MovieInfoDomain.class, MovieWithUserDomain.class, UserDomain.class}, version = 2)
public abstract class ApplicationDatabase extends RoomDatabase {

    private static ApplicationDatabase INSTANCE;

    public abstract UserDAO userDAO();

    public abstract MovieDAO movieDAO();

    public abstract MovieWithUserDAO movieWithUserDAO();

    public static ApplicationDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, ApplicationDatabase.class, "MovieApp")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
