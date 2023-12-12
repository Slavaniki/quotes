package slavaniki.quotes.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import slavaniki.quotes.Dto.QuoteDto;
import slavaniki.quotes.service.QuoteService;

import java.util.List;

@RestController
@RequestMapping("/quotes")
@Slf4j
@RequiredArgsConstructor
public class QuoteController {
    private final QuoteService quoteService;

    @PostMapping
    public ResponseEntity<QuoteDto> addQuote(@RequestBody QuoteDto quote) {
        QuoteDto addedQuote = quoteService.addQuote(quote);
        return new ResponseEntity<>(addedQuote, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<QuoteDto> deleteQuote(@PathVariable Long id) {
        QuoteDto deletedQuote = quoteService.deleteQuote(id);
        return new ResponseEntity<>(deletedQuote, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuoteDto> updateQuote(@RequestBody QuoteDto quote, @PathVariable Long id) {
        QuoteDto updatedQuote = quoteService.updateQuote(quote, id);
        return new ResponseEntity<>(updatedQuote, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<QuoteDto>> getQuotes(@RequestParam int from) {
        List<QuoteDto> quotes = quoteService.getQuotes(from);
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }

    @GetMapping("/random")
    public ResponseEntity<QuoteDto> getRandomQuote() {
        QuoteDto randomQuote = quoteService.getRandomQuote();
        return new ResponseEntity<>(randomQuote, HttpStatus.OK);
    }

    @GetMapping("/top10")
    public ResponseEntity<List<QuoteDto>> getTop10Quotes() {
        List<QuoteDto> top10Quotes = quoteService.getTop10Quotes();
        return new ResponseEntity<>(top10Quotes, HttpStatus.OK);
    }

    @GetMapping("/flop10")
    public ResponseEntity<List<QuoteDto>> getFlop10Quotes() {
        List<QuoteDto> flop10Quotes = quoteService.getFlop10Quotes();
        return new ResponseEntity<>(flop10Quotes, HttpStatus.OK);
    }
}