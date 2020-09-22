package br.unip.chacara.EIAAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.chacara.EIAAPI.model.WatsonSession;
import br.unip.chacara.EIAAPI.repository.WatsonSessionRepository;

@Service
public class WatsonSessionService {

	@Autowired
	private WatsonSessionRepository repository;
	
	public WatsonSession getWatsonSessionByIsUser(Long idUser){
		return repository.findByIdUser(idUser).orElse(null);
	}
	
	public WatsonSession saveWatsonSession(WatsonSession watsonSession){
		return repository.save(watsonSession);
	}
	
	public WatsonSession updateUser(WatsonSession watsonSession) {
		WatsonSession existingWatsonSession = repository.findById(watsonSession.getId()).orElse(watsonSession);

		existingWatsonSession.setDtSession(watsonSession.getDtSession());
		existingWatsonSession.setIdUser(watsonSession.getIdUser());
		existingWatsonSession.setSessionHash(watsonSession.getSessionHash());
		
		return repository.save(existingWatsonSession);
	}
}
