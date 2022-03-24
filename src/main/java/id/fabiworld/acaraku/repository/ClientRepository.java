package id.fabiworld.acaraku.repository;

import id.fabiworld.acaraku.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
