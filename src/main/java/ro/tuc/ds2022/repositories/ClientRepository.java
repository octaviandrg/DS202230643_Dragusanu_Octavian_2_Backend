package ro.tuc.ds2022.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuc.ds2022.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
