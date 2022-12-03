package ro.tuc.ds2022.dtos.builders;

import org.elasticsearch.common.recycler.Recycler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.ObjenesisException;
import org.springframework.stereotype.Component;
import ro.tuc.ds2022.dtos.ConsumptionDto;
import ro.tuc.ds2022.entities.Consumption;
import ro.tuc.ds2022.entities.Device;
import ro.tuc.ds2022.repositories.ConsumptionRepository;
import ro.tuc.ds2022.repositories.DeviceRepository;

@Component
public class ConsumptionBuilder {

    private static DeviceRepository deviceRepository;

    public ConsumptionBuilder(DeviceRepository deviceRepository) {
        ConsumptionBuilder.deviceRepository = deviceRepository;
    }

    public static ConsumptionDto toConsumptionDto(Consumption consumption){
        return new ConsumptionDto(consumption.getId(), consumption.getDevice().getId(), consumption.getTimestamp(), consumption.getValue());
    }

    public static ConsumptionDto toConsumptionDtoHour(ConsumptionDto consumption){
        return new ConsumptionDto(consumption.getId(), consumption.getDeviceId(), consumption.getTimestamp().getHours(), consumption.getValue());
    }

    public static Consumption toEntity(ConsumptionDto consumptionDto) {
        Device device = deviceRepository.findById(consumptionDto.getDeviceId())
                .orElseThrow(() -> new ObjenesisException(String.valueOf(consumptionDto.getDeviceId())));
        return new Consumption(device, consumptionDto.getTimestamp(), consumptionDto.getValue());
    }
}
