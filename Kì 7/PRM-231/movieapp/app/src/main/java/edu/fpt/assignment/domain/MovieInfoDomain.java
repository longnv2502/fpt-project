package edu.fpt.assignment.domain;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Locale;

import edu.fpt.assignment.dto.HomeSection;
import edu.fpt.assignment.dto.MovieDetail;
import edu.fpt.assignment.dto.SearchResult;
import edu.fpt.assignment.utils.RegexUntils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@Accessors(chain = true)
@Entity(tableName = "movies")
public class MovieInfoDomain implements Serializable {
    @PrimaryKey
    private int id;
    private String category;
    private String title;
    private double score;

    @ColumnInfo(name = "image_url")
    private String imageUrl;

    @ColumnInfo(name = "release_time")
    private String releaseTime;

    @ColumnInfo(name = "jump_address")
    private String jumpAddress;

    @ColumnInfo(name = "description")
    private String description;

    @Ignore
    public MovieInfoDomain() {
    }

    public static MovieInfoDomain of(MovieDetail movieDetail) {
        String jumpAddress = String.format(Locale.getDefault(), "tiktik://jump/detail?id=%s&type=%d", movieDetail.getId(), movieDetail.getCategory());
        return new MovieInfoDomain()
                .setId(Integer.parseInt(movieDetail.getId()))
                .setCategory(String.valueOf(movieDetail.getCategory()))
                .setTitle(movieDetail.getName())
                .setScore(movieDetail.getScore())
                .setImageUrl(movieDetail.getCoverVerticalUrl())
                .setReleaseTime(String.valueOf(movieDetail.getYear()))
                .setJumpAddress(jumpAddress)
                .setDescription(movieDetail.getIntroduction());
    }

    public RegexUntils.SplitJumpAddress getSplitJumpAddress() {
        return RegexUntils.splitJumpAddress(jumpAddress);
    }

    public static MovieInfoDomain of(HomeSection.MovieInfo movieInfo) {
        return new MovieInfoDomain()
                .setId(movieInfo.getId())
                .setCategory(movieInfo.getCategory())
                .setTitle(movieInfo.getTitle())
                .setScore(movieInfo.getScore())
                .setImageUrl(movieInfo.getImageUrl())
                .setReleaseTime(movieInfo.getReleaseTime())
                .setJumpAddress(movieInfo.getJumpAddress());
    }

    public static MovieInfoDomain of(MovieDetail.MovieSimilar movieSimilar) {
        String jumpAddress = String.format(Locale.getDefault(), "tiktik://jump/detail?id=%s&type=%d", movieSimilar.getId(), movieSimilar.getCategory());
        return new MovieInfoDomain()
                .setId(Integer.parseInt(movieSimilar.getId()))
                .setCategory(String.valueOf(movieSimilar.getCategory()))
                .setTitle(movieSimilar.getName())
                .setScore(movieSimilar.getScore())
                .setImageUrl(movieSimilar.getCoverVerticalUrl())
                .setReleaseTime(String.valueOf(movieSimilar.getYear()))
                .setJumpAddress(jumpAddress);
    }

    public static MovieInfoDomain of(SearchResult searchResult) {
        String jumpAddress = String.format(Locale.getDefault(), "tiktik://jump/detail?id=%s&type=%d", searchResult.getId(), searchResult.getDomainType());
        return new MovieInfoDomain()
                .setId(Integer.parseInt(searchResult.getId()))
                .setCategory(String.valueOf(searchResult.getDomainType()))
                .setTitle(searchResult.getName())
                .setScore(9.0)
                .setImageUrl(searchResult.getCoverVerticalUrl())
                .setReleaseTime(searchResult.getReleaseTime())
                .setJumpAddress(jumpAddress);
    }
}
