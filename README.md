# Take Home Engineering Challenge

Commercial Software Engineering is a very practical team at Microsoft and this extends to the way that we work with you to find out if this team is a great fit for you. We want you to come away with a great understanding of the work that we actually do day to day and what it is like to work with us.

So instead of coding at a whiteboard with someone watching over your shoulder under high pressure, which is not a thing we often do, we instead discuss code that you have written previously when we meet.

## Guidelines

-   This is meant to be an assignment that you spend approximately three hours of dedicated, focused work. Do not feel like you need to overengineer the solution with dozens of hours to impress us. Be biased toward quality over quantity, simplicity over complexity.

-   Think of this like an open source project. Create a repo on Github, use git for source control, and use README.md to document what you built for the newcomer to your project.

-   Our team builds, alongside our customers and partners, systems engineered to run in production. Given this, please organize, design, test, deploy, and document your solution as if you were going to put into production. We completely understand this might mean you can't do as much in the time budget. Be biased for production-ready over features.

-   Think out loud in your submission's documentation. Document tradeoffs, the rationale behind your technical choices, or things you would do or do differently if you were able to spend more time on the project or do it again.

-   Our team meets our customers where they are in terms of software engineering platforms, frameworks, tools, and languages. This means you have wide latitude to make choices that express the best solution to the problem given your knowledge and favorite tools. Make sure to document how to get started with your solution in terms of setup.

## The Problem

Our San Francisco team loves to eat. They are also a team that loves variety, so they also like to discover new places to eat.

In fact, we have a particular affection for food trucks. One of the great things about Food Trucks in San Francisco is that the city releases a list of them as open data.

Your assignment is to make it possible for us to find a food truck no matter where our work takes us in the city.

Feel free to tackle this problem in a way that demonstrates your expertise of an area -- or takes you out of your comfort zone.

## Technical Requirements

### Interface

You can write a simple REST service that returns a set of food trucks (our team is fluent in JSON).

### Expected Data Size

Design the solution assuming that the dataset includes data from many cities with millions of records.

### Data Schema

San Francisco's food truck open dataset is [located here](https://data.sfgov.org/Economy-and-Community/Mobile-Food-Facility-Permit/rqzj-sfat/data) and there is an endpoint with a [CSV dump of the latest data here](https://data.sfgov.org/api/views/rqzj-sfat/rows.csv). We've included a [copy of this data](./Mobile_Food_Facility_Permit.csv) in this repo as well.

### Programming Language 

You are welcome to use any language frameworks or libraries you like. 

### Data Storage

You don’t need to use a database to store food truck data. Instead, your REST Service should use language native data structures (ie _Set, List, Map, Stack, Heap, etc_) to implement the in-memory data store. Please avoid using query and/or ORM frameworks(_ie Linq, Hibernate_).

### Service Requirements

Your REST service should make it possible to:

- Add a new food truck.
- Retrieve a food truck based on the `locationid` field.
- Get all food trucks for a given `block`.

### Testing

You are welcome to use your unit testing framework of choice to validate the in-memory data store and service functionality.

Good luck! Please send a link to your solution on Github back to us at least 12 hours before your interview so we can review it before we speak.

### User Solution

With short time to build the solution and for the simplicity of the project, some assumptions were made:
  - The type specified as "Truck" was the actual food truck. Only those were being considered for the solution.
  - The locationId was NOT auto generated for addFoodTruck call.
  - Only few of the main fields were shown on the result such as: LocationId, Name, Address, Block and Food Menu
  - Project was made as Maven and pox.xml file was added to add dependency for the codes
  
 The solution is consisting of 3 main java class: FoodTruckApplication, FoodTruckController and FoodTruckService.
 
 1st step in the solution is to extract the data from CSV and store it locally. In this case, they are being stored in hashmaps.
 The fuction createMaps reads the provided CSV and puts the data into 2 hashmaps.
 1st hashmap, the code gets locationId as key and values would be rest of the data. Current code only takes few important fields and stores them as String.
 2nd hashmap stores the blockId as key and all the locationIds associated with that blockId will be added in as value in a list.
 
 This will help with endpoint to lookup by blockId, and saves space instead of storing all details again.
 
 
 In the FoodTruckController:
  - addFoodTruck endpoint is POST method as we are creating new instrance of a food truck object. This takes locationId as a string and all other fields       are in info string. --> It is unclear weather locationId is autoGenerated and being used as the primary key for the table or not.
    the controller calles the FoodTruckService method addFoodTruck and adds the key/value pair to the locationMap. Which will print whether it was done       successfully or there was error thrown (for debugging purpose).
    
  - getFoodTruckByLocationId is  a GET method because we are retrieving from existing data. It checks whether the locationId exists in locationMap and         returns it's value if it's there or else returns null.
  - getFoodTruckByBlockId is also another GET method. It checks whether the blockId exists in the blockMap and returns the list locationIds associated         with that blockId in a list. After getting the list, the method iterates thru the list and searches for that locationId in locationMap and returns         info associated with it. This will return all food trucks associated with the block number.


  
  
  
  
  
 
 
