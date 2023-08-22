//package com.mn.theatersengine.controller;
//
//import com.mn.theatersengine.dto.TheaterDTO;
//import com.mn.theatersengine.repository.TheaterRepository;
//import com.mn.theatersengine.service.TheaterService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/theaters")
//public class TheaterController {
//
//    private final TheaterService theaterService;
//
//    private final TheaterRepository theaterRepository;
//
//    @Autowired
//    public TheaterController(TheaterService theaterService, TheaterRepository theaterRepository) {
//        this.theaterService = theaterService;
//        this.theaterRepository = theaterRepository;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<TheaterDTO>> getAllTheaters() {
//        return ResponseEntity.ok(theaterService.getAllTheaters());
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<Void> saveTheater(@RequestBody TheaterDTO theaterDTO) {
//        theaterService.saveTheater(theaterDTO);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/delete/{theaterId}")
//    public ResponseEntity<Object> deleteTheater(@PathVariable Long theaterId){
//        theaterService.deleteTheater(theaterId);
//
//        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
//    }
//
////    @GetMapping("/listTheaters")
////    public String loadTheaters(Model model){
////
////        model.addAttribute("theaters", theaterRepository.findAll());
////
////        return "user/theatersPage";
////
////    }
//
//}
