package pl.karolkolarczyk.lgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.karolkolarczyk.lgs.entity.Link;

public interface LinkRepository extends JpaRepository<Link, Integer> {

	Link findByName(String string);

	Link findByHref(String string);

}
