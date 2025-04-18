package kr.ac.dankook.ace.lab3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.dankook.ace.lab3.dao.EarthquakeDataImporter;
import kr.ac.dankook.ace.lab3.dao.EarthquakeDateFinder;
import kr.ac.dankook.ace.lab3.dao.EarthquakeMagFinder;
import kr.ac.dankook.ace.lab3.dto.Earthquake;
import kr.ac.dankook.ace.lab3.dto.EarthquakeSearchForm;

@Controller
public class EarthquakeController {

    @Autowired
    EarthquakeDataImporter earthquakeDataImporter;
    @Autowired
    EarthquakeDateFinder dateFinder;
    @Autowired
    EarthquakeMagFinder magFinder;

    @GetMapping("/earthquake")
    public String showList(Model model) {
        List<Earthquake> list = earthquakeDataImporter.load();
        List<Integer> yearList = dateFinder.extractYearValues(list);
        List<Double> magList = magFinder.extractMagValues(list);
        model.addAttribute("data", list);
        model.addAttribute("yearOptions", yearList);
        model.addAttribute("magOptions", magList);
        model.addAttribute("form", new EarthquakeSearchForm());
        return "earthquake";
    }

    @PostMapping("/earthquake")
    public String processSearch(@RequestParam String action, @ModelAttribute("form") EarthquakeSearchForm form, Model model) {
        List<Earthquake> list = earthquakeDataImporter.load();
        List<Integer> yearList = dateFinder.extractYearValues(list);
        List<Double> magList = magFinder.extractMagValues(list);
        
        List<Earthquake> filtered = list.stream()
        .filter(e -> form.getStartYear() == null || e.getYear() >= form.getStartYear())
        .filter(e -> form.getEndYear() == null || e.getYear() <= form.getEndYear())
        .filter(e -> form.getMagMin() == null || e.getMagnitude() >= form.getMagMin())
        .filter(e -> form.getMagMax() == null || e.getMagnitude() <= form.getMagMax())
        .toList();

        if (action.equals("save")) {
            earthquakeDataImporter.save(filtered);
        }
        
        model.addAttribute("yearOptions", yearList);
        model.addAttribute("magOptions", magList);
        model.addAttribute("data", filtered);
        model.addAttribute("form", form);
        return "earthquake";
    }
}
