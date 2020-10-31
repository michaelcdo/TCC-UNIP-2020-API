package br.unip.chacara.EIAAPI.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_humor")
public class Humor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="cod_humor")
	private int codHumor;
	
	@Column(name="dt_gravacao")
	private Date dtGravacao;
	
	@Column(name="id_user")
	private Long idUser;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCodHumor() {
		return codHumor;
	}

	public void setCodHumor(int codHumod) {
		this.codHumor = codHumod;
	}

	public Date getDtGravacao() {
		return dtGravacao;
	}

	public void setDtGravacao(Date dtGravacao) {
		this.dtGravacao = dtGravacao;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
		
}
