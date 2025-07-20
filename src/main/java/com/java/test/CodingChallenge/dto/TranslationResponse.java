package com.java.test.CodingChallenge.dto;

import lombok.Data;
import java.util.Set;

@Data
public class TranslationResponse {
    private Long id;
    private String translationKey;
    private String locale;
    private String content;
    private Set<String> tags;
}
