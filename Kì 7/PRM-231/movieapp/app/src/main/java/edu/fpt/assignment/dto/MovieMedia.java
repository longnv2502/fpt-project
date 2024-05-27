package edu.fpt.assignment.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import edu.fpt.assignment.enums.DefinitionCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MovieMedia implements Serializable {
    private int businessType;
    private DefinitionCode currentDefinition;
    private String episodeId;
    private String mediaUrl;
    private int totalDuration;
}
