package edu.fpt.assignment.utils;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class RegexUntils {
    private static final String PATTERN_JUMP_ADDDRESS = "id=([\\d]+)&type=([\\d]+)";

    public static SplitJumpAddress splitJumpAddress(String address) {
        Pattern pattern = Pattern.compile(PATTERN_JUMP_ADDDRESS);
        Matcher matcher = pattern.matcher(address);
        if (matcher.find()) {
            return new SplitJumpAddress(Integer.parseInt(Objects.requireNonNull(matcher.group(1))), Integer.parseInt(Objects.requireNonNull(matcher.group(2))));
        }
        return null;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SplitJumpAddress implements Serializable {
        private int id;
        private int category;
    }
}
