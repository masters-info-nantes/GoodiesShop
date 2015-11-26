package fr.services.boutique.api.services;

import fr.service.fournisseur.application.WebApplicationFournisseurStub.IProduit;

public interface IBoutique {
	
	public IProduit[] listerProduits();
	public void validerProduitPourCommande(String nomUtilisateur, String idProduits, int quantite);
	public void annulerCommandeEnCours(String nomUtilisateur);
	public String effectuerPaiement(String nomUtilisateur, String numeroCarte, String cyptogramme, String date);
	public void creerClient(String name);
	public void supprimerCLient(String name);
	
}
