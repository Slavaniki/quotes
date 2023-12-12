package slavaniki.quotes.Dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class QuoteDto {
    private Long id;
    private String content;
    private LocalDateTime dateOfUpdate;
    private Long userId;
    private List<Long> votes;
}
