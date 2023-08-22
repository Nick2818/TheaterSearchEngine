package com.mn.theatersengine.controller;

import com.mn.theatersengine.dto.ActorDTO;
import com.mn.theatersengine.dto.CityDTO;
import com.mn.theatersengine.dto.TheaterDTO;
import com.mn.theatersengine.entity.ActorEntity;
import com.mn.theatersengine.entity.CityEntity;
import com.mn.theatersengine.entity.TheaterEntity;
import com.mn.theatersengine.entity.UserEntity;
import com.mn.theatersengine.repository.ActorRepository;
import com.mn.theatersengine.repository.CityRepository;
import com.mn.theatersengine.repository.TheaterRepository;
import com.mn.theatersengine.repository.UserRepository;
import com.mn.theatersengine.service.ActorService;
import com.mn.theatersengine.service.CityService;
import com.mn.theatersengine.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {


    // *USER*######################################################
    private final UserRepository userRepository;

    // *CITY*######################################################
    private final CityRepository cityRepository;

    private final CityService cityService;

    // *THEATER* ###################################################
    private final TheaterRepository theaterRepository;

    private final TheaterService theaterService;

    // *ACTORS* ####################################################
    private final ActorRepository actorRepository;

    private final ActorService actorService;

    @Autowired
    public AdminController(UserRepository userRepository, CityRepository cityRepository, CityService cityService, TheaterRepository theaterRepository, TheaterService theaterService, ActorRepository actorRepository, ActorService actorService) {
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
        this.cityService = cityService;
        this.theaterRepository = theaterRepository;
        this.theaterService = theaterService;
        this.actorRepository = actorRepository;
        this.actorService = actorService;
    }

    @ModelAttribute
    private void userDetails(Model m, Principal p){
        String email = p.getName();
        UserEntity user = userRepository.findByEmail(email);

        m.addAttribute("user", user);
    }

    //*HOME*####################################################################################################################

    @GetMapping("/home")
    public String home(){
        return "admin/home";
    }

    //*CITY*####################################################################################################################

    @GetMapping("/listCities")
    public String loadCities(Model m){
        m.addAttribute("cities", cityRepository.findAll());
        return "admin/city/citiesPage";
    }

    @GetMapping ("/addCity")
    public String loadAddCity(){
        return "admin/city/addCityPage";
    }

    @PostMapping("/createCity")
    public String addCityAction(@ModelAttribute CityDTO city, HttpSession session){

            CityDTO cityDTO = cityService.saveCity(city);

            if(cityDTO != null){
                session.setAttribute("msg", "City added successfully!");
            }else{
                session.setAttribute("msg", "Something wrong on server, try again later!");
            }

        return "redirect:/admin/listCities";
    }

    @GetMapping("/deleteCity/{id}")
    public String deleteCity(@PathVariable(value = "id") Long id, HttpSession session){
        cityRepository.deleteById(id);
        session.setAttribute("msg", "City deleted successfully!");
        return "redirect:/admin/listCities";
    }

    @GetMapping("/updateCity/{id}")
    public String loadUpdateCity(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("city", cityRepository.findById(id));
        return "admin/city/updateCityPage";
    }

    @PostMapping("/updateCityAction")
    public String updateCityAction(@ModelAttribute CityEntity city, HttpSession session){

        Optional<CityEntity> cityEntity = cityRepository.findById(city.getId());

        if(cityEntity.isPresent()){
            cityService.updateCity(city);
            session.setAttribute("msg", "City edited successfully!");
        }else{
            session.setAttribute("msg", "Something wrong on server, try again later!");
        }

        return "redirect:/admin/listCities";
    }

    //*THEATER*##################################################################################################################

    @GetMapping("/listTheaters")
    public String loadTheaters(Model m){
        m.addAttribute("theaters", theaterRepository.findAll());
        return "admin/theater/theatersPage";
    }

    @GetMapping ("/addTheater")
    public String loadAddTheater(Model model){

        model.addAttribute("cities", cityRepository.findAll());

        return "admin/theater/addTheaterPage";
    }

    @PostMapping("/createTheater")
    public String addTheaterAction(@ModelAttribute TheaterDTO theaterDTO, HttpSession session){

            theaterService.saveTheater(theaterDTO);

            if(theaterDTO != null){
                session.setAttribute("msg", "Theater added successfully!");
            }else{
                session.setAttribute("msg", "Something wrong on server, try again later!");
            }

        return "redirect:/admin/listTheaters";
    }

    @GetMapping("/updateTheater/{id}")
    public String loadUpdateTheater(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("theater", theaterRepository.findById(id));
        model.addAttribute("cities", cityRepository.findAll());
        return "admin/theater/updateTheaterPage";
    }

    @PostMapping("/updateTheaterAction")
    public String updateTheaterAction(@ModelAttribute TheaterEntity theater, HttpSession session){

        Optional<TheaterEntity> theaterEntity = theaterRepository.findById(theater.getId());

        if(theaterEntity.isPresent()){
            theaterService.updateTheater(theater);
            session.setAttribute("msg", "Theater edited successfully!");
        }else{
            session.setAttribute("msg", "Something wrong on server, try again later!");
        }

        return "redirect:/admin/listTheaters";
    }

    @GetMapping("/deleteTheater/{id}")
    public String deleteTheater(@PathVariable(value = "id") Long id, HttpSession session){
        theaterRepository.deleteById(id);
        session.setAttribute("msg", "Theater deleted successfully!");
        return "redirect:/admin/listTheaters";
    }

    //*ACTOR*####################################################################################################################

    @GetMapping("/listActors")
    public String loadActors(Model m){
        m.addAttribute("actors", actorRepository.findAll());
        return "admin/actor/actorsPage";
    }

    @GetMapping ("/addActor")
    public String loadAddActor(Model model){

        model.addAttribute("theaters", theaterRepository.findAll());

        return "admin/actor/addActorPage";
    }

    @PostMapping("/createActor")
    public String addActorAction(@ModelAttribute ActorDTO actorDTO, HttpSession session){

        actorService.saveActor(actorDTO);

        if(actorDTO != null){
            session.setAttribute("msg", "Actor added successfully!");
        }else{
            session.setAttribute("msg", "Something wrong on server, try again later!");
        }

        return "redirect:/admin/listActors";
    }

    @GetMapping("/updateActor/{id}")
    public String loadUpdateActor(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("actor", actorRepository.findById(id));
        model.addAttribute("theaters", theaterRepository.findAll());
        return "admin/actor/updateActorPage";
    }

    @PostMapping("/updateActorAction")
    public String updateActorAction(@ModelAttribute ActorEntity actor, HttpSession session){

        Optional<ActorEntity> actorEntity = actorRepository.findById(actor.getId());

        if(actorEntity.isPresent()){
            actorService.updateActor(actor);
            session.setAttribute("msg", "Actor edited successfully!");
        }else{
            session.setAttribute("msg", "Something wrong on server, try again later!");
        }

        return "redirect:/admin/listActors";
    }

    @GetMapping("/deleteActor/{id}")
    public String deleteActor(@PathVariable(value = "id") Long id, HttpSession session){
        actorRepository.deleteById(id);
        session.setAttribute("msg", "Actor deleted successfully!");
        return "redirect:/admin/listActors";
    }

}
