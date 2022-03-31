package net.identityservice.springboot.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import net.identityservice.springboot.model.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {

}
