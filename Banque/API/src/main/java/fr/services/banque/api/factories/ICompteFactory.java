package fr.services.banque.api.factories;

import fr.services.banque.api.valueobject.ICompte;

public interface ICompteFactory {

	public ICompte getCompte(String IBAN, double solde);
}
