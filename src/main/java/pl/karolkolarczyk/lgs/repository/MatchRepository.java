package pl.karolkolarczyk.lgs.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.entity.Round;

public interface MatchRepository extends JpaRepository<Match, Integer> {

	List<Match> findByMatchDate(Date matchdate);

	List<Match> findByRound(Round round);

}
