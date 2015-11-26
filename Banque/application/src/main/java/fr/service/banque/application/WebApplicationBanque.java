package fr.service.banque.application;

import fr.services.banque.api.services.IBanqueService;
import fr.services.banque.domain.services.Banque;
import fr.services.banque.infra.factories.CompteFactory;

public class WebApplicationBanque {
	
	public static IBanqueService banque = null;
	
	public boolean payer(String compteDebit, String compteCredit, double somme) {
		if(banque == null){
			banque = new Banque(new CompteFactory());
		}
		return banque.payer(compteDebit, compteCredit, somme);
	}	
}
