package ro.tuc.ds2022.dtos.builders;

import org.springframework.objenesis.ObjenesisException;
import org.springframework.stereotype.Component;
import ro.tuc.ds2022.dtos.DeviceDto;
import ro.tuc.ds2022.entities.Device;
import ro.tuc.ds2022.entities.User;
import ro.tuc.ds2022.repositories.UserRepository;

@Component
public class DeviceBuilder {

    private static UserRepository userRepository;

    private DeviceBuilder(UserRepository userRepository){
       DeviceBuilder.userRepository = userRepository;
    }

    public static DeviceDto toDeviceDto(Device device){
        return new DeviceDto(device.getDescription(), device.getAddress(), device.getMaxConsumption(), device.getUser().getId(), device.getId());
    }

    public static Device toEntity(DeviceDto deviceDto){
        User user = userRepository.findById(deviceDto.getUserId())
                .orElseThrow(() -> new ObjenesisException(String.valueOf(deviceDto.getUserId())));
        return new Device(user, deviceDto.getDescription(), deviceDto.getAddress(), deviceDto.getMaxConsumption());
    }
}
