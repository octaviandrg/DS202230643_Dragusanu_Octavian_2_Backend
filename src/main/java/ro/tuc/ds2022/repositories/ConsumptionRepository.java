package ro.tuc.ds2022.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuc.ds2022.entities.Consumption;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface ConsumptionRepository extends JpaRepository<Consumption, Long> {
    List<Consumption> findAllByDeviceId(Long deviceId);


}
