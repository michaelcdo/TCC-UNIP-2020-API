package br.unip.chacara.EIAAPI.controller.dto;

import javax.persistence.Column;

import br.unip.chacara.EIAAPI.model.Humor;
import br.unip.chacara.EIAAPI.model.User;

public class UserDTO {

	private long id;
	private String nome;
	private String email;
	private String telefone;
	private String sexo;
	private int idade;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public static UserDTO fromModel(User us) {
    	UserDTO user = new UserDTO();
    	user.setEmail(us.getEmail());
    	user.setId(us.getId());
    	user.setIdade(us.getIdade());
    	user.setNome(us.getNome());
    	user.setTelefone(us.getTelefone());
    	user.setSexo(us.getSexo());
    	return user;
    }
    public static User toModel(UserDTO us) {
    	User user = new User();
    	user.setEmail(us.getEmail());
    	user.setId(us.getId());
    	user.setIdade(us.getIdade());
    	user.setNome(us.getNome());
    	user.setTelefone(us.getTelefone());
    	user.setSexo(us.getSexo());
    	return user;
    }
}
