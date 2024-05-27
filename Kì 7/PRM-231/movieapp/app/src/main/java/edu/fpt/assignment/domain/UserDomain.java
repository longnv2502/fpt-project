package edu.fpt.assignment.domain;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
@Entity(tableName = "users",
        indices = {@Index(value = {"email"}, unique = true)})
public class UserDomain implements Serializable {
    @PrimaryKey
    @NonNull
    private String id;
    private String email;
    private String password;
    private String name;

    @Ignore
    public UserDomain() {
    }

    public static String generateID() {
        return UUID.randomUUID().toString();
    }
}
