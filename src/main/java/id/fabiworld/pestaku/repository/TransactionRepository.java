package id.fabiworld.pestaku.repository;

import id.fabiworld.pestaku.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
