package model;

public class Bassins {
	private int id_bassin;
	private int id_piscine;
	private String nom;
	private int places;
	
	public Bassins(int id_bassin, int id_piscine, String nom, int places) {
		this.id_bassin=id_bassin;
		this.id_piscine = id_piscine;
		this.nom = nom;
		this.places = places;
	}

	public int getId_bassin() {
		return id_bassin;
	}

	public void setId_bassin(int id_bassin) {
		this.id_bassin = id_bassin;
	}

	public int getId_piscine() {
		return id_piscine;
	}

	public void setId_piscine(int id_piscine) {
		this.id_piscine = id_piscine;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPlaces() {
		return places;
	}

	public void setPlaces(int places) {
		this.places = places;
	}
	
	
}
