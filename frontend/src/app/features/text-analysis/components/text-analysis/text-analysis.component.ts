import { Component } from '@angular/core';
import {TextAnalyzerService} from "../../services/text-analyzer.service";
import {FormsModule} from "@angular/forms";
import {CommonModule, NgFor} from "@angular/common";

@Component({
  selector: 'app-text-analysis',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule,
    NgFor,
  ],
  templateUrl: './text-analysis.component.html',
  styleUrl: './text-analysis.component.sass'
})
export class TextAnalysisComponent {
  inputText: string = '';
  analysisType: 'vowels' | 'consonants' = 'vowels';
  isOnline: boolean = false;
  analysisResults: { character: string, count: number }[] = [];

  constructor(private textAnalyzerService: TextAnalyzerService) {}

  analyzeText() {
    if (this.isOnline) {
      this.textAnalyzerService.analyzeOnline(this.inputText, this.analysisType).subscribe({
        next: result => this.updateAnalysisResults(result),
        error: error => console.error('Error analyzing text online:', error)
      });
    } else {
      const result = this.textAnalyzerService.analyzeOffline(this.inputText, this.analysisType);
      this.updateAnalysisResults(result);
    }
  }

  private updateAnalysisResults(charCountMap: Map<string, number>) {
    this.analysisResults = Array.from(charCountMap.entries()).map(([character, count]) => ({ character, count }));
  }
}
