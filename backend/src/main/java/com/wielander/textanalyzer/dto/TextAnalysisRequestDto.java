package com.wielander.textanalyzer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TextAnalysisRequestDto {
        @NotNull(message = "Type cannot be null")
        @Pattern(regexp = "^(vowels|consonants)$", message = "Type must be either 'vowels' or 'consonants'")
        private String type;

        @NotNull(message = "Text cannot be null")
        @NotBlank(message = "Text cannot be empty")
        @Size(min = 1, max = 1000, message = "Text must be between 1 and 1000 characters")
        private String text;
}
