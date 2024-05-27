package edu.fpt.assignment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import edu.fpt.assignment.domain.MovieInfoDomain;
import edu.fpt.assignment.enums.AdapterType;
import edu.fpt.assignment.enums.ContentType;
import edu.fpt.assignment.enums.DramaType;
import edu.fpt.assignment.enums.HomeSectionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class HomeSection {
    private Double bannerProportion;
    private int blockGroupNum;
    private String category;
    private int coverType;
    private int homeSectionId;
    private String homeSectionName;
    private HomeSectionType homeSectionType;
    private String recommendAutoShowMark;
    private List<MovieInfo> recommendContentVOList;
    private int refId;
    private String refRedirectUrl;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MovieInfo {
        private String category;
        private ContentType contentType;
        private DramaTypeVo dramaTypeVo;
        private int id;
        private String imageUrl;
        private String jumpAddress;
        private String jumpType;
        private boolean needLogin;
        private String releaseTime;
        private String resourceNum;
        private String resourceStatus;
        private double score;
        private boolean showMark;
        private String title;
        private boolean titleCenter;
        private String videoName;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DramaTypeVo {
        private String dramaName;
        private DramaType dramaType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HomeSection that = (HomeSection) o;
        return homeSectionId == that.homeSectionId && Objects.equals(category, that.category) && Objects.equals(homeSectionName, that.homeSectionName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, homeSectionId, homeSectionName);
    }

    public List<MovieInfoDomain> getMovieInfoDomains() {
        return recommendContentVOList.stream()
                .map(MovieInfoDomain::of)
                .collect(Collectors.toList());
    }

    public AdapterType getAdapterType() {
        if (bannerProportion != null && bannerProportion == 1.0) {
            if (recommendContentVOList.get(0).contentType == ContentType.APP_URL) {
                return AdapterType.ActorImage;
            } else {
                return AdapterType.MovieSlider;
            }
        } else if (bannerProportion != null && bannerProportion == 2.0) {
            return AdapterType.MovieImageHorizontal;
        } else if (homeSectionName.equals("Trending Now")) {
            return AdapterType.MovieDetail;
        } else {
            return AdapterType.MovieImageVertical;
        }
    }


}
