package pl.karolkolarczyk.lgs.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "Kolejka")
public class Round {

	@Id
	@GeneratedValue
	int id;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "kolejka_spotkanie")
	private List<Match> matches;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "season_id")
	Season season;

	int number;

	public int getNumber() {
		return number;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

}
