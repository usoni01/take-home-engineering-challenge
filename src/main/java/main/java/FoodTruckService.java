package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import au.com.bytecode.opencsv.CSVReader;

import org.springframework.stereotype.Service;

@Service
public class FoodTruckService {

    public static Map<String, String> locationMap = new HashMap<>();
    public static Map<String, List<String>> blockMap = new HashMap<>();
    public static void createMaps() throws IOException {
        //Reads in the input csv file
        CSVReader reader = new CSVReader(new FileReader("Mobile_Food_Facility_Permit.csv"));
        String [] nextLine;
        int lineNumber = 0;
        while ((nextLine = reader.readNext()) != null) {
            lineNumber++;

            // nextLine[] is an array of values from the line
            //only checking for the type that has type "Truck" == food truck -- assumption
            if(nextLine[2].equals("Truck")){
                //get the field from specific columns
                //this is assuming that these are the important information needed for simple solution
                //if all of the values attached to the locationMap needs to be returned, it can be adgusted via
                //updating the line of code below.
                locationMap.put(nextLine[0], "Applicant:" + nextLine[1] + "Address:" +
                        nextLine[5] + "Food Items:" + nextLine[11] + "/n");

                // check if block id exists
                //check if the blockId already exists, if it does that get the blockID key and append the current
                //locationId onto existing list
                if(!nextLine[7].isEmpty()){
                    if(blockMap.containsKey(nextLine[7])){
                       blockMap.get(nextLine[7]).add(nextLine[0]);
                    }
                    else{
                        //create new list and add the value to the map
                        blockMap.put(nextLine[7], new ArrayList<>());
                        blockMap.get(nextLine[7]).add(nextLine[0]);
                    }
                }
            }
        }

    }

    public static void addFoodTruck(String locationId, String input){
           try {
               locationMap.put(locationId, input);
               System.out.println("Added food truck " + locationId + locationMap.get(locationId));

           }catch (Exception e){
               System.out.println("Error adding foodTruck");
           }


    }
    //Takes the locationId passed by the user, and searches for it in the locationMap. returns the result if it exists or
    //returns null
    public static String findByLocationId(String locationId) throws IOException {
        String result = locationMap.get(locationId);
        if(result != null)
            return result;
        else
            return null;
    }

    public static List<String> findByBlockId(String blockId) throws IOException {
        List<String> response = new ArrayList<>();
        //get the list of locationIds by blockId number from the blockMap
        //iterate through each locationId in the list and find that locationId in thelocationMap
        //if the size is 0, no location id's were present.
        //return null
        List <String> locationIds = blockMap.get(blockId);
        if(locationIds.size() == 0){
            return null;
        }
        else{
            for(int i = 0; i < locationIds.size(); i++){
                response.add(locationIds.get(i) + ":" + locationMap.get(locationIds.get(i)));
            }
            return response;
        }
    }

    //This code is kept for testing purposes to check each method locally
    /*
    public static void main(String[] args) throws IOException {
        createMaps();
       // findByBlockId("3722");
        //findByLocationId("934597") ;
        //addFoodTruck("010101", "Applicant:Ruru Juice LLCAddress:601 MISSION STFood Items:Smoothies: Juice");
    }*/



}
