package com.java.test.CodingChallenge.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(indexes = {
    @Index(name = "idx_locale", columnList = "locale"),
    @Index(name = "idx_translation_key", columnList = "translationKey")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Translation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String translationKey;

    @Column(nullable = false)
    private String locale;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ElementCollection
    @CollectionTable(name = "translation_tags", joinColumns = @JoinColumn(name = "translation_id"))
    @Column(name = "tag")
    private Set<String> tags;
}
