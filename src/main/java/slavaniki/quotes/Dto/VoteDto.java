package slavaniki.quotes.Dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VoteDto {
    private Long id;
    private Long quoteId;
    private int voteType;
}