package org.training.spring.devices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.training.spring.devices.models.Device;
import org.training.spring.devices.services.DeviceService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("devices", deviceService.getDevices());
        return "devices/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        Device device = deviceService.getDeviceById(id);
        model.addAttribute("device", device);
        return "devices/show";
    }

    @GetMapping("/searchDevice")
    public String searchDevice(@RequestParam(name = "name") String query, Model model) {
        List<Device> devices = deviceService.searchByNameOrType(query);
        model.addAttribute("devices", devices);
        return "devices/index";
    }

    @GetMapping("/create")
    public String createDevive(Model model) {
        Device newDevice = new Device();
        model.addAttribute("device", newDevice);
        return "devices/create";
    }

    @PostMapping("/create")
    public String storeDevice(@Valid @ModelAttribute("device") Device deviceForm, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "devices/create";
        }

        deviceService.addDevice(deviceForm);

        return "redirect:/devices";
    }

    @GetMapping("/edit/{id}")
    public String editDevice(@PathVariable Long id, Model model) {
        Device deviceToEdit = deviceService.getDeviceById(id);
        model.addAttribute("device", deviceToEdit);
        return "devices/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateDevice(@Valid @ModelAttribute("device") Device deviceForm, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "devices/edit";
        }

        deviceService.updateDevice(deviceForm);

        return "redirect:/devices";
    }

    @PostMapping("/delete/{id}")
    public String deleteDevice(@PathVariable Long id) {
        Device deviceToDelete = deviceService.getDeviceById(id);

        deviceService.deleteDevice(deviceToDelete);

        return "redirect:/devices";
    }
}
