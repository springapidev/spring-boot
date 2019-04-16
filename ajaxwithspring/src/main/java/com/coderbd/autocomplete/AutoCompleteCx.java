package com.coderbd.autocomplete;


import com.coderbd.Country;
import com.coderbd.CountryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Controller
public class AutoCompleteCx {
    private List<Country> allCountries;

    private String firstThreeCharacters;
    @Autowired
    private CountryRepo countryRepo;

    @RequestMapping(value = "/countryAutocomplete")
    @ResponseBody
    public List<LabelValueDTO> plantNamesAutocomplete(@RequestParam(value = "term", required = false, defaultValue = "Ban") String term) {
        List<LabelValueDTO> suggestions = new ArrayList<LabelValueDTO>();
        try {
            // only update when term is three characters.
            if (term.length() == 3) {
                           allCountries = countryRepo.findAll();
            }

            for (Country country : allCountries) {
             //   if (country.toString().contains(term))
                if (country.toString().contains(term) || country.toString().contains(term.toUpperCase()) || country.toString().contains(term.toLowerCase()) || country.toString().contains(term.substring(0, 1).toUpperCase() + term.substring(1)) ) {
                    LabelValueDTO lv = new LabelValueDTO();
                    lv.setLabel(country.getCountry());
                    lv.setValue(country.getCountry());
                    suggestions.add(lv);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return suggestions;

    }

    @RequestMapping(value = "/autocompletes", method = RequestMethod.GET)
    public String showAutocomplete() {
        return "autocomplete";
    }
}

////https://www.youtube.com/watch?v=hdqFm6lu_kg