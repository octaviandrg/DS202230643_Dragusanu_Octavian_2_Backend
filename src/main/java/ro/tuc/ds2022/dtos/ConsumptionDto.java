package ro.tuc.ds2022.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ro.tuc.ds2022.entities.Device;

import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
public class ConsumptionDto {

    private Long id;

    private Long deviceId;

    private Date timestamp;

    private Double value;

    private int hour;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public ConsumptionDto(Long deviceId, Date timestamp, Double value) {
        this.deviceId = deviceId;
        this.timestamp = timestamp;
        this.value = value;
    }

    public ConsumptionDto(Long id, Long deviceId, Date timestamp, Double value) {
        this.id = id;
        this.deviceId = deviceId;
        this.timestamp = timestamp;
        this.value = value;
    }

    public ConsumptionDto(Long id, Long deviceId, int hour, Double value){
        this.id = id;
        this.deviceId = deviceId;
        this.hour = hour;
        this.value = value;
    }


    public ConsumptionDto() {
    }
}
