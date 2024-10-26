import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TextAnalyzerService {

  private readonly apiUrl = 'http://localhost/api/v1/analyze';

  constructor(private http: HttpClient) { }

  analyzeOnline(text: string, type: 'vowels' | 'consonants'): Observable<Map<string, number>> {
    const requestBody = { text, type };
    return this.http.post<Map<string, number>>(this.apiUrl, requestBody);
  }

  analyzeOffline(text: string, type: 'vowels' | 'consonants'): Map<string, number> {
    const result = new Map<string, number>();
    const vowels = ['a', 'e', 'i', 'o', 'u'];
    const consonants = 'bcdfghjklmnpqrstvwxyz'.split('');
    const characters = text.toLowerCase().split('');
    const target = type === 'vowels' ? vowels : consonants;
    characters.forEach(character => {
      if (target.includes(character)) {
        result.set(character, (result.get(character) || 0) + 1);
      }
    });
    return result;
  }
}
