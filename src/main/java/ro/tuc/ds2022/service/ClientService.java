package ro.tuc.ds2022.service;

import org.elasticsearch.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2022.dtos.ClientDto;
import ro.tuc.ds2022.dtos.builders.ClientBuilder;
import ro.tuc.ds2022.entities.Client;
import ro.tuc.ds2022.repositories.ClientRepository;
import ro.tuc.ds2022.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ClientDto> getAllClients(){
        return clientRepository.findAll().stream().map(ClientBuilder::toClientDto).collect(Collectors.toList());
    }

    public Long createClient(ClientDto clientDto) {
        Client client = ClientBuilder.toEntity(clientDto);
        return clientRepository.save(client).getId();
    }

    public ClientDto getClientById(Long id) throws Exception {
        Optional<Client> client = clientRepository.findById(id);
        if(client.isEmpty()){
            throw new Exception("Client with id " + id + " does not exist");
        }
        return ClientBuilder.toClientDto(client.get());
    }

    public ClientDto updateClient(Long clientId, ClientDto clientDto){
        Client client = clientRepository.findById(clientId).orElseThrow(() ->  new ResourceNotFoundException("Client", "id", clientId));
        client.setAddress(clientDto.getAddress());
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setUser(userRepository.findById(clientDto.getUserId()).get());

        Client updatedClient = clientRepository.save(client);
        return new ClientDto(updatedClient.getUser().getId(), updatedClient.getFirstName(), updatedClient.getLastName(), updatedClient.getAddress());
    }

    public void deleteClient(Long clientId){
        Client client = clientRepository.findById(clientId).orElseThrow(() ->  new ResourceNotFoundException("Client", "id", clientId));
        clientRepository.delete(client);
    }
}
