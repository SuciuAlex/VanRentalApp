package com.example.VanRentalApp.model;

import com.example.VanRentalApp.enums.FuelTypeEnum;
import com.example.VanRentalApp.enums.GearboxTypeEnum;
import com.example.VanRentalApp.enums.MakerEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document
public class Van {
    @Id
    private String id;

    private MakerEnum maker;
    private String model;

    @Indexed(unique = true)
    private String licensePlate;

    private int buildYear;
    private int mileageKm;
    private int engineCapacity;
    private String seatCapacity;
    private GearboxTypeEnum gearboxType;
    private FuelTypeEnum fuelType;
    private String costPerDay;
    private boolean inStock;
    private boolean hasAc;
    private boolean hasLeatherSeats;
    private boolean hasExtraStorageSpace;

    public Van(MakerEnum maker
            , String model
            , String licensePlate
            , int buildYear
            , int mileageKm
            , int engineCapacity
            , String seatCapacity
            , GearboxTypeEnum gearboxType
            , FuelTypeEnum fuelType
            , String costPerDay
            , boolean inStock
            , boolean hasAc
            , boolean hasLeatherSeats
            , boolean hasExtraStorageSpace) {
        this.maker = maker;
        this.model = model;
        this.licensePlate = licensePlate;
        this.buildYear = buildYear;
        this.mileageKm = mileageKm;
        this.engineCapacity = engineCapacity;
        this.seatCapacity = seatCapacity;
        this.gearboxType = gearboxType;
        this.fuelType = fuelType;
        this.costPerDay = costPerDay;
        this.inStock = inStock;
        this.hasAc = hasAc;
        this.hasLeatherSeats = hasLeatherSeats;
        this.hasExtraStorageSpace = hasExtraStorageSpace;
    }
}
