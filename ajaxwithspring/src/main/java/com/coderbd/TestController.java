package com.coderbd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {
@Autowired
private CityRepo cityRepo;
    static List<City> cityList;

    @GetMapping("/city")
    public List<City> countrySingle(Model model,@RequestParam("id") Long id) {
        System.out.println("ID: "+id);
        List<City> list=this.cityRepo.findAllByCountry(new Country(id));
        return list;
    }
}
