package slavaniki.quotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import slavaniki.quotes.entity.Quote;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    @Query(value = "SELECT * FROM quotes AS q, (SELECT v.quote_id, SUM(v.vote_type) AS sum_votes FROM votes AS v " +
            "GROUP BY v.quote_id) AS a WHERE a.quote_id = q.id ORDER BY a.sum_votes DESC LIMIT 10", nativeQuery = true)
    List<Quote> findTop10();
    @Query(value = "SELECT * FROM quotes AS q, (SELECT v.quote_id, SUM(v.vote_type) AS sum_votes FROM votes AS v " +
            "GROUP BY v.quote_id) AS a WHERE a.quote_id = q.id ORDER BY a.sum_votes ASC LIMIT 10", nativeQuery = true)
    List<Quote> findFlop10();
    @Query(value = "SELECT * FROM quotes AS q ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Quote findRandomQuote();

}