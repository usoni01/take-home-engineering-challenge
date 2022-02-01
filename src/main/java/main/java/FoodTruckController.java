package main.java;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController

public class FoodTruckController {

    FoodTruckService foodTruckService;
    FoodTruckController( FoodTruckService foodTruckService) {
        this.foodTruckService = foodTruckService;
    }
/*
---------------------------------------------
TODO: For the simplicity of the project, I have created 2 hashmaps. 1 which is storing
        Important details as locationId, Name, block number, and food menu
        Block map stores block number as id and all the locationIds are values

---------------------------------------------
Assumptions: in CSV, Type Truck = Food truck and do not add anything else that is not Truck ex: push cart

---------------------------------------------
    //add a foodTruck
    //check if it already exists
    //put try catch
    //print confirmation once done
    /We sending the request, ran into the thought whether or not the request will be giving us all the fields required to add
    //to the CSV file? will it get stored? as we are not making any connection to the Database.
    -- Is the locationID auto generated? or the user is passing it in when hitting add endpoint?
    -- In an ideal world, it would be better to have a request body where all the fields are entered through json, and parse the json to extract those fields.
    -- For the simplicity of the project, I am making the assumption that the use is passing the locationID and rest of the information as a string
    -- To show that it has been added, successful message will be shown to the user
    -- I will be updating the maps I built for location and block.

*/
    @PostMapping("foodtruck/{{locationId}/{info}}")
    public void addFoodTruck(@PathVariable Map<String, String> pathVarsMap){
        String locationId = pathVarsMap.get("locationId");
        String info = pathVarsMap.get("info");

        foodTruckService.addFoodTruck(locationId, info);
    }

    //get foodTrucks by locationId
    //calls the function to create map by locationId. and calls the function findByLocationID.
    //Which traverses through the map of locationId we just built and returns the value. if not found returns null.
    @GetMapping("foodTrucks/{locationId}")
    @ResponseBody
    public String getFoodTruckByLocationId(@PathVariable String locationId) throws IOException {
        foodTruckService.createMaps();
        String result = foodTruckService.findByLocationId(locationId);
        return result;
    }

    @GetMapping("foodTrucks/{blockId}")
    public List<String> getAllByBlock(@PathVariable String blockId) throws IOException {
        foodTruckService.createMaps();
        List<String> result = foodTruckService.findByBlockId(blockId);
        return result;
    }

}
