package com.wielander.textanalyzer.service;

import com.wielander.textanalyzer.model.TextAnalysisRequest;
import com.wielander.textanalyzer.service.implementation.TextAnalyzerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TextAnalyzerServiceTest {
    private TextAnalyzerService textAnalyzerService;

    @BeforeEach
    void setUp() {
        textAnalyzerService = new TextAnalyzerService();
    }

    @Test
    void testAnalyzeVowels() {
        TextAnalysisRequest request = new TextAnalysisRequest("vowels", "Hello World, this is a test. I hope you enjoy it.");
        Map<Character, Integer> result = textAnalyzerService.analyze(request);

        assertEquals(5, result.size());
        assertEquals(1, result.get('a'));
        assertEquals(4, result.get('e'));
        assertEquals(4, result.get('i'));
        assertEquals(5, result.get('o'));
        assertEquals(1, result.get('u'));
    }

    @Test
    void testAnalyzeConsonants() {
        TextAnalysisRequest request = new TextAnalysisRequest("consonants", "Hello World");
        Map<Character, Integer> result = textAnalyzerService.analyze(request);

        assertEquals(5, result.size());
        assertEquals(1, result.get('h'));
        assertEquals(3, result.get('l'));
        assertEquals(1, result.get('w'));
        assertEquals(1, result.get('r'));
        assertEquals(1, result.get('d'));
    }

    @Test
    void testAnalyzeEmptyText() {
        TextAnalysisRequest request = new TextAnalysisRequest("vowels", "");
        Map<Character, Integer> result = textAnalyzerService.analyze(request);

        assertTrue(result.isEmpty());
    }

    @Test
    void testAnalyzeInvalidType() {
        TextAnalysisRequest request = new TextAnalysisRequest("invalid", "Hello World");
        Map<Character, Integer> result = textAnalyzerService.analyze(request);

        assertTrue(result.isEmpty());
    }
}
