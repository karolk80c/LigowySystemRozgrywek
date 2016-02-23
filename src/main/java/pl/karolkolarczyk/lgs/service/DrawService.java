package pl.karolkolarczyk.lgs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.karolkolarczyk.lgs.entity.Match;
import pl.karolkolarczyk.lgs.entity.Role;
import pl.karolkolarczyk.lgs.entity.Round;
import pl.karolkolarczyk.lgs.entity.Season;
import pl.karolkolarczyk.lgs.entity.User;
import pl.karolkolarczyk.lgs.repository.MatchRepository;
import pl.karolkolarczyk.lgs.repository.RoundRepository;
import pl.karolkolarczyk.lgs.repository.SeasonRepository;
import pl.karolkolarczyk.lgs.repository.UserRepository;

@Transactional
@Service
public class DrawService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SeasonRepository seasonRepository;

	@Autowired
	private MatchRepository matchRepository;

	@Autowired
	private RoundRepository roundRepository;

	public void draw() {
		Season season = new Season();
		List<User> users = userRepository.findAll();
		List<String> userListLogin = generateLoginList(users);
		int iloscSpotkan = numMatches(userListLogin.size());
		if (userListLogin.size() % 2 != 0) {
			userListLogin.add("null");
		}

		int userListSize = userListLogin.size();
		int numDays = (userListSize - 1);
		int halfSize = userListSize / 2;

		System.out.println("\nIlosc uczestników: " + userListSize);
		for (String string : userListLogin) {
			System.out.println(string);
		}
		System.out.println("\nIlosc kolejek: " + numRounds(userListSize));
		System.out.println("Ilosc spotkan: " + iloscSpotkan + "\n");

		List<String> teams = new ArrayList<>();
		if (userListLogin.size() > 0) {
			teams.addAll(userListLogin);

			teams.remove(0);

			int teamsSize = teams.size();
			List<Round> rounds = new ArrayList<>();

			for (int day = 0; day < numDays; day++) {
				System.out.println("Kolejka " + (day + 1));
				Round round = new Round();
				List<Match> matches = new ArrayList<>();
				round.setNumber((day + 1));

				int teamIdx = day % teamsSize;
				if (!("null".equals(teams.get(teamIdx))) && !("null".equals(userListLogin.get(0)))) {
					System.out.println(teams.get(teamIdx) + " vs " + userListLogin.get(0));
					User user1 = userRepository.findOne(teams.get(teamIdx));
					User user2 = userRepository.findOne(userListLogin.get(0));
					List<User> usersList = new ArrayList<>();
					usersList.add(user1);
					usersList.add(user2);
					Match match = new Match();
					match.setUsers(usersList);
					match.setFirstName(user1.getFirstName() + " " + user1.getLastName());
					match.setSecondName(user2.getFirstName() + " " + user2.getLastName());
					match.setRound(round);
					matchRepository.save(match);
					matches.add(match);
					List<Match> user1Matches = user1.getMatches();
					List<Match> user2Matches = user2.getMatches();
					user1Matches.add(match);
					user2Matches.add(match);
					user1.setMatches(user1Matches);
					user2.setMatches(user2Matches);
					userRepository.save(user1);
					userRepository.save(user2);

				}

				for (int idx = 1; idx < halfSize; idx++) {
					int firstTeam = (day + idx) % teamsSize;
					int secondTeam = (day + teamsSize - idx) % teamsSize;
					if (!("null".equals(teams.get(firstTeam))) && !("null".equals(teams.get(secondTeam)))) {
						System.out.println(teams.get(firstTeam) + " vs " + teams.get(secondTeam));
						User user1 = userRepository.findOne(teams.get(firstTeam));
						User user2 = userRepository.findOne(teams.get(secondTeam));
						List<User> usersList = new ArrayList<>();
						usersList.add(user1);
						usersList.add(user2);
						Match match = new Match();
						match.setFirstName(user1.getFirstName() + " " + user1.getLastName());
						match.setSecondName(user2.getFirstName() + " " + user2.getLastName());
						match.setUsers(usersList);
						match.setRound(round);
						matchRepository.save(match);
						matches.add(match);
						List<Match> user1Matches = user1.getMatches();
						List<Match> user2Matches = user2.getMatches();
						user1Matches.add(match);
						user2Matches.add(match);
						user1.setMatches(user1Matches);
						user2.setMatches(user2Matches);
						userRepository.save(user1);
						userRepository.save(user2);

					}
				}
				System.out.println();
				round.setMatches(matches);
				round.setSeason(season);
				roundRepository.save(round);
				rounds.add(round);

			}

			season.setRounds(rounds);
			seasonRepository.save(season);
		}

	}

	public List<String> generateLoginList(List<User> users) {
		List<String> userListLogin = new ArrayList<>();
		for (User user : users) {
			for (Role role : user.getRoles()) {
				if ("ROLE_USER".equals(role.getName())) {
					userListLogin.add(user.getLogin());
				}
			}
		}

		return userListLogin;
	}

	public int numRounds(int userListSize) {
		int numRounds;
		if (userListSize % 2 == 0) {
			numRounds = userListSize - 1;
		} else {
			numRounds = userListSize;
		}
		return numRounds;
	}

	public int numMatches(int userListSize) {
		return (userListSize * (userListSize - 1)) / 2;
	}

}
