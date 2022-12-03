package ro.tuc.ds2022.controllers;


import co.elastic.clients.elasticsearch.nodes.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2022.dtos.ClientDto;
import ro.tuc.ds2022.entities.Client;
import ro.tuc.ds2022.service.ClientService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/all")
    public ResponseEntity<?> getClients(){
        return new ResponseEntity<>(clientService.getAllClients(), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createClient(@Valid @RequestBody ClientDto clientDto){
        Long idClient = clientService.createClient(clientDto);
        return new ResponseEntity<>("Client created with id " + idClient, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClient(@PathVariable(name = "id") Long clientId) throws Exception {
        ClientDto clientDto = clientService.getClientById(clientId);
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateClient(@PathVariable(name = "id") Long clientId,
                                          @Valid @RequestBody ClientDto clientDto){
        ClientDto updatedClient = clientService.updateClient(clientId, clientDto);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable(name = "id") Long clientId){
        clientService.deleteClient(clientId);
        return new ResponseEntity<>("Client deleted!", HttpStatus.OK);
    }





}
