package fr.services.fournisseur.domain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import fr.services.fournisseur.api.services.IFournisseurService;
import fr.services.fournisseur.api.valueobject.IProduit;
import fr.services.fournisseur.domain.valueobject.Produit;

public class Fournisseur implements IFournisseurService{
	
	private String deviseProduits;
	private Map<String, Produit> produitsEnStock;
	private Map<String, Produit> reservations;
	private Map<String, Produit> produitsCommandes;

	
	public Fournisseur() {
		
		deviseProduits = "USD";
		produitsEnStock = new TreeMap<String, Produit>();
		reservations = new TreeMap<String, Produit>();
		Produit p = new Produit("dexter", 20.0, 10);
		produitsEnStock.put(p.getId(), p);
		deviseProduits = "USD";

		Produit p2 = new Produit("aaaaaa", 20, 10);
		produitsEnStock.put(p2.getId(), p2);

		deviseProduits = "USD";

		Produit p3 = new Produit("bbbbb", 20, 10);
		produitsEnStock.put(p3.getId(), p3);
		 
	}

	public void annulerReservation(String id) {
		if(!reservations.containsKey(id)){
		}
		else{
			Produit p = produitsEnStock.get(id);
			p.setQuantite(p.getQuantite() + reservations.get(id).getQuantite());
			reservations.remove(id);
		}
	}

	public IProduit getReservation(String id) {
		if(!reservations.containsKey(id)){
			return null;
		}
		return reservations.get(id);
	}

	public IProduit[] listerProduits() {
		List<Produit> list = new ArrayList<Produit>(produitsEnStock.values());
		Produit[] p = new Produit[list.size()];
		list.toArray(p);
		return p;
	}
	
	public String reserverProduit(String id, int quantite) {
		if(!produitsEnStock.containsKey(id)){
			return null;
		}
		else if(produitsEnStock.get(id).getQuantite() < quantite){
			return null;
		}
		else{	
			Produit p = produitsEnStock.get(id);
			String idReservation = UUID.randomUUID().toString();
			reservations.put(idReservation, new Produit(p.getNom(), p.getPrix(), quantite));
			p.setQuantite(p.getQuantite() - quantite);
			return idReservation;
		}
	}

}
