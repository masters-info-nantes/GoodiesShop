package fr.service.fournisseur.application;

import java.util.List;

import fr.services.fournisseur.api.services.IFournisseurService;
import fr.services.fournisseur.api.valueobject.IProduit;
import fr.services.fournisseur.domain.services.Fournisseur;
import fr.services.fournisseur.infra.factories.ProduitFactory;

public class WebApplicationFournisseur {
	
	public static IFournisseurService fournisseur = null;
	
	public void annulerReservation(String id){
		if(fournisseur == null){
			fournisseur = new Fournisseur(new ProduitFactory());
		}
		fournisseur.annulerReservation(id);
	}
	
	public IProduit getReservation(String id){
		if(fournisseur == null){
			fournisseur = new Fournisseur(new ProduitFactory());
		}
		return fournisseur.getReservation(id);
	}
	
	public List<IProduit> listerProduits(){
		if(fournisseur == null){
			fournisseur = new Fournisseur(new ProduitFactory());
		}
		return fournisseur.listerProduits();
	}
	
	public String reserverProduit(String id, int quantite){
		if(fournisseur == null){
			fournisseur = new Fournisseur(new ProduitFactory());
		}
		return fournisseur.reserverProduit(id, quantite);
	}
}
