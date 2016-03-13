package pl.karolkolarczyk.lgs.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import pl.karolkolarczyk.lgs.annotation.UniqueEmail;
import pl.karolkolarczyk.lgs.annotation.UniqueLogin;

@Entity(name = "uzytkownik")
public class User {

	@Id
	@Column(nullable = false, unique = true)
	@Size(min = 3, message = "Login powinien miec conajmniej 3 znaki!")
	@UniqueLogin(message = "Podany login jest ju¿ zajêty")
	private String login;

	@Column(nullable = false)
	@Size(min = 3, message = "Haslo powinno miec conajmniej 3 znaki!")
	private String password;

	@Column(nullable = false)
	@NotBlank(message = "To pole nie mo¿e byc puste.")
	private String firstName;

	@Column(nullable = false)
	@NotBlank(message = "To pole nie mo¿e byc puste.")
	private String lastName;

	@Column(nullable = false, unique = true)
	@Email(message = "Nieprawid³owy adres email")
	@NotBlank(message = "To pole nie mo¿e byc puste.")
	@UniqueEmail(message = "Na podany adres zosta³ ju¿ zarejestrowany u¿ytkownik")
	private String emailAdress;

	private String contactNumber;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "uzytkownik_mecz")
	private List<Match> matches;

	private boolean enabled;

	private int wonSmallPoints;

	private int wonSets;

	private int lostSmallPoints;

	private int lostSets;

	private int wonMatches;

	private int lostMatches;

	private int balanceMatches;

	private int balanceSets;

	private int balanceSmallPoints;

	private int rankingPosition;

	public int getRankingPosition() {
		return rankingPosition;
	}

	public void setRankingPosition(int rankingPosition) {
		this.rankingPosition = rankingPosition;
	}

	@PreUpdate
	public void updateBalance() {
		this.setBalanceSmallPoints(wonSmallPoints - lostSmallPoints);
		this.setBalanceMatches(wonMatches - lostMatches);
		this.setBalanceSets(wonSets - lostSets);
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFullName() {
		return firstName.concat(" ").concat(lastName);
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAdress() {
		return emailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getWonSmallPoints() {
		return wonSmallPoints;
	}

	public void setWonSmallPoints(int wonSmallPoints) {
		this.wonSmallPoints = wonSmallPoints;
	}

	public int getWonSets() {
		return wonSets;
	}

	public void setWonSets(int wonSets) {
		this.wonSets = wonSets;
	}

	public int getLostSmallPoints() {
		return lostSmallPoints;
	}

	public void setLostSmallPoints(int lostSmallPoints) {
		this.lostSmallPoints = lostSmallPoints;
	}

	public int getLostSets() {
		return lostSets;
	}

	public void setLostSets(int lostSets) {
		this.lostSets = lostSets;
	}

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

	public int getBalanceMatches() {
		return balanceMatches;
	}

	public int getBalanceSets() {
		return balanceSets;
	}

	public int getBalanceSmallPoints() {
		return balanceSmallPoints;
	}

	public void setBalanceMatches(int balanceMatches) {
		this.balanceMatches = balanceMatches;
	}

	public void setBalanceSets(int balanceSets) {
		this.balanceSets = balanceSets;
	}

	public void setBalanceSmallPoints(int balanceSmallPoints) {
		this.balanceSmallPoints = balanceSmallPoints;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
