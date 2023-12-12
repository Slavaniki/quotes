package slavaniki.quotes.service;

import slavaniki.quotes.Dto.QuoteDto;

import java.util.List;

public interface QuoteService {
    QuoteDto addQuote(QuoteDto quote);
    QuoteDto updateQuote(QuoteDto quote, Long id);
    List<QuoteDto> getTop10Quotes();
    List<QuoteDto> getFlop10Quotes();
    QuoteDto getRandomQuote();
    List<QuoteDto> getQuotes(int from);
    QuoteDto deleteQuote(Long id);
}
