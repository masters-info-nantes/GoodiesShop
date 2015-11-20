package fr.services.goodies.boutique.event.examples;

import java.io.Serializable;
import java.util.Map;

import fr.services.goodies.fournisseur.Produit;

/**
 * Idée d'événement qui pourrait être généré quand une commande est validée.
 * @author hector
 *
 */
public class CommandePassee implements Serializable {
	
	public static final String STREAM_NAME = "boutique";
	public static final String EVENT_TYPE = "achat";

	private static final long serialVersionUID = 4439038852906781357L;
	
	/**
	 * Chaque produit acheté avec sa quantité associée.
	 */
	public Map<Produit, Integer> articles;
	
	public int montant;
	public String devise;
}
