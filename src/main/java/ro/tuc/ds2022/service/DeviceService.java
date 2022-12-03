package ro.tuc.ds2022.service;

import org.elasticsearch.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2022.dtos.DeviceDto;
import ro.tuc.ds2022.dtos.builders.DeviceBuilder;
import ro.tuc.ds2022.entities.Device;
import ro.tuc.ds2022.repositories.DeviceRepository;
import ro.tuc.ds2022.repositories.UserRepository;

import javax.sound.midi.MidiDeviceReceiver;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UserRepository userRepository;


    public List<DeviceDto> getAllDevices(){
        return deviceRepository.findAll().stream().map(DeviceBuilder::toDeviceDto).collect(Collectors.toList());
    }

    public List<DeviceDto> getDevicesByUserId(Long userId){
        return deviceRepository.findAllByUserId(userId).stream().map(DeviceBuilder::toDeviceDto).collect(Collectors.toList());
    }

    public List<DeviceDto> getDevicesByUsername(String username){
        Long userId = userRepository.findByUsername(username).getId();
        return deviceRepository.findAllByUserId(userId).stream().map(DeviceBuilder::toDeviceDto).collect(Collectors.toList());
    }

    public Long createDevice(DeviceDto deviceDto){
        Device device = DeviceBuilder.toEntity(deviceDto);
        return deviceRepository.save(device).getId();
    }

    public DeviceDto getDeviceById(Long id) throws Exception {
        Optional<Device> device = deviceRepository.findById(id);
        if(device.isEmpty()){
            throw new Exception("Device with id " + id + " not found!");
        }
        return DeviceBuilder.toDeviceDto(device.get());
    }

    public DeviceDto updateDevice(Long deviceId, DeviceDto deviceDto){
        Device device = deviceRepository.findById(deviceId).orElseThrow(() ->  new ResourceNotFoundException("Device", "id", deviceId));
        device.setAddress(deviceDto.getAddress());
        device.setDescription(deviceDto.getDescription());
        device.setMaxConsumption(deviceDto.getMaxConsumption());
        device.setUser(userRepository.findById(deviceDto.getUserId()).get());

        Device updatedDevice = deviceRepository.save(device);
        return new DeviceDto(updatedDevice.getDescription(), updatedDevice.getAddress(), updatedDevice.getMaxConsumption(), updatedDevice.getUser().getId());
    }

    public void deleteDevice(Long deviceId){
        Device device = deviceRepository.findById(deviceId).orElseThrow(() ->  new ResourceNotFoundException("Device", "id", deviceId));
        deviceRepository.delete(device);
    }
}
