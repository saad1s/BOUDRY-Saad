package entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Produit")
public class Produit {

	private Long id;
	private String nomPrdt;
	private String designation ; 
	private double prix;
	private int quantite;
	private String image;
	private String categorie;
	//private Blob images;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomPrdt() {
		return nomPrdt;
	}
	public void setNomPrdt(String nomPrdt) {
		this.nomPrdt = nomPrdt;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
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
	
	
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public Produit(String nomPrdt, String designation, double prix, int quantite, String image, String categorie) {
		super();
		this.nomPrdt = nomPrdt;
		this.designation = designation;
		this.prix = prix;
		this.quantite = quantite;
		this.image = image;
		this.categorie = categorie;
	}
	public Produit() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Produit [id=" + id + ", nomPrdt=" + nomPrdt + ", designation=" + designation + ", prix=" + prix
				+ ", quantite=" + quantite + ", image=" + image + ", categorie=" + categorie + "]";
	}
}	
	
	 
	
	
	
	
	

