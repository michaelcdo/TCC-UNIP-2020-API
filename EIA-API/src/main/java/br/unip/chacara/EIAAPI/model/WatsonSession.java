package br.unip.chacara.EIAAPI.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_watsonSession")
public class WatsonSession {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="id_user")
	private Long idUser;
	
	@Column(name="session_hash")
	private String sessionHash;
	
	@Column(name="dt_session")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtSession;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getSessionHash() {
		return sessionHash;
	}

	public void setSessionHash(String sessionHash) {
		this.sessionHash = sessionHash;
	}

	public Date getDtSession() {
		return dtSession;
	}

	public void setDtSession(Date dtSession) {
		this.dtSession = dtSession;
	}	
}
