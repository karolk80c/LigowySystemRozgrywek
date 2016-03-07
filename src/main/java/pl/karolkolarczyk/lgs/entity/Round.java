package pl.karolkolarczyk.lgs.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "kolejka")
public class Round {

	@Id
	@GeneratedValue
	int id;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Match> matches;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "season_id")
	Season season;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((matches == null) ? 0 : matches.hashCode());
		result = prime * result + number;
		result = prime * result + ((season == null) ? 0 : season.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Round other = (Round) obj;
		if (id != other.id) {
			return false;
		}
		if (matches == null) {
			if (other.matches != null) {
				return false;
			}
		} else if (!matches.equals(other.matches)) {
			return false;
		}
		if (number != other.number) {
			return false;
		}
		if (season == null) {
			if (other.season != null) {
				return false;
			}
		} else if (!season.equals(other.season)) {
			return false;
		}
		return true;
	}

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
