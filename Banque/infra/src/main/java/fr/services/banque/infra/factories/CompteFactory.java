package fr.services.banque.infra.factories;

import fr.services.banque.api.factories.ICompteFactory;
import fr.services.banque.api.valueobject.ICompte;
import fr.services.banque.domain.valueobject.Compte;

public class CompteFactory implements ICompteFactory {

	@Override
	public ICompte getCompte(String IBAN, double solde) {
		return new Compte(IBAN, solde);
	}

}
