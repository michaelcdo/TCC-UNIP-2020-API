package br.unip.chacara.EIAAPI.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.unip.chacara.EIAAPI.model.WatsonSession;

@Repository
public interface WatsonSessionRepository extends JpaRepository<WatsonSession, Long>{

	Optional<WatsonSession> findByIdUser(Long idUser);

}
