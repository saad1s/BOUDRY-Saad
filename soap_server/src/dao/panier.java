package dao;

import java.util.List;

import entity.Produit;

public class panier {

	public static void main(String[] args) {
		Fonctions ft =new Fonctions();
		List<Produit> p = ft.getPanier();
		 for(Produit s:p) {
				System.out.println(s.getPrix());
			}

	}

}
