package pl.karolkolarczyk.lgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.karolkolarczyk.lgs.entity.Season;


public interface SeasonRepository extends JpaRepository<Season, Integer> {


}
