package slavaniki.quotes.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import slavaniki.quotes.entity.Quote;
import slavaniki.quotes.entity.Vote;
import slavaniki.quotes.Dto.VoteDto;
import slavaniki.quotes.exeption.BadRequestException;
import slavaniki.quotes.exeption.NotFoundException;
import slavaniki.quotes.mapper.VoteMapper;
import slavaniki.quotes.repository.QuoteRepository;
import slavaniki.quotes.repository.VoteRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {
    private final VoteRepository voteRepository;
    private final QuoteRepository quoteRepository;

    @Override
    public VoteDto vote(VoteDto voteDto) {
        if (voteDto.getQuoteId() != null) {
            Quote quote = quoteRepository.findById(voteDto.getQuoteId()).orElseThrow(() ->
                    new NotFoundException("Цитата с id " + voteDto.getQuoteId() + " не найдена"));
            Vote vote = VoteMapper.toVote(voteDto, quote);
            vote = voteRepository.save(vote);
            List<Vote> listVotes = quote.getVotes();
            listVotes.add(vote);
            quote.setVotes(listVotes);
            quoteRepository.save(quote);
            log.info("вы успешно проголосовали");
            return VoteMapper.toVoteDto(vote);
        } else {
            throw new BadRequestException("не было передано quoteId");
        }
    }
}
