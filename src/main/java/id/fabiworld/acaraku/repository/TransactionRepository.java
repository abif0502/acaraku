package id.fabiworld.acaraku.repository;

import id.fabiworld.acaraku.model.Client;
import id.fabiworld.acaraku.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.status='ACTIVE'")
    List<Transaction> findAllActive();

    @Query("SELECT t FROM Transaction t WHERE t.client=?1")
    List<Transaction> findByClient(Client client);
}
