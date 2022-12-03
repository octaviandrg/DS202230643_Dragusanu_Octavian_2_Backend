package ro.tuc.ds2022.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "consumptions")
public class Consumption implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "deviceId")
    private Device device;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    @Column(name = "value", nullable = false)
    private Double value;

    public Consumption(Long id, Device device, Date timestamp, Double value) {
        this.id = id;
        this.device = device;
        this.timestamp = timestamp;
        this.value = value;
    }

    public Consumption() {
    }

    public Consumption(Device device, Date timestamp, Double value) {
        this.device = device;
        this.timestamp = timestamp;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
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
}
