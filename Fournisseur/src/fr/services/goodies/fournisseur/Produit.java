package fr.services.goodies.fournisseur;

import java.util.UUID;

public class Produit {

	String id;
	String nom;
	
	double prix;
	int quantite;
	
	public Produit(String nom, double prix, int quantite) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.quantite = quantite;
		this.id = UUID.randomUUID().toString();
	}
	
	public Produit(String id, String nom, double prix, int quantite) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.quantite = quantite;
		this.id = id;
	}
	
	public String getId(){
		return this.id;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
}
