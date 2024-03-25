package metier.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SERIE")
public class Serie implements Serializable{
	
	@Id
	@Column (name="ID_S")
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long idS;
	
	@Column (name="NOM_S")
	private String nomS;
	
	@Column (name="NB_S")
	private double NbS;
	
	public Serie() {
	super();
	}

	public Serie(String nomS, double nbS) {
		super();
		this.nomS = nomS;
		NbS = nbS;
	}
	
	public Serie(String nomS) {
		super();
		this.nomS = nomS;
	}

	public Long getIdS() {
		return idS;
	}

	public void setIdS(Long idS) {
		this.idS = idS;
	}

	public String getNomS() {
		return nomS;
	}

	public void setNomS(String nomS) {
		this.nomS = nomS;
	}

	public double getNbS() {
		return NbS;
	}

	public void setNbS(double nbS) {
		NbS = nbS;
	}

	@Override
	public String toString() {
		return "Serie [idS=" + idS + ", nomS=" + nomS + ", NbS=" + NbS + "]";
	}	
	
}
