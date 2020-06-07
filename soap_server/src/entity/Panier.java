package entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Panier")
public class Panier implements Serializable {
	private Long id;
	private String NomProduit;
	private String image;
	private double prix;
	private int qunatite;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getNomProduit() {
		return NomProduit;
	}
	public void setNomProduit(String nomProduit) {
		NomProduit = nomProduit;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public int getQunatite() {
		return qunatite;
	}
	public void setQunatite(int qunatite) {
		this.qunatite = qunatite;
	}
	public Panier(String nomProduit, String image, double prix, int qunatite) {
		super();
		NomProduit = nomProduit;
		this.image = image;
		this.prix = prix;
		this.qunatite = qunatite;
	}
	public Panier() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Panier [id=" + id + ", NomProduit=" + NomProduit + ", image=" + image + ", prix=" + prix + ", qunatite="
				+ qunatite + "]";
	}
}
