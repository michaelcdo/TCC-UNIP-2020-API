package br.unip.chacara.EIAAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unip.chacara.EIAAPI.controller.dto.MensagemWatsonDTO;
import br.unip.chacara.EIAAPI.service.WatsonService;

@RestController
public class EIAController {
	@Autowired
	private WatsonService watsonService;
	
	@RequestMapping("/watson")
	public MensagemWatsonDTO watson(@RequestBody MensagemWatsonDTO mensagem) {
		
		try {
			mensagem = watsonService.callWatson(mensagem);
		}catch (Exception e) {
			mensagem.setMensagemRetorno("Erro");
		}
		return mensagem;
	}
	
	
}
