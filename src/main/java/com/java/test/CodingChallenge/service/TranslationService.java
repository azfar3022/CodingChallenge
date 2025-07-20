package com.java.test.CodingChallenge.service;


import com.java.test.CodingChallenge.dto.TranslationRequest;
import com.java.test.CodingChallenge.dto.TranslationResponse;

import java.util.List;

public interface TranslationService {
    TranslationResponse createTranslation(TranslationRequest request);
    TranslationResponse updateTranslation(Long id, TranslationRequest request);
    void deleteTranslation(Long id);
    TranslationResponse getTranslation(Long id);
    List<TranslationResponse> searchTranslations(String key, String content, List<String> tags);
    List<TranslationResponse> exportTranslations(String locale);
    void generateBulkData(int count);
}
