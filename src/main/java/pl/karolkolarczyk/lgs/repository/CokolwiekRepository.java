package pl.karolkolarczyk.lgs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.entity.Cokolwiek;

public interface CokolwiekRepository extends JpaRepository<Cokolwiek, Integer> {

	List<Cokolwiek> findByMatch(Match match);

}
