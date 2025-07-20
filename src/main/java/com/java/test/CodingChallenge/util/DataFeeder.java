package com.java.test.CodingChallenge.util;

import com.java.test.CodingChallenge.model.Translation;
import com.java.test.CodingChallenge.repository.TranslationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class DataFeeder implements CommandLineRunner {

    private final TranslationRepository repository;

    @Override
    public void run(String... args) {
        if (repository.count() >= 100_000) {
            System.out.println("Data already seeded.");
            return;
        }

        System.out.println("Seeding 100,000+ translation records...");
        List<Translation> translations = new ArrayList<>();

        IntStream.range(0, 100_000).forEach(i -> {
            translations.add(Translation.builder()
                    .translationKey("key_" + i)
                    .locale(i % 2 == 0 ? "en" : "fr")
                    .content("Sample content " + i)
                    .tags(Set.of(i % 2 == 0 ? "web" : "mobile"))
                    .build());

            // Save in batches of 1000
            if (translations.size() == 1000) {
                repository.saveAll(translations);
                translations.clear();
            }
        });

        // Save any remaining records
        if (!translations.isEmpty()) {
            repository.saveAll(translations);
        }

        System.out.println("Data seeding complete.");
    }
}
