package fr.services.goodies.fournisseur;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Fournisseur {

	private String deviseProduits;
	private Map<String, Produit> produitsEnStock;
	private Map<String, Produit> produitsReserves;
	
	public Fournisseur() {
		deviseProduits = "USD";
		produitsEnStock = new TreeMap<String, Produit>();
		produitsReserves = new TreeMap<String, Produit>();
		Produit p = new Produit("dexter", 20, 10);
		produitsEnStock.put(p.getId(), p);
	}
	
	public List<Produit> listerProduits(){
		List<Produit> list = new ArrayList<Produit>(produitsEnStock.values());
		return list;
	}
	
	public void reserverProduit(String id, int quantite){
		if(produitsEnStock.get(id).getQuantite() < quantite){
			
		}
		else{	
			Produit p = produitsEnStock.get(id);
			produitsReserves.put(id, new Produit(id, p.getPrix(), quantite));
			p.setQuantite(p.getQuantite() - quantite);
		}
	}
			
	public void annulerReservation(String id){
		Produit p = produitsEnStock.get(id);
		p.setQuantite(p.getQuantite() + produitsReserves.get(id).getQuantite());
		produitsReserves.remove(id);
	}
	
	public List<Produit> listerProduitsR(String s){
		List<Produit> list = new ArrayList<Produit>(produitsReserves.values());
		return list;
	}
	
}
