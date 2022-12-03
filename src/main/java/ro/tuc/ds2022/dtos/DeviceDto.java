package ro.tuc.ds2022.dtos;

public class DeviceDto {

    private String description;

    private String address;

    private Double maxConsumption;

    private Long userId;

    private Long id;


    public DeviceDto(String description, String address, Double maxConsumption, Long userId){
        this.description = description;
        this.address = address;
        this.maxConsumption = maxConsumption;
        this.userId = userId;
    }

    public DeviceDto(String description, String address, Double maxConsumption, Long userId, Long id){
        this.description = description;
        this.address = address;
        this.maxConsumption = maxConsumption;
        this.userId = userId;
        this.id = id;
    }

    public DeviceDto(){

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getMaxConsumption() {
        return maxConsumption;
    }

    public void setMaxConsumption(Double maxConsumption) {
        this.maxConsumption = maxConsumption;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId(){
        return id;
    }
}
