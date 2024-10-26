import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TextAnalyzerService {

  private readonly apiUrl = 'http://localhost:8080/api/v1/analyze';

  constructor(private http: HttpClient) { }

  analyzeOnline(text: string, type: 'vowels' | 'consonants'): Observable<Map<string, number>> {
    const requestBody = { type, text };
    return this.http.post<{ [key: string]: number }>(this.apiUrl, requestBody).pipe(
      map(response => this.transformToMap(response))
    );
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

  private transformToMap(data: { [key: string]: number }): Map<string, number> {
    return new Map<string, number>(Object.entries(data));
  }
}
