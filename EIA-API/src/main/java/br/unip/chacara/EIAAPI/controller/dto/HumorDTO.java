package br.unip.chacara.EIAAPI.controller.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.unip.chacara.EIAAPI.model.Humor;

public class HumorDTO {

    private long idUser;
    private int codHumor;
    private Date dtGravacao;
    private int codRetorno;

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public int getCodHumor() {
        return codHumor;
    }

    public void setCodHumor(int codHumor) {
        this.codHumor = codHumor;
    }

    public Date getDtGravacao() {
        return dtGravacao;
    }

    public void setDtGravacao(Date dtGravacao) {
        this.dtGravacao = dtGravacao;
    }

    public int getCodRetorno() {
        return codRetorno;
    }

    public void setCodRetorno(int codRetorno) {
        this.codRetorno = codRetorno;
    }
    public static HumorDTO fromModel(Humor hm) {
    	HumorDTO humor = new HumorDTO();
    	humor.setCodHumor(hm.getCodHumor());
    	humor.setCodRetorno(0);
    	humor.setDtGravacao(hm.getDtGravacao());
    	humor.setIdUser(hm.getIdUser());
    	return humor;
    }
}
