package slavaniki.quotes.mapper;

import slavaniki.quotes.Dto.QuoteDto;
import slavaniki.quotes.entity.*;

import java.util.List;

public final class QuoteMapper {
    private QuoteMapper() {}

    public static QuoteDto toQuoteDto(Quote quote) {
        return QuoteDto.builder()
                .id(quote.getId())
                .content(quote.getContent())
                .dateOfUpdate(quote.getDateOfUpdate())
                .userId(quote.getUser().getId())
                .votes(VoteMapper.toVotesId(quote.getVotes()))
                .build();
    }

    public static Quote toQuote(QuoteDto quoteDto, User user, List<Vote> votes) {
        return Quote.builder()
                .content(quoteDto.getContent())
                .dateOfUpdate(quoteDto.getDateOfUpdate())
                .user(user)
                .votes(votes)
                .build();
    }
}
