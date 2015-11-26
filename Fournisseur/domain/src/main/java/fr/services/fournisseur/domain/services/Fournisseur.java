package fr.services.fournisseur.domain.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import fr.services.fournisseur.api.factories.IProduitFactory;
import fr.services.fournisseur.api.services.IFournisseurService;
import fr.services.fournisseur.api.valueobject.IProduit;
import fr.services.fournisseur.domain.valueobject.Produit;

public class Fournisseur implements IFournisseurService{
	
	private String deviseProduits;
	private Map<String, IProduit> produitsEnStock;
	private Map<String, IProduit> reservations;
	private Map<String, IProduit> produitsCommandes;

	private IProduitFactory produitFactory = null;
	
	public Fournisseur(IProduitFactory newProduitFactory) {
		this.produitFactory = newProduitFactory;
		
		deviseProduits = "USD";
		produitsEnStock = new TreeMap<String, IProduit>();
		reservations = new TreeMap<String, IProduit>();
		IProduit p = produitFactory.getProduit("dexter", 20.0, 10);
		produitsEnStock.put(p.getId(), p);
		deviseProduits = "USD";

		IProduit p2 = produitFactory.getProduit("aaaaaa", 20, 10);
		produitsEnStock.put(p2.getId(), p2);

		deviseProduits = "USD";

		IProduit p3 = produitFactory.getProduit("bbbbb", 20, 10);
		produitsEnStock.put(p3.getId(), p3);
		 
	}

	public void annulerReservation(String id) {
		if(!reservations.containsKey(id)){
		}
		else{
			IProduit p = produitsEnStock.get(id);
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

	public List<IProduit> listerProduits() {
		List<IProduit> list = new ArrayList<IProduit>(produitsEnStock.values());
		return list;
	}
	
	public String reserverProduit(String id, int quantite) {
		if(!produitsEnStock.containsKey(id)){
			return null;
		}
		else if(produitsEnStock.get(id).getQuantite() < quantite){
			return null;
		}
		else{	
			IProduit p = produitsEnStock.get(id);
			String idReservation = UUID.randomUUID().toString();
			reservations.put(idReservation, produitFactory.getProduit(p.getNom(), id, p.getPrix(), quantite));
			p.setQuantite(p.getQuantite() - quantite);
			return idReservation;
		}
	}

}
