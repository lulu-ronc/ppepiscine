package model;

import java.sql.Date;

public class Ticket {
	private int id_ticket;
	private int id_formule;
	private int id_achat;
	private Date date_debut;
	private int quantite;
	private int code_validation;
	
	public Ticket(int id_ticket, int id_formule, int id_achat,  Date date_debut,int quantite, int code) {
		this.id_ticket = id_ticket;
		this.id_formule = id_formule;
		this.id_achat = id_achat;
		this.date_debut = date_debut;
		this.quantite = quantite;
		this.code_validation = code;
	}

	public int getId_ticket() {
		return id_ticket;
	}

	public void setId_ticket(int id_ticket) {
		this.id_ticket = id_ticket;
	}

	public int getId_formule() {
		return id_formule;
	}

	public void setId_formule(int id_formule) {
		this.id_formule = id_formule;
	}

	public int getId_achat() {
		return id_achat;
	}

	public void setId_achat(int id_achat) {
		this.id_achat = id_achat;
	}


	public Date getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getCode_validation() {
		return code_validation;
	}

	public void setCode_validation(int code_validation) {
		this.code_validation = code_validation;
	}
	
}
