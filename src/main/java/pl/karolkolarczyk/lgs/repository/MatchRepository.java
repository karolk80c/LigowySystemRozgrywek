package pl.karolkolarczyk.lgs.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.entity.Round;

public interface MatchRepository extends JpaRepository<Match, Integer> {

	List<Match> findByMatchDate(Date matchdate);

	List<Match> findByRound(Round round);

	Page<Match> findByCompletedAndMatchDateAfter(boolean completed, Date date, Pageable pageable);

	Page<Match> findByCompletedAndMatchDateAfterAndFirstNameOrSecondName(boolean completed, Date date,
			Pageable pageable, String firstName, String secondName);

	Page<Match> findByCompletedAndMatchDateNotNull(boolean completed, Pageable pageable);

	Page<Match> findByCompletedAndMatchDateNotNullAndFirstNameOrSecondName(boolean completed, Pageable pageable,
			String firstName, String secondName);

}
