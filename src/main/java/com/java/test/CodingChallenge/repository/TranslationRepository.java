package com.java.test.CodingChallenge.repository;

import com.java.test.CodingChallenge.model.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TranslationRepository extends JpaRepository<Translation, Long> {
    List<Translation> findByLocale(String locale);
    List<Translation> findByTranslationKeyContainingIgnoreCase(String key);
    List<Translation> findByContentContainingIgnoreCase(String content);
    List<Translation> findByTagsIn(List<String> tags);

}
