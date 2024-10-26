package com.wielander.textanalyzer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wielander.textanalyzer.dto.TextAnalysisRequestDto;
import com.wielander.textanalyzer.service.implementation.TextAnalyzerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TextAnalyzerEndpoint.class)
public class TextAnalyzerEndpointTest {

    private static final String ANALYZE_URL = "/api/v1/analyze";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TextAnalyzerService textAnalyzerService;

    @MockBean
    private ModelMapper modelMapper;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testAnalyzeEndpointWithValidRequest() throws Exception {
        TextAnalysisRequestDto requestDto = new TextAnalysisRequestDto();
        requestDto.setType("vowels");
        requestDto.setText("Hello World");

        Map<Character, Integer> mockResponse = Map.of('e', 1, 'o', 2);
        when(textAnalyzerService.analyze(any())).thenReturn(mockResponse);

        mockMvc.perform(post(ANALYZE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockResponse)));
    }

    @Test
    void testAnalyzeEndpointWithInvalidRequestType() throws Exception {
        TextAnalysisRequestDto requestDto = new TextAnalysisRequestDto();
        requestDto.setType("invalid"); // Invalid type
        requestDto.setText("Hello World");

        mockMvc.perform(post(ANALYZE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"message\":\"Validation failed\",\"errors\":[\"Type must be either 'vowels' or 'consonants'\"]}"));
    }

    @Test
    void testAnalyzeEndpointWithMalformedJson() throws Exception {
        String malformedJson = "{ \"type\": \"vowels\", \"text\": \"Hello World\""; // Missing closing brace

        mockMvc.perform(post(ANALYZE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(malformedJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"message\":\"Malformed JSON request\",\"errors\":[\"Invalid JSON format or data type\"]}"));
    }
}
