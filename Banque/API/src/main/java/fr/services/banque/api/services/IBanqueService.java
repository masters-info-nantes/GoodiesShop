package fr.services.banque.api.services;

public interface IBanqueService {

	/**
	 * Effectuer un paiement.
	 * @param compteDebit Bénéficiaire du paiement (IBAN)
	 * @param compteCredit Client qui paye (IBAN)
	 * @param somme Montant en dollars.
	 * @return true si le paiement a réussi, false sinon.
	 */
	public boolean payer(String compteDebit, String compteCredit, double somme);
}
