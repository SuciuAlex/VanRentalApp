package com.example.VanRentalApp.service;

import com.example.VanRentalApp.enums.MakerEnum;
import com.example.VanRentalApp.model.Van;
import com.example.VanRentalApp.repository.VanRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Arrays;
import java.util.List;

import static com.example.VanRentalApp.enums.FuelTypeEnum.*;
import static com.example.VanRentalApp.enums.GearboxTypeEnum.*;
import static com.example.VanRentalApp.enums.MakerEnum.*;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class VanServiceTest {

    private VanService testedClass;
    @Autowired
    private VanRepository vanRepository;

    @BeforeEach
    void setUp() {
        testedClass = new VanService(vanRepository);
    }

    @AfterEach
    void tearDown() {
        vanRepository.deleteAll();
    }

    @Test
    void getVans() {
        Van fordManual = new Van(
                FORD,
                "Transit",
                "B998TST",
                2020,
                123456,
                2000,
                "7+1",
                MANUAL,
                DIESEL,
                "50 euro",
                true,
                true,
                false,
                false
        );

        Van fordAutomatic = new Van(
                FORD,
                "Transit",
                "B999TST",
                2022,
                10000,
                3000,
                "8+1",
                AUTOMATIC,
                DIESEL,
                "100 euro",
                true,
                true,
                true,
                true
            );

            vanRepository.saveAll(Arrays.asList(fordManual,fordAutomatic));
            List<Van> vans = testedClass.getVans();

            //count number of returned customers.Should be 2
            assertEquals(2,vans.size());
    }

    @Test
    void getVan() {
        Van fordManual = new Van(
                FORD,
                "Transit",
                "B998TST",
                2020,
                123456,
                2000,
                "7+1",
                MANUAL,
                DIESEL,
                "50 euro",
                true,
                true,
                false,
                false
        );
        vanRepository.saveAll(Arrays.asList(fordManual));
        List<Van> van = testedClass.getVan("B998TST");
        assertEquals("B998TST", van.stream().findFirst().get().getLicensePlate() );
        assertEquals(FORD, van.stream().findFirst().get().getMaker() );
        assertEquals(2020, van.stream().findFirst().get().getBuildYear() );
        assertEquals(123456, van.stream().findFirst().get().getMileageKm() );
        assertEquals(2000, van.stream().findFirst().get().getEngineCapacity() );
        assertEquals("7+1", van.stream().findFirst().get().getSeatCapacity() );
        assertEquals(MANUAL, van.stream().findFirst().get().getGearboxType() );
        assertEquals(DIESEL, van.stream().findFirst().get().getFuelType() );
        assertEquals("50 euro", van.stream().findFirst().get().getCostPerDay());
    }



}