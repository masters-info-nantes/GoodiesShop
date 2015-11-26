package fr.services.boutique.domain.services;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.axis2.AxisFault;

import fr.service.banque.application.WebApplicationBanqueStub;
import fr.service.banque.application.WebApplicationBanqueStub.Payer;
import fr.service.fournisseur.application.WebApplicationFournisseurStub;
import fr.service.fournisseur.application.WebApplicationFournisseurStub.AnnulerReservation;
import fr.service.fournisseur.application.WebApplicationFournisseurStub.GetReservation;
import fr.service.fournisseur.application.WebApplicationFournisseurStub.GetReservationResponse;
import fr.service.fournisseur.application.WebApplicationFournisseurStub.IProduit;
import fr.service.fournisseur.application.WebApplicationFournisseurStub.ListerProduits;
import fr.service.fournisseur.application.WebApplicationFournisseurStub.ListerProduitsResponse;
import fr.service.fournisseur.application.WebApplicationFournisseurStub.ReserverProduit;
import fr.service.fournisseur.application.WebApplicationFournisseurStub.ReserverProduitResponse;
import fr.services.boutique.api.services.IBoutique;


public class Boutique implements IBoutique{

	private final static String fournisseurEndPoint = "http://localhost:9763/services/WebApplicationFournisseur/";
	private final static String banqueEndPoint = "http://localhost:9763/services/WebApplicationBanque/";
	private Set<String> clients;
	/*nom utilisateur / id reservation*/
	private Map<String, Set<String>> reservations;
	/*id commande / Produits*/
	private Map<String, List<IProduit>> commandes;
	
	public Boutique(){
		clients = new HashSet<String>();
		reservations = new TreeMap<String, Set<String>>();
		commandes = new TreeMap<String, List<IProduit>>();
	}

	
	@Override
	public IProduit[] listerProduits() {
		IProduit[] listeProduits = null;
		WebApplicationFournisseurStub stub;
		try {
			stub = new WebApplicationFournisseurStub(fournisseurEndPoint);
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

	@Override
	public void validerProduitPourCommande(String nomUtilisateur,
			String idProduits, int quantite) {
		WebApplicationFournisseurStub stub;
		try {
			stub = new WebApplicationFournisseurStub(fournisseurEndPoint);
			
			ReserverProduit requete = new ReserverProduit();
			requete.setId(idProduits);
			requete.setQuantite(quantite);
			ReserverProduitResponse reponse = stub.reserverProduit(requete);
			String idReservation = reponse.get_return();
			if(reservations.containsKey(nomUtilisateur)){
				reservations.get(nomUtilisateur).add(idReservation);
			}
			else{
				HashSet<String> temp = new HashSet<String>();
				temp.add(idReservation);
				reservations.put(nomUtilisateur, temp);
			}

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void annulerCommandeEnCours(String nomUtilisateur) {
		WebApplicationFournisseurStub stub;
		try {
			stub = new WebApplicationFournisseurStub(fournisseurEndPoint);
			for(String s : this.reservations.get(nomUtilisateur)){
				AnnulerReservation requete = new AnnulerReservation();
				requete.setId(s);
				
				stub.annulerReservation(requete);
				this.reservations.get(nomUtilisateur).remove(s);
				if(this.reservations.get(nomUtilisateur).isEmpty()){
					this.reservations.remove(nomUtilisateur);
				}
			}
		}
		catch(AxisFault e){
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public String effectuerPaiement(String nomCrediteur, String nomDebiteur,
			double somme) {
		WebApplicationBanqueStub stubPaiement;
		WebApplicationFournisseurStub stubFournisseur;
		String idCommande = null;
		try {
			stubPaiement = new WebApplicationBanqueStub(banqueEndPoint);
			stubFournisseur = new WebApplicationFournisseurStub(fournisseurEndPoint);
			
			Payer requete = new Payer();
			requete.setCompteCredit(nomCrediteur);
			requete.setCompteDebit(nomDebiteur);
			requete.setSomme(somme);
			
			stubPaiement.payer(requete);
			
			idCommande = UUID.randomUUID().toString();
			
			List<IProduit> p = new ArrayList<IProduit>();
			for(String s : reservations.get(nomCrediteur)){
				GetReservation request = new GetReservation();
				request.setId(nomCrediteur);
				GetReservationResponse response = stubFournisseur.getReservation(request);
				p.add(response.get_return());
			}
			this.commandes.put(idCommande, p);
			this.reservations.remove(nomCrediteur);
			
		}
		catch(AxisFault e){
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idCommande;
	}

	@Override
	public void creerClient(String name) {
		clients.add(name);
	}

	@Override
	public void supprimerCLient(String name) {
		clients.remove(name);
	}

}
