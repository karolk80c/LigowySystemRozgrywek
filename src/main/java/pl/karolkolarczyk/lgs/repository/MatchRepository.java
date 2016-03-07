package pl.karolkolarczyk.lgs.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.karolkolarczyk.lgs.entity.Match;

public interface MatchRepository extends JpaRepository<Match, Integer> {

	List<Match> findByMatchDate(Date matchdate);

}
