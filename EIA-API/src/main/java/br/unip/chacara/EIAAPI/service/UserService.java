package br.unip.chacara.EIAAPI.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import br.unip.chacara.EIAAPI.model.User;
import br.unip.chacara.EIAAPI.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	public User saveUser(User user) {
		return repository.save(user);
	}
	public List<User> saveUsers(List<User> user) {
		return repository.saveAll(user);
	}
	
	public List<User> getUsers(){
		return repository.findAll();
	}
	
	public User getUserById(Long id){
		return repository.findById(id).orElse(null);
	}
	
	public User getUserByNome(String nome){
		return repository.findByNome(nome).orElse(null);
	}
	
	public void deleteUser(Long id) {
		repository.deleteById(id);
	}
	
	public User updateUser(User user) {
		User existingUser = repository.findById(user.getId()).orElse(user);
		existingUser.setNome(user.getNome());
		existingUser.setEmail(user.getEmail());
		return repository.save(existingUser);
	}
}
