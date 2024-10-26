package com.wielander.textanalyzer.controller;

import com.wielander.textanalyzer.dto.TextAnalysisRequestDto;
import com.wielander.textanalyzer.model.TextAnalysisRequest;
import com.wielander.textanalyzer.service.implementation.TextAnalyzerService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/analyze")
public class TextAnalyzerEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(TextAnalyzerEndpoint.class);
    private final TextAnalyzerService textAnalyzerService;
    private final ModelMapper modelMapper;


    public TextAnalyzerEndpoint(TextAnalyzerService textAnalyzerService, ModelMapper modelMapper) {
        this.textAnalyzerService = textAnalyzerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<Map<Character, Integer>> analyze(@RequestBody @Valid TextAnalysisRequestDto request) {
        logger.info("Received analysis request for type: {}", request.getType());
        TextAnalysisRequest textAnalysisRequest = modelMapper.map(request, TextAnalysisRequest.class);
        logger.debug("Mapped DTO to model: {}", textAnalysisRequest);

        Map<Character, Integer> result = textAnalyzerService.analyze(textAnalysisRequest);

        logger.info("Analysis completed with {} results", result.size());
        return ResponseEntity.ok(result);
    }
}
