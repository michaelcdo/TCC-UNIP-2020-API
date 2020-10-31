package br.unip.chacara.EIAAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unip.chacara.EIAAPI.model.Humor;
import br.unip.chacara.EIAAPI.model.User;

@Repository
public interface HumorRepository extends JpaRepository<Humor, Long>{

	Optional<List<Humor>> findByIdUser(Long idUser);

}
