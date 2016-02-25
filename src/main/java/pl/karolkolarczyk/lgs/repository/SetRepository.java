package pl.karolkolarczyk.lgs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.entity.Set;

public interface SetRepository extends JpaRepository<Set, Integer> {

	List<Set> findByMatch(Match match);

}
