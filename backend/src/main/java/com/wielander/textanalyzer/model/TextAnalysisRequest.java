package com.wielander.textanalyzer.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextAnalysisRequest {
    private String type;
    private String text;
}

