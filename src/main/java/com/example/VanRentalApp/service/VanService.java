package com.example.VanRentalApp.service;


import com.example.VanRentalApp.exception.NotFoundException;
import com.example.VanRentalApp.model.Van;
import com.example.VanRentalApp.repository.VanRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class VanService {

    //only constant variables should be typed with upper case;
    private final static Logger logger = LoggerFactory.getLogger(VanService.class);
    private final VanRepository vanRepository;

    public List<Van> getVan(String licensePlate){


        return  getVans()
                    .stream().filter(vanDetails -> licensePlate.equals(vanDetails.getLicensePlate()))
                .collect(Collectors.toList());
    }

    public List<Van> getVans(){
        try{
            return vanRepository.findAll();
        } catch(Exception e){
            logger.error("vanRepository.findAll:"+e.getMessage());
            throw new IllegalStateException("An error was detected when getting list of vans. Error message raised: "+ e.getMessage());
        }
    }


    public void addVan(List<Van> van){
        try{
            vanRepository.insert(van);
        }catch(Exception e){
            logger.error("vanRepository.insert:"+e.getMessage());
            throw new IllegalStateException("An error was detected when adding the new van data. Error message raised: "+ e.getMessage());
        }
    }

    public String getVanIdBasedOnLicensePlate(String licensePlate){
        String  licensePlateId = "";
        try{
            licensePlateId = getVans()
                    .stream().filter(vanDetails -> licensePlate.equals(vanDetails.getLicensePlate()))
                    .findFirst()
                    .get().getId();
        }catch(Exception e){
            logger.error("getVanIdBasedOnLicensePlate: "+e.getMessage());
            throw new IllegalStateException("An error was detected when getting the id for the provided license plate("+licensePlate+"). Error message raised: "+ e.getMessage());
        }
        return licensePlateId;



    }
    public void removeVan(String licensePlate){
        String id = getVanIdBasedOnLicensePlate(licensePlate);
        String errorMessage = "";

        System.out.println("The license plate's("+ licensePlate+") id is "+id);
        try {
            vanRepository.deleteById(id);
        }
        catch(Exception e){
            logger.error("removeVan: "+e.getMessage());
            throw new IllegalStateException("An error was detected when removing the data for the provided license plate("+licensePlate+"). Error message raised: "+ e.getMessage());
        }
    }

    public void removeVans(){
        try{
            vanRepository.deleteAll();
        }catch(Exception e){
            logger.error("deleteAll: "+e.getMessage());
            throw new IllegalStateException("An error was detected when cleaning all the van data. Error message raised: "+ e.getMessage());
        }

    }


    public void updateAvailability(String licensePlate, Boolean isAvailable){
        Van van = getVan(licensePlate).get(0);
        if(isAvailable){
            van.setInStock(true);
            logger.info("License plate "+licensePlate+" set as available");
            System.out.println("The license plate's("+ licensePlate+") state was set as available");
        }else{
//            vanRepository.findById(id).stream().findFirst().get().setInStock(false);
                van.setInStock(false);
                logger.info("License plate "+licensePlate+" set as reserved");
                System.out.println("The license plate's("+ licensePlate+") state was set as reserved");
        }

        try{
            vanRepository.save(van);
        }catch(Exception e){
            logger.error("updateAvailability: "+e.getMessage());
            throw new IllegalStateException("An error was detected when updating the licesne plate("+licensePlate+") data. Error message raised: "+ e.getMessage());
        }
    }

}
