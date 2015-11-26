package fr.services.banque.domain.valueobject;

import fr.services.banque.api.valueobject.ICompte;

public class Compte implements ICompte {
	
	private String IBAN;
	private double solde;

	public Compte(String IBAN, double solde) {
		this.IBAN = IBAN;
		this.solde = solde;
	}

	@Override
	public String getIBAN() {
		return IBAN;
	}

	@Override
	public double getSolde() {
		return solde;
	}

	@Override
	public void credit(double somme) {
		solde += somme; 
	}

	@Override
	public void debit(double somme) {
		solde -= somme;
	}

}
