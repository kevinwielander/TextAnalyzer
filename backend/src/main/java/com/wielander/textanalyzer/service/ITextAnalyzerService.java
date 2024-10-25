package com.wielander.textanalyzer.service;

import com.wielander.textanalyzer.model.TextAnalysisRequest;

import java.util.Map;

public interface ITextAnalyzerService {
    Map<Character, Integer> analyze(TextAnalysisRequest request);
}
