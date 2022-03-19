package id.fabiworld.pestaku.repository;

import id.fabiworld.pestaku.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
