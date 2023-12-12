package slavaniki.quotes.mapper;

import slavaniki.quotes.entity.Quote;
import slavaniki.quotes.entity.Vote;
import slavaniki.quotes.Dto.VoteDto;

import java.util.List;

public final class VoteMapper {
    private VoteMapper() {}

    public static VoteDto toVoteDto(Vote vote) {
        return VoteDto.builder()
                .id(vote.getId())
                .quoteId(vote.getQuote().getId())
                .voteType(vote.getVoteType())
                .build();
    }

    public static Vote toVote(VoteDto voteDto, Quote quote) {
        return Vote.builder()
                .quote(quote)
                .voteType(voteDto.getVoteType())
                .build();
    }

    public static List<Long> toVotesId(List<Vote> votes) {
        return votes.stream().map(vote -> vote.getId()).toList();
    }
}
