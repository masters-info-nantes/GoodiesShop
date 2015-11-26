package fr.services.banque.api.valueobject;

public interface ICompte {

	public String getIBAN();
	public double getSolde();
	/**
	 * Verser de l'argent sur ce compte.
	 * @param somme Montant en USD
	 */
	public void credit(double somme);
	/**
	 * Retirer de l'argent de ce compte.
	 * @param somme Montant en USD
	 */
	public void debit(double somme);
}
