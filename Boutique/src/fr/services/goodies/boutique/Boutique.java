package fr.services.goodies.boutique;


import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import fr.services.goodies.fournisseur.FournisseurQuantiteInsufisanteException;
import fr.services.goodies.fournisseur.FournisseurStub;
import fr.services.goodies.fournisseur.FournisseurStub.AnnulerReservation;
import fr.services.goodies.fournisseur.FournisseurStub.ListerProduits;
import fr.services.goodies.fournisseur.FournisseurStub.ListerProduitsResponse;
import fr.services.goodies.fournisseur.FournisseurStub.Produit;
import fr.services.goodies.fournisseur.FournisseurStub.ReserverProduit;
import fr.services.goodies.fournisseur.FournisseurStub.ReserverProduitResponse;

public class Boutique {
	
	private final static String fournisseurEndPoint = "http://localhost:9763/services/Fournisseur/";

	public Produit[] listerProduits(){
		 
		Produit[] listeProduits = null;
		FournisseurStub stub;
		try {
			stub = new FournisseurStub(fournisseurEndPoint);
			
			ListerProduits requete = new ListerProduits();
			ListerProduitsResponse reponse = stub.listerProduits(requete);
			listeProduits = reponse.get_return();

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeProduits;
	}
	
	public void validerCommande(String id, int quantite) throws FournisseurQuantiteInsufisanteException{
		FournisseurStub stub;
		try {
			stub = new FournisseurStub(fournisseurEndPoint);
			
			ReserverProduit requete = new ReserverProduit();
			requete.setId(id);
			requete.setQuantite(quantite);
			ReserverProduitResponse reponse = stub.reserverProduit(requete);
			reponse.get_return();


		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
