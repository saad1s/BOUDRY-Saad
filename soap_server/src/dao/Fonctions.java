package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import entity.Panier;
import entity.Produit;
import entity.User;

@WebService(name="Fonctions")
public class Fonctions {
	//*****************************Fonctions du client***********************************
	
	@WebMethod(operationName="ajoutProduitAuPanierP")
	public Panier ajouterp(@WebParam(name="panierP") Panier P) {
		Connection connection=Base.getConnection();
		try { 
			PreparedStatement pd= connection.prepareStatement("INSERT INTO panier(nomProduit,prix,quantite,image)VALUE(?,?,?,?)");
			pd.setString(1, P.getNomProduit());
			pd.setDouble(2, P.getPrix());
			pd.setInt(3, P.getQunatite());
			pd.setString(4, P.getImage());
			pd.executeUpdate();
			
			pd.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return P;
		
	}
	@WebMethod(operationName="ChercherProduitP")
	public Produit chercherp(@WebParam(name="NomDuProduirP") String nom) {
		Produit a=null;
		Connection connection=Base.getConnection();
		try {
			PreparedStatement ps= connection.prepareStatement("select * from PRODUITS where nomProduit= ?");
			ps.setString(1,nom);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				a=new Produit();
				a.setPrix(rs.getDouble("prix"));
				a.setQuantite(rs.getInt("quantite"));
				a.setCategorie(rs.getString("categorie"));
				a.setNomPrdt(rs.getString("nomProduit"));
				a.setId(rs.getLong("id"));
				a.setDesignation(rs.getString("designation"));
				a.setImage(rs.getString("image"));
				//System.out.println("found");
			}
		} catch (SQLException e) {
			//System.out.println("not found");
			e.printStackTrace();
		}
		return a;
	}
	
	@WebMethod(operationName="SupprimerProduitDuPanier")
	public void supprimerp(@WebParam(name="NomDuProduirP")String nom) {
		//Produit p = chercher(nom);
		Connection connection=Base.getConnection();
	
			try {
				PreparedStatement ps1= connection.prepareStatement("delete from panier where nomProduit ='"+nom+"'");
				//PreparedStatement ps2= connection.prepareStatement("delete from PANIER where nomProduit ='"+nom+"'");
				ps1.executeUpdate();
				System.out.println("le livre  est spr");
				ps1.close();	
			} catch (SQLException e) {
				System.out.println("n est pas trouvé");
				e.printStackTrace();
			}
		}
	
	@WebMethod(operationName="SelectionnerLesProduitsParCategorie")
	public List<Produit> getProduitCategorie(@WebParam(name="NomDuCategorie")String cat) {
		Connection connection=Base.getConnection();
		List<Produit> rs = new ArrayList<Produit>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM produits WHERE categorie = ?");
			ps.setString(1,cat);
			ResultSet r = ps.executeQuery();
			while(r.next()) {
				Produit a = new Produit();
				a.setPrix(r.getDouble("prix"));
				a.setQuantite(r.getInt("quantite"));
				a.setCategorie(r.getString("categorie"));
				a.setNomPrdt(r.getString("nomProduit"));
				a.setId(r.getLong("id"));
				a.setDesignation(r.getString("designation"));
				a.setImage(r.getString("image"));
				rs.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	@WebMethod
	public List<Produit> getProduit() {
		Connection connection=Base.getConnection();
		List<Produit> rs = new ArrayList<Produit>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM produits ");
			ResultSet r = ps.executeQuery();
			while(r.next()) {
				Produit a = new Produit();
				a.setPrix(r.getDouble("prix"));
				a.setQuantite(r.getInt("quantite"));
				a.setCategorie(r.getString("categorie"));
				a.setNomPrdt(r.getString("nomProduit"));
				a.setId(r.getLong("id"));
				a.setDesignation(r.getString("designation"));
				a.setImage(r.getString("image"));
				rs.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	@WebMethod
	public List<Produit> getCategorie() {
		Connection connection=Base.getConnection();
		List<Produit> rs = new ArrayList<Produit>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT DISTINCT categorie FROM produits ");
			
			ResultSet r = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
	}
	@WebMethod
	public User sautehtifier(@WebParam(name="UserName")String username,@WebParam(name="Password") String Password) {
		User us=null;
		Connection connection=Base.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE username LIKE ? AND password LIKE ? ");
			ps.setString(1, username );
			ps.setString(2,Password);
			ResultSet u = ps.executeQuery();
			if(u.next()) {
				us = new User();
				us.setId(u.getInt("id"));
				us.setPassword(u.getString("password"));
				us.setUsername(u.getString("username"));
			}
		 
			ps.close();	
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return us;
		
	}
	@WebMethod
	public User sinscrire(@WebParam(name="UserU") User u) {
		Connection connection=Base.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("insert into users(username,password ,email) value(?,?,?)");
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getEmail());
			
			ps.executeUpdate();
			System.out.println("ca marche bien");
		} catch (SQLException e) {
			System.out.println("ca marche pas bien");

			e.printStackTrace();
		}
		return u;
		
	}
	@WebMethod
	public List<Produit> getPanier() {
		Connection connection=Base.getConnection();
		List<Produit> rs = new ArrayList<Produit>();
		try {
			PreparedStatement st = connection.prepareStatement("select * from panier");
			
			ResultSet r = st.executeQuery();
			while(r.next()) {
				Produit a = new Produit();
				a.setImage(r.getString("image"));
				a.setNomPrdt(r.getString("nomProduit"));
				a.setPrix(r.getDouble("prix"));
				a.setQuantite(r.getInt("quantite"));;
				rs.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	/*List<Produit> rs = new ArrayList<Produit>();
	try {
		PreparedStatement ps = connection.prepareStatement("SELECT * FROM produits ");
		ResultSet r = ps.executeQuery();
		while(r.next()) {
			Produit a = new Produit();
			a.setPrix(r.getDouble("prix"));
			a.setQuantite(r.getInt("quantite"));
			a.setCategorie(r.getString("categorie"));
			a.setNomPrdt(r.getString("nomProduit"));
			a.setId(r.getLong("id"));
			a.setDesignation(r.getString("designation"));
			a.setImage(r.getString("image"));
			rs.add(a);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return rs;
	*/
	@WebMethod
	public void sprPanier() {
		Connection connection=Base.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Panier");
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//*****************************Fonctions d'admin***********************************
	
	@WebMethod(operationName="AjouterProduitAuBase")
	public Produit ajouter(@WebParam(name="ProduitP") Produit P) {
		Connection connection=Base.getConnection();
		try {
			PreparedStatement pd= connection.prepareStatement("INSERT INTO PRODUITS(nomProduit,designation,prix,quantite,image,categorie)VALUE(?,?,?,?,?,?)");
			pd.setString(1, P.getNomPrdt());
			pd.setString(2, P.getDesignation());
			pd.setDouble(3, P.getPrix());
			pd.setInt(4, P.getQuantite());
			pd.setString(5, P.getImage());
			pd.setString(6, P.getCategorie());
			pd.executeUpdate();
			PreparedStatement pdt= connection.prepareStatement("SELECT MAX(ID) AS MAX_ID FROM PRODUITS");
			ResultSet rs =pdt.executeQuery();
			if(rs.next()) {
				P.setId(rs.getLong("MAX_ID"));
			}
			pd.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return P;
	}
	
	@WebMethod(operationName="ChercherProduitParadmin")
	public Produit chercher(@WebParam(name="NomDuProduit")String nom) {
		Produit a=null;
		Connection connection=Base.getConnection();
		try {
			PreparedStatement ps= connection.prepareStatement("select * from PRODUITS where nomProduit like ?");
			ps.setString(1,nom);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				a=new Produit();
				a.setPrix(rs.getDouble("prix"));
				a.setQuantite(rs.getInt("quantite"));
				a.setCategorie(rs.getString("categorie"));
				a.setNomPrdt(rs.getString("nomProduit"));
				a.setId(rs.getLong("id"));
				a.setDesignation(rs.getString("designation"));
				a.setImage(rs.getString("image"));
			}else {
				System.out.println("not found");

				return null ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return a;
		
	}
	@WebMethod(operationName="SupprimerProduitAuBaseParadmin")
	public void supprimer(@WebParam(name="NomDuProduit")String nom) {
		Produit p = chercher(nom);
		Connection connection=Base.getConnection();
		if(!p.equals(null)) {
			try {
				PreparedStatement ps1= connection.prepareStatement("delete from PRODUITS where nomProduit ='"+nom+"'");
				//PreparedStatement ps2= connection.prepareStatement("delete from PANIER where nomProduit ='"+nom+"'");
				ps1.executeUpdate();
				System.out.println("le livre  est spr");
				ps1.close();	
			} catch (SQLException e) {
				System.out.println("n est pas trouvé");
				e.printStackTrace();
			}
		}
	}
	
	@WebMethod(operationName="ModifierProduitAuBaseParadmin")
	public Boolean modifier(@WebParam(name="ProduitP")Produit p) {
		Connection connection=Base.getConnection();
		try {
			PreparedStatement pd= connection.prepareStatement("UPDATE PRODUITS set designation=?,prix=?,quantite=? VALUE(?,?,?)");
			pd.setString(1, p.getDesignation());
			pd.setDouble(2, p.getPrix());
			pd.setInt(3, p.getQuantite());
			pd.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@WebMethod
	public User sautehtifierAdmin(@WebParam(name="username")String username,@WebParam(name="Password") String Password) {
		User us=null;
		Connection connection=Base.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE username LIKE ? AND password LIKE ? ");
			ps.setString(1, username );
			ps.setString(2,Password);
			ResultSet u = ps.executeQuery();
			if(u.next()) {
				us = new User();
				us.setId(u.getInt("id"));
				us.setPassword(u.getString("password"));
				us.setUsername(u.getString("username"));
			}
		 
			ps.close();	
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return us;
	}
	
	@WebMethod
	public void sinscrireAdmin(@WebParam(name="UserU")User u) {
		Connection connection=Base.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("insert into users(username,password ,email) value(?,?,?)");
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getEmail());
			
			ps.executeUpdate();
			System.out.println("ca marche bien");
		} catch (SQLException e) {
			System.out.println("ca marche pas bien");

			e.printStackTrace();
		}

		
	}
}
