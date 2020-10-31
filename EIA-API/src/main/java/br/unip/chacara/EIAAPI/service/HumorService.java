package br.unip.chacara.EIAAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.chacara.EIAAPI.model.Humor;
import br.unip.chacara.EIAAPI.repository.HumorRepository;

@Service
public class HumorService {
	@Autowired
	private HumorRepository repository;
	
	public Humor saveHumor(Humor humor) {
		return repository.save(humor);
	}
	public List<Humor> saveHumors(List<Humor> humor) {
		return repository.saveAll(humor);
	}
	
	public List<Humor> getHumors(){
		return repository.findAll();
	}
	
	public Humor getHumorById(Long id){
		return repository.findById(id).orElse(null);
	}
	
	public List<Humor> getHumorByIdUser(Long idUser){
		return repository.findByIdUser(idUser).orElse(null);
	}
	
	public void deleteHumor(Long id) {
		repository.deleteById(id);
	}
}
