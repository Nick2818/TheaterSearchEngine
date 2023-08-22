package com.mn.theatersengine.controller;

import com.mn.theatersengine.entity.UserEntity;
import com.mn.theatersengine.repository.ActorRepository;
import com.mn.theatersengine.repository.CityRepository;
import com.mn.theatersengine.repository.TheaterRepository;
import com.mn.theatersengine.repository.UserRepository;
import com.mn.theatersengine.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    private final TheaterRepository theaterRepository;

    private final CityRepository cityRepository;

    private final ActorRepository actorRepository;

    private final SearchService searchService;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository, TheaterRepository theaterRepository, CityRepository cityRepository, ActorRepository actorRepository, SearchService searchService, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.theaterRepository = theaterRepository;
        this.cityRepository = cityRepository;
        this.actorRepository = actorRepository;
        this.searchService = searchService;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute
    private void userDetails(Model m, Principal p){
        String email = p.getName();
        UserEntity user = userRepository.findByEmail(email);

        m.addAttribute("user", user);
    }

    @GetMapping("/home")
    public String home(){
        return "user/home";
    }

    @GetMapping("/changePassword")
    public String loadChangePassword(){
        return "user/changePassword";
    }

    @PostMapping("/updatePassword")
    public String changePassword(Principal p, @RequestParam("oldPass") String oldPass, @RequestParam("newPass") String newPass, HttpSession session){

        String email = p.getName();
        UserEntity user = userRepository.findByEmail(email);

        boolean b = passwordEncoder.matches(oldPass, user.getPassword());

        if(b){
            user.setPassword(passwordEncoder.encode(newPass));
            UserEntity updatePasswordUser = userRepository.save(user);

            if(updatePasswordUser!=null){
                session.setAttribute("msg", "Password changed successfully!");
            }else{
                session.setAttribute("msg", "Something went wrong, try again later!");
            }
        }else{
            session.setAttribute("msg", "Old password is incorrect!");
        }

        return "redirect:/user/changePassword";
    }


    @GetMapping("/listTheaters")
    public String loadTheaters(Model m){
        m.addAttribute("theaters", theaterRepository.findAll());
        return "user/theatersPage";
    }

    @GetMapping("/listCities")
    public String loadCities(Model m){
        m.addAttribute("cities", cityRepository.findAll());
        return "user/citiesPage";
    }

    @GetMapping("/listActors")
    public String loadActors(Model m){
        m.addAttribute("actors", actorRepository.findAll());
        return "user/actorsPage";
    }

    @GetMapping("/listSearch")
    public String search(@RequestParam String query, Model model) {
        List<Object[]> searchResult = searchService.searchEntitiesByKeyword(query);
        model.addAttribute("searchResults", searchResult);
        return "user/searchPage";
    }

    @GetMapping("/listAll")
    public String allEntities(Model model) {
        List<Object[]> searchResult = searchService.allEntities();
        model.addAttribute("allResults", searchResult);
        return "user/allPage";
    }

}
