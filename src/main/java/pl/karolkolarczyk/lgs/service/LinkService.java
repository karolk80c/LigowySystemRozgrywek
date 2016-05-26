package pl.karolkolarczyk.lgs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.karolkolarczyk.lgs.entity.Link;
import pl.karolkolarczyk.lgs.repository.LinkRepository;

@Service
public class LinkService {

	@Autowired
	LinkRepository linkRepository;

	public void removeLink(Integer linkId) {
		linkRepository.delete(linkId);
	}

	public void addLink(Link link) {
		linkRepository.save(link);
	}

	public List<Link> getAllLinks() {
		return linkRepository.findAll();
	}

}
