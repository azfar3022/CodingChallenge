package com.java.test.CodingChallenge.controller;


import com.java.test.CodingChallenge.dto.TranslationRequest;
import com.java.test.CodingChallenge.dto.TranslationResponse;
import com.java.test.CodingChallenge.service.TranslationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/translations")
@RequiredArgsConstructor
public class TranslationController {

    private final TranslationService service;

    @PostMapping
    public ResponseEntity<TranslationResponse> create(@RequestBody TranslationRequest request) {
        return ResponseEntity.ok(service.createTranslation(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TranslationResponse> update(@PathVariable Long id, @RequestBody TranslationRequest request) {
        return ResponseEntity.ok(service.updateTranslation(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TranslationResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTranslation(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteTranslation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<TranslationResponse>> search(@RequestParam(required = false) String key,
                                                             @RequestParam(required = false) String content,
                                                             @RequestParam(required = false) List<String> tags) {
        return ResponseEntity.ok(service.searchTranslations(key, content, tags));
    }

    @GetMapping("/export")
    public ResponseEntity<List<TranslationResponse>> export(@RequestParam String locale) {
        return ResponseEntity.ok(service.exportTranslations(locale));
    }

    @PostMapping("/bulk")
    public ResponseEntity<Void> generateBulk(@RequestParam(defaultValue = "100000") int count) {
        service.generateBulkData(count);
        return ResponseEntity.ok().build();
    }
}
