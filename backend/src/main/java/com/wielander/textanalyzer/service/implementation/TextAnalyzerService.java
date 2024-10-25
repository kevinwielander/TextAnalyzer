package com.wielander.textanalyzer.service.implementation;

import com.wielander.textanalyzer.model.TextAnalysisRequest;
import com.wielander.textanalyzer.service.ITextAnalyzerService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TextAnalyzerService implements ITextAnalyzerService {

    @Override
    public Map<Character, Integer> analyze(TextAnalysisRequest request) {
        if (request.getType().equals("vowels")) {
            return countCharacters(request.getText(), "aeiou");
        } else if (request.getType().equals("consonants")) {
            return countCharacters(request.getText(), "bcdfghjklmnpqrstvwxyz");
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
