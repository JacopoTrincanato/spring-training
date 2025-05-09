package org.training.spring.devices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.spring.devices.enums.TypeEnum;
import org.training.spring.devices.models.Device;
import org.training.spring.devices.repositories.DeviceRepository;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    public List<Device> getDevices() {
        return deviceRepository.findAll();
    }

    public Device getDeviceById(Long id) {
        Optional<Device> deviceAttempt = deviceRepository.findById(id);
        if (deviceAttempt.isEmpty()) {
            throw new IllegalArgumentException("Device not found");
        }
        return deviceAttempt.get();
    }

    public List<Device> searchByNameOrType(String query) {
        try {
            TypeEnum type = TypeEnum.valueOf(query.toUpperCase());
            return deviceRepository.findByType(type);
        } catch (IllegalArgumentException ex) {
            return deviceRepository.findByNameContainingIgnoreCase(query);
        }
    }

    public Device addDevice(Device device) {
        return deviceRepository.save(device);
    }

    public Device updateDevice(Device device) {
        return deviceRepository.save(device);
    }

    public void deleteDevice(Device deviceToDelete) {
        deviceRepository.delete(deviceToDelete);
    }
}
