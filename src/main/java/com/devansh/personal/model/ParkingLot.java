package com.devansh.personal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkingLot {

    Integer lotId;
    Car car;

    public static class Car {
        String carLicenceNumber;
        String carColour;
    }
}
