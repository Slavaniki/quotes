package slavaniki.quotes.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import slavaniki.quotes.Dto.QuoteDto;
import slavaniki.quotes.entity.Quote;
import slavaniki.quotes.entity.User;
import slavaniki.quotes.entity.Vote;
import slavaniki.quotes.exeption.BadRequestException;
import slavaniki.quotes.exeption.NotFoundException;
import slavaniki.quotes.mapper.QuoteMapper;
import slavaniki.quotes.repository.QuoteRepository;
import slavaniki.quotes.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {
    private final UserRepository userRepository;
    private final QuoteRepository quoteRepository;

    @Override
    public QuoteDto addQuote(QuoteDto quoteDto) {
        if (quoteDto.getUserId() == null) {
            throw new BadRequestException("Нужно передать userId");
        }
        User user = userRepository.findById(quoteDto.getUserId()).orElseThrow(() ->
                        new NotFoundException("User не найден"));
        List<Vote> votes = new ArrayList<>();
        Quote quote = QuoteMapper.toQuote(quoteDto,user,votes);
        Quote result = quoteRepository.save(quote);
        log.info("цитата создана");
        return QuoteMapper.toQuoteDto(result);
    }

    @Override
    public QuoteDto updateQuote(QuoteDto quoteDto, Long id) {
        Quote oldQuote = quoteRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Цитата с id " + id + " не найдена"));
        if (quoteDto.getContent() != null && !quoteDto.getContent().isBlank()) {
            oldQuote.setContent(quoteDto.getContent());
        }
        if (quoteDto.getDateOfUpdate() != null && !quoteDto.getDateOfUpdate().isAfter(LocalDateTime.now())) {
            oldQuote.setDateOfUpdate(quoteDto.getDateOfUpdate());
        }
        log.info("Цитата с id " + id + " обновлена");
        return QuoteMapper.toQuoteDto(quoteRepository.save(oldQuote));
    }

    @Override
    public List<QuoteDto> getTop10Quotes() {
        return toQuoteDtos(quoteRepository.findTop10());
    }

    @Override
    public List<QuoteDto> getFlop10Quotes() {
        return toQuoteDtos(quoteRepository.findFlop10());
    }

    @Override
    public QuoteDto getRandomQuote() {
        return QuoteMapper.toQuoteDto(quoteRepository.findRandomQuote());
    }

    @Override
    public List<QuoteDto> getQuotes(int from) {
        return toQuoteDtos(quoteRepository.findAll(PageRequest.of(from, 10)).toList());
    }

    @Override
    public QuoteDto deleteQuote(Long id) {
        Quote oldQuote = quoteRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Цитата с id " + id + " не найдена"));
        quoteRepository.delete(oldQuote);
        log.info("Цитата с id " + id + " удалена");
        return QuoteMapper.toQuoteDto(oldQuote);
    }

    private List<QuoteDto> toQuoteDtos(List<Quote> quotes) {
        List<QuoteDto> quotesDto = new ArrayList<>();
        quotes.forEach(quote -> quotesDto.add(QuoteMapper.toQuoteDto(quote)));
        return quotesDto;
    }
}
