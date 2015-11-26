package fr.services.banque.domain.services;

import java.util.HashMap;
import java.util.Map;

import fr.services.banque.api.factories.ICompteFactory;
import fr.services.banque.api.services.IBanqueService;
import fr.services.banque.api.valueobject.ICompte;

public class Banque implements IBanqueService {
	
	private Map<String, ICompte> comptes;
	
	public Banque(ICompteFactory factory) {
		
		comptes = new HashMap<String, ICompte>();
		//valeurs pour test
		ICompte client = factory.getCompte("client", 1000),
				boutique = factory.getCompte("boutique", 1000);
		comptes.put("client", client);
		comptes.put("boutique", boutique);
	}

	@Override
	public boolean payer(String compteDebit, String compteCredit, double somme) {
		//On ne valide pas si découvert... ?
		ICompte debiteur = comptes.get(compteDebit);
		ICompte crediteur = comptes.get(compteCredit);
		
		if (somme <= 0 || debiteur == null || crediteur == null) {
			return false;
		}
		if (debiteur.getSolde() - somme < 0) {
			return false;
		}
		debiteur.debit(somme);
		crediteur.credit(somme);
		return true;
	}

}
