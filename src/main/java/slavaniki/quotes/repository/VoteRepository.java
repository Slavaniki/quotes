package slavaniki.quotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import slavaniki.quotes.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
