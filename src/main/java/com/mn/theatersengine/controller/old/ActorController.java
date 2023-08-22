//package com.mn.theatersengine.controller;
//
//import com.mn.theatersengine.dto.ActorDTO;
//import com.mn.theatersengine.service.ActorService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RestController
//@RequestMapping("/actors")
//public class ActorController {
//
//    private final ActorService actorService;
//
//    @Autowired
//    public ActorController(ActorService actorService) {
//        this.actorService = actorService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<ActorDTO>> getAllActors(){
//        return ResponseEntity.ok(actorService.getAllActors());
//    }
//
//
//    @PostMapping("/add")
//    public ResponseEntity<Void> saveActor(@RequestBody ActorDTO actorDTO) {
//        actorService.saveActor(actorDTO);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/delete/{actorId}")
//    public ResponseEntity<Object> deleteActor(@PathVariable Long acotrId){
//        actorService.deleteActor(acotrId);
//
//        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
//    }
//}
//
