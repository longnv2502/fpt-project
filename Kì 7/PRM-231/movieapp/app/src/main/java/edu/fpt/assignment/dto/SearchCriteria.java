package edu.fpt.assignment.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class SearchCriteria {
    private int size;
    private String searchKeyWord;
    private String searchType;
    private String sort;

    public static SearchCriteria of (String searchKeyWord, int size) {
        return new SearchCriteria()
                .setSort("")
                .setSearchType("")
                .setSearchKeyWord(searchKeyWord)
                .setSize(size);
    }
}
