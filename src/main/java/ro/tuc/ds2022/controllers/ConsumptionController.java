package ro.tuc.ds2022.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2022.dtos.ConsumptionDto;
import ro.tuc.ds2022.service.ConsumptionService;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@CrossOrigin
@RequestMapping(value = "/consumption")
public class ConsumptionController {

    @Autowired
    private ConsumptionService consumptionService;

    @GetMapping("/all")
    public ResponseEntity<?> getConsumptions(){
        return new ResponseEntity<>(consumptionService.getAllConsumptions(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createConsumption(@Valid @RequestBody ConsumptionDto consumptionDto){
        Long idClient = consumptionService.createConsumption(consumptionDto);
        return new ResponseEntity<>("Consumption created with id " + idClient, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/deviceId/{id}/{d}/{m}/{y}")
    public ResponseEntity<?> getConsumptionsByDeviceId(@PathVariable(name = "id") Long deviceId,
                                                       @PathVariable(name = "d") int day,
                                                       @PathVariable(name = "m") int month,
                                                       @PathVariable(name = "y") int year ) throws Exception {
        LocalDate date = LocalDate.of(year, month, day);
        return new ResponseEntity<>(consumptionService.getConsumptionsByDeviceId(deviceId, date), HttpStatus.OK);
    }

//    @GetMapping("/{username}")
//    public ResponseEntity<?> getConsumptionsByUsername(@PathVariable(name = "username") String username) throws Exception {
//        return new ResponseEntity<>(consumptionService.getConsumptionsByUsername(username), HttpStatus.OK);
//    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> updateConsumption(@PathVariable(name = "id") Long clientId,
//                                          @Valid @RequestBody ClientDto clientDto){
//        ClientDto updatedClient = consumptionService.updateClient(clientId, clientDto);
//        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
//    }
//

    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteConsumption(@PathVariable(name = "id") Long consumptionId){
        consumptionService.deleteConsumption(consumptionId);
        return new ResponseEntity<>("consumption deleted!", HttpStatus.OK);
    }





}
