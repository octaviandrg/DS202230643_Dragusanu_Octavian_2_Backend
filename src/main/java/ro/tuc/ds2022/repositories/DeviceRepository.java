package ro.tuc.ds2022.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuc.ds2022.entities.Device;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    List<Device> findAllByUserId(Long userId);

}
