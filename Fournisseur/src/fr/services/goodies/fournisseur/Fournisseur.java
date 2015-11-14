package fr.services.goodies.fournisseur;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Fournisseur {

	String deviseProduits;
	Map<String, Produit> produitsEnStock;
	Map<String, Produit> produitsReserves;
	
	public Fournisseur() {
		deviseProduits = "dollar";
		produitsEnStock = new TreeMap<String, Produit>();
		produitsReserves = new TreeMap<String, Produit>();
		Produit p = new Produit("dexter", 20, 10);
		produitsEnStock.put(p.getId(), p);
	}
	
	public List<Produit> listerProduits(){
		List<Produit> list = new ArrayList<Produit>(produitsEnStock.values());
		return list;
	}
	
	public List<Produit> listerProduitsR(){
		List<Produit> list = new ArrayList<Produit>(produitsReserves.values());
		return list;
	}
	
	public void reserverProduit(String id, int quantite) throws QuantiteInsufisanteException{
		if(produitsEnStock.get(id).getQuantite() < quantite){
			Throwable t = new IllegalArgumentException("Quantite insufisante");
			throw new QuantiteInsufisanteException("il n'y a pas assez de ce produit en stock", t);
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
	
}
