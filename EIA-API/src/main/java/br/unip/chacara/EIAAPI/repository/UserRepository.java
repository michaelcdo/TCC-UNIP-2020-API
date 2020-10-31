package br.unip.chacara.EIAAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unip.chacara.EIAAPI.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByNome(String nome);

}
