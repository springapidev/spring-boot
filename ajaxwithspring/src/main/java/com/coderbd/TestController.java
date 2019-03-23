package com.coderbd;

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

    static List<City> cityList;
//    @GetMapping("/list")
//    public List<Country> countryListView(Model model) {
//       List<Country> countrylist; countrylist = new ArrayList<>();
//        countrylist.add(new Country(1L, "BD"));
//        countrylist.add(new Country(2L, "Japan"));
//        countrylist.add(new Country(3L, "USA"));
//        model.addAttribute("countrylist",countrylist);
//        return countrylist;
//    }
    @GetMapping("/city")
    public List<City> countrySingle(Model model,@RequestParam("id") Long id) {
        System.out.println("ID: "+id);

            if(id == 1){
                cityList = new ArrayList<>();
                cityList.add(new City(1L,"Dhaka"));
                cityList.add(new City(2L,"Dhaka"));
                model.addAttribute("cityList",cityList);
            } if(id == 2 ){
            cityList = new ArrayList<>();
            cityList.add(new City(1L,"Hiroshima"));
            cityList.add(new City(2L,"Nagashaki"));
            model.addAttribute("cityList",cityList);
            } if(id == 3 ){
            cityList = new ArrayList<>();
            cityList.add(new City(1L,"New York"));
            cityList.add(new City(2L,"Wasington"));
            model.addAttribute("cityList",cityList);
            }


        return cityList;
    }
}
