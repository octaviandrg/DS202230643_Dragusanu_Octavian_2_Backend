package ro.tuc.ds2022.service;

import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2022.dtos.ConsumptionDto;
import ro.tuc.ds2022.dtos.builders.ConsumptionBuilder;
import ro.tuc.ds2022.entities.Consumption;
import ro.tuc.ds2022.repositories.ConsumptionRepository;
import ro.tuc.ds2022.repositories.DeviceRepository;
import ro.tuc.ds2022.repositories.UserRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsumptionService {

    @Autowired
    private ConsumptionRepository consumptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    public List<ConsumptionDto> getAllConsumptions(){
        consumptionRepository.deleteAll();
        return consumptionRepository.findAll().stream().map(ConsumptionBuilder::toConsumptionDto).collect(Collectors.toList());
    }

    public void deleteConsumption(Long id){
        consumptionRepository.deleteById(id);
    }

    public Long createConsumption(ConsumptionDto consumptionDto){
        return consumptionRepository.save(ConsumptionBuilder.toEntity(consumptionDto)).getId();
    }

//    public List<ConsumptionDto> getConsumptionsByUsername(String username){
//        Long userId = userRepository.findByUsername(username).getId();
//        Long deviceId = deviceRepository.findAllByUserId(userId);
//    }

    public List<ConsumptionDto> getConsumptionsByDeviceId(Long deviceId, LocalDate date){

        List<ConsumptionDto> consumptionDtos =  consumptionRepository.findAllByDeviceId(deviceId).stream().map(ConsumptionBuilder::toConsumptionDto).collect(Collectors.toList());
        List<ConsumptionDto> result = new ArrayList<>();
        for(ConsumptionDto consumptionDto : consumptionDtos){
            if(consumptionDto.getTimestamp().toInstant().atZone(ZoneId.of("UTC")).toLocalDate().equals(date)){
                result.add(ConsumptionBuilder.toConsumptionDtoHour(consumptionDto));
            }
        }
        return result;
    }


}
