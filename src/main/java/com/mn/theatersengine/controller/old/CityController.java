//package com.mn.theatersengine.controller;
//
//import com.mn.theatersengine.dto.CityDTO;
//import com.mn.theatersengine.service.CityService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/cities")
//public class CityController {
//
//    private final CityService cityService;
//
//    @Autowired
//    public CityController(CityService cityService) {
//        this.cityService = cityService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<CityDTO>> getAllCities(){
//        return ResponseEntity.ok(cityService.getAllCities());
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<Void> saveCity(@RequestBody CityDTO cityDTO) {
//        cityService.saveCity(cityDTO);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//}
//
