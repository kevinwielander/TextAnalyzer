import { TestBed } from '@angular/core/testing';
import { TextAnalyzerService } from '../../services/text-analyzer.service';
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe('TextAnalyzerService', () => {
  let service: TextAnalyzerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(TextAnalyzerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should correctly analyze vowels', () => {
    const text = 'hello world';
    const result = service.analyzeOffline(text, 'vowels');
    expect(result.get('e')).toBe(1);
    expect(result.get('o')).toBe(2);
    expect(result.size).toBe(2);
  });

  it('should correctly analyze consonants', () => {
    const text = 'hello world';
    const result = service.analyzeOffline(text, 'consonants');
    expect(result.get('h')).toBe(1);
    expect(result.get('l')).toBe(3);
    expect(result.get('w')).toBe(1);
    expect(result.get('r')).toBe(1);
    expect(result.get('d')).toBe(1);
    expect(result.size).toBe(5);
  });
});
