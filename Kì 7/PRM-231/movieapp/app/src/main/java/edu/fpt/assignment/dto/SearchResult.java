package edu.fpt.assignment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SearchResult {
    public List<Area> areas;
    public List<CategoryTag> categoryTag;
    public String coverHorizontalUrl;
    public String coverVerticalUrl;
    public Integer domainType;
    public DramaType dramaType;
    public String duration;
    public String id;
    public String name;
    public String releaseTime;
    public Object resourceNum;
    public Object resourceStatus;
    public String sort;
    public UpInfo upInfo;


    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class CategoryTag {
        public Integer id;
        public String name;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Area {
        public Integer id;
        public String name;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class UpInfo {
        public Boolean enable;
        public Integer upId;
        public String upImgUrl;
        public String upName;
        public Object userId;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class DramaType {
        public String code;
        public String name;
    }
}
