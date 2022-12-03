package ro.tuc.ds2022.dtos.builders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.ObjenesisException;
import org.springframework.stereotype.Component;
import ro.tuc.ds2022.dtos.ClientDto;
import ro.tuc.ds2022.entities.Client;
import ro.tuc.ds2022.entities.User;
import ro.tuc.ds2022.repositories.UserRepository;

@Component
public class ClientBuilder {

    private static UserRepository userRepository;

    public ClientBuilder(UserRepository userRepository){
        ClientBuilder.userRepository = userRepository;
    }

    public static ClientDto toClientDto(Client client){
        return new ClientDto(client.getId(), client.getUser().getId(), client.getFirstName(), client.getLastName(), client.getAddress());
    }

    public static Client toEntity(ClientDto clientDto){
        User user = userRepository.findById(clientDto.getUserId())
                .orElseThrow(() -> new ObjenesisException(String.valueOf(clientDto.getUserId())));
        return new Client(user, clientDto.getFirstName(), clientDto.getLastName(), clientDto.getAddress());
    }
}
