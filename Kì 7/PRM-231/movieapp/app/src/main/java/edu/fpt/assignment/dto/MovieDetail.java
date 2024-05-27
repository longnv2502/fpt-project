package edu.fpt.assignment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import edu.fpt.assignment.enums.DefinitionCode;
import edu.fpt.assignment.enums.DramaType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MovieDetail {
    private String aliasName;
    private List<Area> areaList;
    private List<String> areaNameList;
    private int category;
    private boolean collect;
    private List<?> contentTagResourceList;
    private String copyrightType;
    private String coverHorizontalUrl;
    private String coverHorizontalUrlJson;
    private String coverVerticalUrl;

    @JsonProperty("drameTypeVo")
    private DramaTypeVo dramaTypeVo;
    private Integer episodeCount;
    private EpisodeRoomListVo episodeRoomListVo;
    private List<EpisodeVo> episodeVo;
    private String id;
    private String introduction;

    @JsonProperty("likeList")
    private List<MovieSimilar> movieSimilarList;
    private String name;

    @JsonProperty("nameJson")
    private String nameJSON;
    private List<MovieRef> refList;
    private boolean reserved;
    private double score;
    private int seriesNo;
    private boolean showSetName;

    @JsonProperty("starList")
    private List<MovieStar> movieStarList;

    private List<MovieTag> tagList;
    private List<String> tagNameList;
    private int translateType;
    private MovieUpInfo upInfo;
    private Object updateInfo;
    private int year;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Area {
        private int id;
        private String name;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DramaTypeVo {
        @JsonProperty("drameName")
        private String dramaName;

        @JsonProperty("drameType")
        private DramaType dramaType;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Tag {
        private int id;
        private String name;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EpisodeRoomListVo {
        private int category;
        private String episodeId;
        private String episodeName;
        private int number;
        private String roomId;
        private String seasonID;
        private String seasonName;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EpisodeVo {
        private List<Definition> definitionList;
        private int id;
        private String name;
        private String nameJson;
        private int resourceType;
        private int seriesNo;
        private boolean showAppLabel;
        private List<Subtitling> subtitlingList;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MovieSimilar {
        private List<Area> areaList;
        private List<String> areaNameList;
        private int category;
        private String coverHorizontalUrl;
        private String coverVerticalUrl;

        @JsonProperty("drameTypeVo")
        private DramaTypeVo dramaTypeVo;

        private String id;
        private String name;
        private Object resourceNum;
        private int resourceStatus;
        private double score;
        private List<Tag> tagList;
        private List<String> tagNameList;
        private String upImgUrl;
        private String upName;
        private int year;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Definition {
        private DefinitionCode code;
        private String description;
        private String fullDescription;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Subtitling implements Serializable {
        private String language;
        private String languageAbbr;
        private String subtitlingUrl;
        private int translateType;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MovieRef {
        private int category;
        private String coverHorizontalUrl;
        private String coverVerticalUrl;

        @JsonProperty("drameTypeVo")
        private DramaTypeVo dramaTypeVo;

        private String id;
        private String name;
        private int seriesNo;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MovieTag {
        private int id;
        private String name;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MovieUpInfo {
        private int upId;
        private String upImgUrl;
        private String upName;
    }

}
