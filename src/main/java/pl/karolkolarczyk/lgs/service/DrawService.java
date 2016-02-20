package pl.karolkolarczyk.lgs.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.karolkolarczyk.lgs.repository.UserRepository;

@Service
@Transactional
public class DrawService {
	
	@Autowired
	private UserRepository userRepository;
	
	public void draw() {
//		List<User> userList = new ArrayList<>();
//		Set<User> userSet = new HashSet<>();
//		final int userListSize = userList.size();
//		Random generator = new Random();
//		int iloscSpotkan = (userListSize * (userListSize - 1)) / 2;
//		int iloscKolejek;
//		if (userListSize % 2 == 0) {
//			iloscKolejek = userListSize - 1;
//		} else {
//			iloscKolejek = userListSize;
//		}
//		
		//
//		// tablica [ilosckolejek][liczbaStartujacychDruzyn]
		// tablicaParzystaKolejka[nrkolejki][nrliczby] = wypelnij ostatnia
		// parzysta kolejke() ;
		// tablicaNieParzystaKolejka[nrkolejki][nrliczby] = wypelniij pierwsza
		// kolejke() ; // = { { }, { }, { } }
//		
		// // WYPELNIJ RESZTE KOLEJEK
//		if(numerkolejki jest parzysty )
//		metoda(numerkolejki,tablicaparzysta)
		// else
//		metoda(numerkolejki,tablicanieparzysta)
//		
		// // KOLEJNE KOLEJKI
//		// kolejka nieparzysta
//		if(liczba==userList.size()){
//			liczba=1;
//		}
//		else{
//			liczba++;
//		}
//		// kolejka parzysta
		// if(liczba==1)
		// liczba=userList.size()
		// else{
		// liczba--
		// }
//		

	}

}
