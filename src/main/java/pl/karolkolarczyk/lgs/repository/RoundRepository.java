package pl.karolkolarczyk.lgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.karolkolarczyk.lgs.entity.Round;

public interface RoundRepository extends JpaRepository<Round, Integer> {


}
