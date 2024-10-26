import { Component } from '@angular/core';
import {TextAnalyzerService} from "../../services/text-analyzer.service";
import {FormsModule} from "@angular/forms";
import {CommonModule, NgFor} from "@angular/common";
import {ToastrService} from "ngx-toastr";

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

  constructor
  (
    private textAnalyzerService: TextAnalyzerService,
    private toastr: ToastrService
  ){}

  analyzeText() {
    if (this.isOnline) {
      this.textAnalyzerService.analyzeOnline(this.inputText, this.analysisType).subscribe({
        next: result => this.updateAnalysisResults(result),
        error: error => this.toastr.error('Error analyzing text online'+error)
      });
      this.toastr.success('Text analyzed online!');
    } else {
      const result = this.textAnalyzerService.analyzeOffline(this.inputText, this.analysisType);
      this.updateAnalysisResults(result);
      this.toastr.success('Text analyzed offline!');
    }
  }

  private updateAnalysisResults(charCountMap: Map<string, number>) {
    charCountMap.forEach((count, character) => {
      const existingEntry = this.analysisResults.find(entry => entry.character === character);
      if (existingEntry) {
        existingEntry.count += count;
      } else {
        this.analysisResults.push({ character, count });
      }
    });
  }

  clearResults() {
    this.inputText = '';
    this.analysisResults = [];
    this.toastr.info('Results cleared!');
  }
}
