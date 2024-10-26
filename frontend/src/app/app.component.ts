import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {TextAnalysisComponent} from "./features/text-analysis/components/text-analysis/text-analysis.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, TextAnalysisComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.sass'
})
export class AppComponent {
  title = 'Text Analyzer';
}
