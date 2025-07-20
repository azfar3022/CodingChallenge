package com.java.test.CodingChallenge.service.impl;

import com.java.test.CodingChallenge.dto.TranslationRequest;
import com.java.test.CodingChallenge.dto.TranslationResponse;
import com.java.test.CodingChallenge.model.Translation;
import com.java.test.CodingChallenge.repository.TranslationRepository;
import com.java.test.CodingChallenge.service.TranslationService;
import jakarta.persistence.Access;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class TranslationServiceImpl implements TranslationService {

    @Autowired
    private TranslationRepository repository;

    private TranslationResponse mapToDto(Translation t) {
        TranslationResponse translationResponse = new TranslationResponse();
        translationResponse.setId(t.getId());
        translationResponse.setTranslationKey(t.getTranslationKey());
        translationResponse.setLocale(t.getLocale());
        translationResponse.setContent(t.getContent());
        translationResponse.setTags(t.getTags());
        return translationResponse;
    }

    @Override
    public TranslationResponse createTranslation(TranslationRequest request) {
        Translation t = Translation.builder()
                .translationKey(request.getTranslationKey())
                .locale(request.getLocale())
                .content(request.getContent())
                .tags(request.getTags())
                .build();
        return mapToDto(repository.save(t));
    }

    @Override
    public TranslationResponse updateTranslation(Long id, TranslationRequest request) {
        Translation t = repository.findById(id).orElseThrow();
        t.setTranslationKey(request.getTranslationKey());
        t.setLocale(request.getLocale());
        t.setContent(request.getContent());
        t.setTags(request.getTags());
        return mapToDto(repository.save(t));
    }

    @Override
    public void deleteTranslation(Long id) {
        repository.deleteById(id);
    }

    @Override
    public TranslationResponse getTranslation(Long id) {
        return mapToDto(repository.findById(id).orElseThrow());
    }

    @Override
    public List<TranslationResponse> searchTranslations(String key, String content, List<String> tags) {
        if (key != null) return repository.findByTranslationKeyContainingIgnoreCase(key).stream().map(this::mapToDto).toList();
        if (content != null) return repository.findByContentContainingIgnoreCase(content).stream().map(this::mapToDto).toList();
        if (tags != null && !tags.isEmpty()) return repository.findByTagsIn(tags).stream().map(this::mapToDto).toList();
        return List.of();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TranslationResponse> exportTranslations(String locale) {
        return repository.findByLocale(locale).stream().map(this::mapToDto).toList();
    }

    @Override
    public void generateBulkData(int count) {
        List<Translation> bulk = IntStream.range(0, count)
                .mapToObj(i -> Translation.builder()
                        .translationKey("key" + i)
                        .locale(i % 2 == 0 ? "en" : "fr")
                        .content("Sample content " + i)
                        .tags(Set.of("web"))
                        .build())
                .collect(Collectors.toList());
        repository.saveAll(bulk);
    }
}
