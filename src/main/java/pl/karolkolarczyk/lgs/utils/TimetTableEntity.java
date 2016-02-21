package pl.karolkolarczyk.lgs.utils;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TimetTableEntity {
	public String firstName;
	public String secondName;
	public Date matchDate;
	public Integer firstPoint;
	public Integer secondPoint;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public Date getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(Date matchDate) {
		this.matchDate = matchDate;
	}

	public Integer getFirstPoint() {
		return firstPoint;
	}

	public void setFirstPoint(Integer firstPoint) {
		this.firstPoint = firstPoint;
	}

	public Integer getSecondPoint() {
		return secondPoint;
	}

	public void setSecondPoint(Integer secondPoint) {
		this.secondPoint = secondPoint;
	}
}
