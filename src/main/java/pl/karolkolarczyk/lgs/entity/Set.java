package pl.karolkolarczyk.lgs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "runda")
public class Set {

	@Id
	@GeneratedValue
	private Integer id;

	private Integer firstPlayerScore;

	private Integer secondPlayerScore;

	@ManyToOne
	@JoinColumn(name = "match_id")
	private Match match;

	public Set() {

	}

	public Set(int firstPoints, int secondPoints) {
		firstPlayerScore = firstPoints;
		secondPlayerScore = secondPoints;
	}

	public Set(int firstPoints, int secondPoints, Match match) {
		firstPlayerScore = firstPoints;
		secondPlayerScore = secondPoints;
		setMatch(match);
	}

	public Integer getFirstPlayerScore() {
		return firstPlayerScore;
	}

	public void setFirstPlayerScore(Integer firstPlayerScore) {
		this.firstPlayerScore = firstPlayerScore;
	}

	public Integer getSecondPlayerScore() {
		return secondPlayerScore;
	}

	public void setSecondPlayerScore(Integer secondPlayerScore) {
		this.secondPlayerScore = secondPlayerScore;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
