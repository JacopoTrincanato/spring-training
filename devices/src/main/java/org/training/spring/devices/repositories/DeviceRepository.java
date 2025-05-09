package org.training.spring.devices.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.spring.devices.enums.TypeEnum;
import org.training.spring.devices.models.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByNameContainingIgnoreCase(String name);

    List<Device> findByType(TypeEnum type);

}
