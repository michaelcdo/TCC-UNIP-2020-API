package br.unip.chacara.EIAAPI.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unip.chacara.EIAAPI.controller.dto.HumorDTO;
import br.unip.chacara.EIAAPI.controller.dto.MensagemWatsonDTO;
import br.unip.chacara.EIAAPI.controller.dto.UserDTO;
import br.unip.chacara.EIAAPI.model.Humor;
import br.unip.chacara.EIAAPI.model.User;
import br.unip.chacara.EIAAPI.service.HumorService;
import br.unip.chacara.EIAAPI.service.UserService;
import br.unip.chacara.EIAAPI.service.WatsonService;


@RestController
public class EIAController {
	@Autowired
	private WatsonService watsonService;
	
	@Autowired
	private HumorService humorService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/watson")
	public MensagemWatsonDTO watson(@RequestBody MensagemWatsonDTO mensagem) {
		
		try {
			mensagem.setIdUser(validaId(mensagem.getIdUser()));
			mensagem = watsonService.callWatson(mensagem);
			mensagem.setCodRetorno(0);
		}catch (Exception e) {
			mensagem.setCodRetorno(99);
		}
		return mensagem;
	}
	
	@RequestMapping("/humor/cadastro")
	public HumorDTO gravaHumor(@RequestBody HumorDTO humor) {
		
		try {
			humor.setIdUser(validaId(humor.getIdUser()));
			Humor hm = new Humor();
			hm.setCodHumor(humor.getCodHumor());
			hm.setDtGravacao(new Date());
			hm.setIdUser(humor.getIdUser());
			humor.setDtGravacao(hm.getDtGravacao());
			hm = humorService.saveHumor(hm);
		}catch (Exception e) {
			humor.setCodRetorno(99);
		}
		
		return humor;
	}
	
	@RequestMapping("/humor/lista")
	public List<HumorDTO> obtemHumores(@RequestBody UserDTO user){
		ArrayList<HumorDTO> humoresDTO = new ArrayList<>();
		try {
			user.setId(validaId(user.getId()));
			List<Humor> humores = humorService.getHumorByIdUser(user.getId());
			if(humores.size()>=6) {
				for (int i = humores.size()-6; i < humores.size(); i++) {				
					humoresDTO.add(HumorDTO.fromModel(humores.get(i)));
				}
			}else {
				if(humores.size() ==1){
					HumorDTO hm = new HumorDTO();
					hm.setCodHumor(0);
					hm.setCodRetorno(99);
					hm.setDtGravacao(new Date());
					humoresDTO.add(hm);
				}
				for (int i = 0; i < humores.size(); i++) {				
					humoresDTO.add(HumorDTO.fromModel(humores.get(i)));
				}
			}
		} catch (Exception e) {
			return null;
		}
		return humoresDTO;
	}
	
	@RequestMapping("/user/cadastro")
	public UserDTO gravaUser(@RequestBody UserDTO user){
		try {
			User us = UserDTO.toModel(user);
			if(us.getId()>0) {
				us = userService.updateUser(us);
			}else {
				us = userService.saveUser(us);
			}
			user = UserDTO.fromModel(us);
		} catch (Exception e) {
			return null;
		}
		return user;
	}
	
	private long validaId(long id){
		if(id<=0) {
			User user = userService.saveUser(new User());
			return user.getId();
		}
		return id;
	}
	
}
