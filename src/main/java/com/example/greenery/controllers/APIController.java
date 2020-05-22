package com.example.greenery.controllers;

import com.example.greenery.model.*;
import com.example.greenery.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/greenery")
public class APIController {

    public AuthDataService authDataService;
    public CreqService creqService;
    public InstructionService instructionService;
    public PlantService plantService;
    public PurchaseService purchaseService;
    public ResourceService resourceService;
    public UserService userService;

    public APIController(AuthDataService authDataService, CreqService creqService, InstructionService instructionService,
                         PlantService plantService, PurchaseService purchaseService, ResourceService resourceService,
                         UserService userService) {
        this.authDataService = authDataService;
        this.creqService = creqService;
        this.instructionService = instructionService;
        this.plantService = plantService;
        this.purchaseService = purchaseService;
        this.resourceService = resourceService;
        this.userService = userService;
    }

    // AuthData mappings //
    @GetMapping("/authData")
    public List<AuthData> allAuthData() {
        return authDataService.findAll();
    }

    @GetMapping("/authData/{login}")
    public AuthData authDataByLogin(@PathVariable String login) {
        return authDataService.findAuthDataByLogin(login);
    }

    @PostMapping(path = "/authData/add", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAuthData(@RequestBody AuthData newAuthData) {
        authDataService.addItem(newAuthData);
    }

    // User info mappings //
    @GetMapping("/users")
    public List<User> allUsers() {
        return userService.findAll();
    }

    @GetMapping("/users/firstName/{firstName}")
    public List<User> userByFirstName(@PathVariable String firstName) throws NameNotFoundException {
        return userService.findUserByFirstName(firstName);
    }

    @GetMapping("/users/secondName/{firstName}")
    public List<User> userBySecondName(@PathVariable String secondName) throws NameNotFoundException {
        return userService.findUserBySecondName(secondName);
    }

    @GetMapping("/users/role/{role}")
    public List<User> filterUsersByRole(@PathVariable String role) {
        return userService.filterUsersByRole(role);
    }

    @GetMapping("/users/uid/{uid}")
    public User userByID(@PathVariable Integer uid) {
        return userService.findUserByUID(uid);
    }

    @PostMapping(path = "/users/add", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(@RequestBody User newUser) {
        userService.addUser(newUser);
    }

    // Plants mappings //
    @GetMapping("/plants")
    public List<Plant> allPlants() {
        return plantService.findAll();
    }

    @GetMapping("/plants/plantid/{plantId}")
    public Plant plantById(@PathVariable Integer plantId) {
        return plantService.findPlantByPlantId(plantId);
    }

    @GetMapping("/plants/{plantId}/instruction")
    public Instruction getInstructionByPlantId(@PathVariable Integer plantId) {
        Plant plant = plantById(plantId);
        Integer instructionId = plant.instructionId;
        return instructionService.getInstructionByInstructionId(instructionId);
    }

    @GetMapping("/plants/{plantId}/resources")
    public List<Resource> getResourcesByPlantId(@PathVariable Integer plantId) {
        return resourceService.getResourcesByPlantId(plantId);
    }

    @GetMapping("/users/uid/{uid}/plants")
    public List<Plant> plantsOfUser(@PathVariable Integer uid) {
        return plantService.plantsByUID(uid);
    }

    @PostMapping(path = "/plants/add", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPlant(@RequestBody Plant newPlant) {
        plantService.addPlant(newPlant);
    }

    @PutMapping(path = "/plants/{plantId}/set/lastInspection", consumes = "application/json", produces = "application/json")
    public void setLastInspection(@RequestBody String newDate, @PathVariable Integer plantId) {
        Optional<Plant> plant = plantService.getPlantRepo().getPlantByPlantId(plantId);

//                .map(plant -> {
//                    plant.setLastInspection(newDate);
//                    return plantService.getPlantRepo().save(plant);
//                });
    }

//    @PostMapping(path = "/plants/{plantId}/set/lastInspection", consumes = "application/json",
//                    produces = "application/json")
//    @ResponseStatus(HttpStatus.OK)
//    void setLastInspection(@PathVariable("plantId") Integer plantId, @RequestBody HttpServletRequest body) throws IOException {
//        Plant plant = plantById(plantId);
//        String received = IOUtils.toString(body.getInputStream(), StandardCharsets.UTF_8);
////        ObjectMapper mapper = new ObjectMapper();
////        String newDate = mapper.readValue(request.getInputStream(), String.class);
//        //plant.setLastInspection(newDate);
//        plantService.getPlantRepo().save(plant);
    //}

//    @PostMapping(path = "/plants/{plantId}/set/nextInspection", consumes = "application/json",
//                    produces = "application/json")
//    @ResponseStatus(HttpStatus.OK)
//    public void setNextInspection(@PathVariable("plantId") Integer plantId, @RequestBody JSONObject arg) throws JSONException {
//        Plant plant = plantById(plantId);
//        String newDate = (String) arg.get("date");
//        plant.setNextInspection(newDate);
//        plantService.getPlantRepo().save(plant);
//    }

    @DeleteMapping("/plants/{plantId}/delete")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public void deletePlant(@PathVariable Integer plantId) {
        plantService.deletePlantById(plantId);
    }

    // Instruction mappings //
    @GetMapping("/instructions")
    public List<Instruction> allInstructions() {
        return instructionService.findAll();
    }

    @GetMapping("/instructions/id/{instructionId}")
    public Instruction instructionByID(@PathVariable Integer instructionId) {
        return instructionService.getInstructionByInstructionId(instructionId);
    }

    @PostMapping(path = "/instructions/add", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addInstruction(@RequestBody Instruction newInstruction) {
        instructionService.addInstruction(newInstruction);
    }
    // Resource mappings //
    @GetMapping("/resources/id/{resourceId}")
    public Resource resourceByID(@PathVariable Integer resourceId) {
        return resourceService.getResourceByResourceId(resourceId);
    }

    @PostMapping(path = "/resources/add", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addResource(@RequestBody Resource newResource) {
        resourceService.addResource(newResource);
    }

    // Client request mappings //

    // Purchase request mappings //


    public CreqService getCreqService() {
        return creqService;
    }

    @Autowired
    public void setCreqService(CreqService creqService) {
        this.creqService = creqService;
    }

    public InstructionService getInstructionService() {
        return instructionService;
    }

    @Autowired
    public void setInstructionService(InstructionService instructionService) {
        this.instructionService = instructionService;
    }

    public PlantService getPlantService() {
        return plantService;
    }

    @Autowired
    public void setPlantService(PlantService plantService) {
        this.plantService = plantService;
    }

    public PurchaseService getPurchaseService() {
        return purchaseService;
    }

    @Autowired
    public void setPurchaseService(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    public ResourceService getResourceService() {
        return resourceService;
    }

    @Autowired
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public AuthDataService getAuthDataService() {
        return authDataService;
    }

    @Autowired
    public void setAuthDataService(AuthDataService authDataService) {
        this.authDataService = authDataService;
    }


    }
