package com.wielander.textanalyzer.service.implementation;

import com.wielander.textanalyzer.exception.GlobalExceptionHandler;
import com.wielander.textanalyzer.model.TextAnalysisRequest;
import com.wielander.textanalyzer.service.ITextAnalyzerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TextAnalyzerService implements ITextAnalyzerService {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Override
    public Map<Character, Integer> analyze(TextAnalysisRequest request) {
        logger.trace("Analyzing text for type: {}", request.getType());
        if (request.getType().equals("vowels")) {
            String vowelString = "aeiou";
            return countCharacters(request.getText(), vowelString);
        } else if (request.getType().equals("consonants")) {
            String consonantString = "bcdfghjklmnpqrstvwxyz";
            return countCharacters(request.getText(), consonantString);
        }
        return new HashMap<>();
    }

    private Map<Character, Integer> countCharacters(String text, String charactersToCount) {
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : text.toLowerCase().toCharArray()) {
            if (charactersToCount.indexOf(c) >= 0) {
                charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            }
        }
        return charCount;
    }
}
