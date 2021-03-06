package com.example.bevarage_service.Controller;

import com.example.bevarage_service.Model.Beverage;
import com.example.bevarage_service.Model.Bottle;
import com.example.bevarage_service.Model.Crate;
import com.example.bevarage_service.repository.BeverageRepository;
import com.example.bevarage_service.repository.BottleRepository;
import com.example.bevarage_service.repository.CrateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/beverage")
public class BeverageController {
    private List<Bottle> bottleList;
    private List<Crate> crateList;
    private List<Beverage> beverageslist;
    private final BottleRepository bottleRepository;
    private final CrateRepository crateRepository;
    private final BeverageRepository bevarageRepository;

    public BeverageController(List<Beverage> beverageslist, BottleRepository bottleRepository, CrateRepository crateRepository, BeverageRepository bevarageRepository) {
        this.beverageslist = beverageslist;
        this.bottleRepository = bottleRepository;
        this.crateRepository = crateRepository;
        this.bevarageRepository = bevarageRepository;
        this.bottleList = new ArrayList<>();
        this.crateList = new ArrayList<>();

    }


    @GetMapping
    public String getBottles(Model model) {
        Bottle bottle = new Bottle();
        Crate crate = new Crate();
        Beverage bev = new Beverage();

        this.beverageslist.clear();
        this.bottleList.clear();
        this.crateList.clear();

        this.beverageslist = this.bottleRepository.findAll();

        for (Beverage b : this.beverageslist) {
            if (b instanceof Bottle) {
                this.bottleList.add((Bottle) b);
            } else if (b instanceof Crate) {
                this.crateList.add((Crate) b);
            } else {
                //error
            }

        }

        System.out.println(this.bottleList);


        model.addAttribute("Bottles", this.bottleList);

        model.addAttribute("Crates", this.crateList);


        //model.addAttribute("Crates", this.crateList);

        model.addAttribute("bottle", bottle);
        model.addAttribute("crate", crate);
        //model.addAttribute("bottles", this.bottleRepository.findAll());



        return "beverage";
    }


    @PostMapping(value = "/bottle")
    public String addBottles(@Valid Bottle bottle, Errors errors, Model model) {


        if (errors.hasErrors()) {
            this.beverageslist.clear();
            this.bottleList.clear();
            this.crateList.clear();

            this.beverageslist = this.bottleRepository.findAll();

            for (Beverage b : this.beverageslist) {
                if (b instanceof Bottle) {
                    this.bottleList.add((Bottle) b);
                } else if (b instanceof Crate) {
                    this.crateList.add((Crate) b);
                } else {
                    //error
                }

            }
            model.addAttribute("Bottles", this.bottleList);

            model.addAttribute("Crates", this.crateList);


            //model.addAttribute("Crates", this.crateList);


            model.addAttribute("bottles", this.bottleRepository.findAll());


            return "beverage";

        }

        this.bottleRepository.save(bottle);

        return "redirect:/beverage";
    }
    @PostMapping(value = "/crate")
    public String addBottles(@Valid Crate crate, Errors errors, Model model) {


        if (errors.hasErrors()) {
            this.beverageslist.clear();
            this.bottleList.clear();
            this.crateList.clear();

            this.beverageslist = this.crateRepository.findAll();

            for (Beverage b : this.beverageslist) {
                if (b instanceof Bottle) {
                    this.bottleList.add((Bottle) b);
                } else if (b instanceof Crate) {
                    this.crateList.add((Crate) b);
                } else {
                    log.info("Code Came here. Must be some error.");
                }
            }

            model.addAttribute("Bottles", this.bottleList);

            model.addAttribute("Crates", this.crateList);


            //model.addAttribute("Crates", this.crateList);


            model.addAttribute("crate", this.crateRepository.findAll());


            return "beverage";

        }

        this.crateRepository.save(crate);

        return "redirect:/beverage";
    }

}
