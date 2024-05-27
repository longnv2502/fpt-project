package edu.fpt.assignment.domain;

import edu.fpt.assignment.dto.HomeSection;
import edu.fpt.assignment.dto.MovieDetail;
import edu.fpt.assignment.dto.MovieStar;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MovieStarDomain {
    private String imageUrl;
    private String name;
    private int starId;

    public static MovieStarDomain of (MovieInfoDomain movieInfo){
        return new MovieStarDomain()
                .setStarId(movieInfo.getId())
                .setName(movieInfo.getTitle())
                .setImageUrl(movieInfo.getImageUrl());
    }

    public static MovieStarDomain of (MovieStar movieStar){
        return new MovieStarDomain()
                .setStarId(movieStar.getStarId())
                .setName(movieStar.getLocalName())
                .setImageUrl(movieStar.getImage());
    }

}
