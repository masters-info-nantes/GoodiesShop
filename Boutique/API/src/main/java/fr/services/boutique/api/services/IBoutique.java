package fr.services.boutique.api.services;

import fr.service.fournisseur.application.WebApplicationFournisseurStub.IProduit;

public interface IBoutique {
	
	public IProduit[] listerProduits();
	public void validerProduitPourCommande(String nomUtilisateur, String idProduits, int quantite);
	public void annulerCommandeEnCours(String nomUtilisateur);
	public void creerClient(String name);
	void supprimerClient(String name);
	String effectuerPaiement(String nomCrediteur, double somme);
	
}
