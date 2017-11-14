package com.devansh.personal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkingSlot {

    Integer lotId;
    Car car;

    public ParkingSlot(final int i, final Car car) {
        this.lotId = i;
        this.car = car;
    }

    @Data
    public static class Car {
        String carLicenceNumber;
        String carColour;

        public Car(final String carLicenceNumber, final String carColour) {
            this.carColour = carColour;
            this.carLicenceNumber = carLicenceNumber;
        }
    }
}
