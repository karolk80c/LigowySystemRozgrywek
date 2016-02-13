package pl.karolkolarczyk.lgs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity(name="Statystyka_Zawodnika")
public class UserStatistic {

	@Id
	@GeneratedValue
	private int id;
	
	int wonSmallPoints;
	int lostSmallPoints;
	int wonSets;
	int lostSets;
	int wonMatches;
	int lostMatches;

	public int getWonMatches() {
		return wonMatches;
	}

	public void setWonMatches(int wonMatches) {
		this.wonMatches = wonMatches;
	}

	public int getLostMatches() {
		return lostMatches;
	}

	public void setLostMatches(int lostMatches) {
		this.lostMatches = lostMatches;
	}

	public int getWonSmallPoints() {
		return wonSmallPoints;
	}

	public void setWonSmallPoints(int wonsmallPoints) {
		this.wonSmallPoints = wonsmallPoints;
	}

	public int getLostSmallPoints() {
		return lostSmallPoints;
	}

	public void setLostSmallPoints(int lostsmallPoints) {
		this.lostSmallPoints = lostsmallPoints;
	}

	public int getWonSets() {
		return wonSets;
	}

	public void setWonSets(int wonSets) {
		this.wonSets = wonSets;
	}

	public int getLostSets() {
		return lostSets;
	}

	public void setLostSets(int lostSets) {
		this.lostSets = lostSets;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
