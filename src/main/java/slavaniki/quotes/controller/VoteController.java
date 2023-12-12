package slavaniki.quotes.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import slavaniki.quotes.Dto.VoteDto;
import slavaniki.quotes.service.VoteService;

@RestController
@RequestMapping("/vote")
@Slf4j
@RequiredArgsConstructor
public class VoteController {
    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<VoteDto> vote(@RequestBody VoteDto voteDto) {
        VoteDto result = voteService.vote(voteDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
