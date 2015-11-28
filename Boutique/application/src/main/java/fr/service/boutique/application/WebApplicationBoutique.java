package fr.service.boutique.application;

import fr.services.boutique.api.services.IBoutique;
import fr.services.boutique.domain.services.Boutique;
import fr.services.boutique.infra.factories.FactoryClient;
import fr.services.fournisseur.domain.services.FournisseurStub.Produit;

public class WebApplicationBoutique {

	public static IBoutique boutique = null;
	
	public static Produit[] listerProduits() {
		if(boutique == null){
			boutique = new Boutique(new FactoryClient());
		}
		return boutique.listerProduits();		
	}
	
	public void validerProduitPourCommande(String nomUtilisateur, String idProduits, int quantite){
		if(boutique == null){
			boutique = new Boutique(new FactoryClient());
		}
		boutique.validerProduitPourCommande(nomUtilisateur, idProduits, quantite);
	}
	
	public void annulerCommandeEnCours(String nomUtilisateur){
		if(boutique == null){
			boutique = new Boutique(new FactoryClient());
		}
		boutique.annulerCommandeEnCours(nomUtilisateur);
	}
	
	public String effectuerPaiement(String nomCrediteur, String nomDebiteur, double somme){
		if(boutique == null){
			boutique = new Boutique(new FactoryClient());
		}
		return boutique.effectuerPaiement(nomCrediteur, somme);
	}
	
	public void creerClient(String name){
		if(boutique == null){
			boutique = new Boutique(new FactoryClient());
		}
		boutique.creerClient(name);
	}
	
	public void supprimerCLient(String name){
		if(boutique == null){
			boutique = new Boutique(new FactoryClient());
		}
		boutique.supprimerClient(name);
	}
	

}
