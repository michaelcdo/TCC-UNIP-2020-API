package br.unip.chacara.EIAAPI.controller.dto;

import java.util.List;

public class MensagemWatsonDTO {
	private long idUser;
	private String mensagemEntrada;
	private List<String> mensagemRetorno;
	private int codRetorno;
	
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUsuario) {
		this.idUser = idUsuario;
	}
	public String  getMensagemEntrada() {
		return mensagemEntrada;
	}
	public void setMensagemEntrada(String mensagemEntrada) {
		this.mensagemEntrada = mensagemEntrada;
	}
	public List<String> getMensagemRetorno() {
		return mensagemRetorno;
	}
	public void setMensagemRetorno(List<String> mensagemRetorno) {
		this.mensagemRetorno = mensagemRetorno;
	}
	public int getCodRetorno() {
		return codRetorno;
	}
	public void setCodRetorno(int codRetorno) {
		this.codRetorno = codRetorno;
	}
}
