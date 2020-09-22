package br.unip.chacara.EIAAPI.controller.dto;

public class MensagemWatsonDTO {
	private long idUser;
	private String mensagemEntrada;
	private String mensagemRetorno;
	
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
	public String getMensagemRetorno() {
		return mensagemRetorno;
	}
	public void setMensagemRetorno(String mensagemRetorno) {
		this.mensagemRetorno = mensagemRetorno;
	}
	
	
}
