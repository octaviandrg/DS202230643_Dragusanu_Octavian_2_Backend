package ro.tuc.ds2022.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2022.dtos.DeviceDto;
import ro.tuc.ds2022.service.DeviceService;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/all")
    public ResponseEntity<?> getDevices(){
        return new ResponseEntity<>(deviceService.getAllDevices(), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createDevice(@Valid @RequestBody DeviceDto deviceDto){
        Long id = deviceService.createDevice(deviceDto);
        return new ResponseEntity<>("Device created with id " + id, HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getDevice(@PathVariable(name = "id") long deviceId) throws Exception {
        DeviceDto deviceDto = deviceService.getDeviceById(deviceId);
        return new ResponseEntity<>("Device found!\n" + deviceDto, HttpStatus.OK);
    }

    @GetMapping(value = "/byUsername/{username}")
    public ResponseEntity<?> getDeviceByUsername(@PathVariable(name = "username") String username) throws Exception {
        return new ResponseEntity<>(deviceService.getDevicesByUsername(username), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateDevice(@PathVariable(name = "id") long deviceId,
                                          @Valid @RequestBody DeviceDto deviceDto){
        DeviceDto updatedDevice = deviceService.updateDevice(deviceId, deviceDto);
        return new ResponseEntity<>("Device updated!\n" + updatedDevice, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteDevice(@PathVariable(name = "id") long deviceId){
        deviceService.deleteDevice(deviceId);
        return new ResponseEntity<>("Device deleted!", HttpStatus.OK);
    }


}
