package model;

import java.sql.Date;

public class AchatM {
	private int id_achat;
	private int id_piscine;
	private int montant;
	private Date date_achat;
	
	public int getId_achat() {
		return id_achat;
	}

	public void setId_achat(int id_achat) {
		this.id_achat = id_achat;
	}

	public int getId_piscine() {
		return id_piscine;
	}

	public void setId_piscine(int id_piscine) {
		this.id_piscine = id_piscine;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public Date getDate_achat() {
		return date_achat;
	}

	public void setDate_achat(Date date_achat) {
		this.date_achat = date_achat;
	}

	
	
	public AchatM(int id_achat, int id_piscine, int montant, Date date_achat) {
		this.id_achat= id_achat;
		this.id_piscine= id_piscine;
		this.montant= montant;
		this.date_achat= date_achat;
		
	}
}
