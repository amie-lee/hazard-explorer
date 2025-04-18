package kr.ac.dankook.ace.lab3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.dankook.ace.lab3.dao.TsunamiCodeFinder;
import kr.ac.dankook.ace.lab3.dao.TsunamiDataImporter;
import kr.ac.dankook.ace.lab3.dao.TsunamiDateFinder;
import kr.ac.dankook.ace.lab3.dao.TsunamiValidityFinder;
import kr.ac.dankook.ace.lab3.dto.Tsunami;
import kr.ac.dankook.ace.lab3.dto.TsunamiSearchForm;

@Controller
public class TsunamiController {
    
    @Autowired
    TsunamiDataImporter tsunamiDataImporter;
    @Autowired
    TsunamiDateFinder dateFinder;
    @Autowired
    TsunamiValidityFinder validityFinder;
    @Autowired
    TsunamiCodeFinder codeFinder;

    @GetMapping("/tsunami")
    public String showList(Model model) {
        List<Tsunami> list = tsunamiDataImporter.load();
        List<Integer> yearList = dateFinder.extractYearValues(list);
        List<Integer> validityList = validityFinder.extractValidityValues(list);
        List<Integer> codeList = codeFinder.extractCodeValues(list);
        model.addAttribute("data", list);
        model.addAttribute("yearOptions", yearList);
        model.addAttribute("validityOptions", validityList);
        model.addAttribute("codeOptions", codeList);
        model.addAttribute("form", new TsunamiSearchForm());
        return "tsunami";
    }

    @PostMapping("/tsunami")
    public String processSearch(@RequestParam String action, @ModelAttribute("form") TsunamiSearchForm form, Model model) {
        List<Tsunami> list = tsunamiDataImporter.load();
        List<Integer> yearList = dateFinder.extractYearValues(list);
        List<Integer> validityList = validityFinder.extractValidityValues(list);
        List<Integer> codeList = codeFinder.extractCodeValues(list);
        
        List<Tsunami> filtered = list.stream()
        .filter(t -> form.getStartYear() == null || t.getYear() >= form.getStartYear())
        .filter(t -> form.getEndYear() == null || t.getYear() <= form.getEndYear())
        .filter(t -> form.getExactValidity() == null || t.getValidity() == form.getExactValidity())
        .filter(t -> form.getValMin() == null || t.getValidity() >= form.getValMin())
        .filter(t -> form.getValMax() == null || t.getValidity() <= form.getValMax())
        .filter(t -> form.getExactCode() == null || t.getCode() == form.getExactCode())
        .filter(t -> form.getCodeMin() == null || t.getCode() >= form.getCodeMin())
        .filter(t -> form.getCodeMax() == null || t.getCode() <= form.getCodeMax())
        .toList();

        if (action.equals("save")) {
            tsunamiDataImporter.save(filtered);
        }

        model.addAttribute("data", filtered);
        model.addAttribute("yearOptions", yearList);
        model.addAttribute("validityOptions", validityList);
        model.addAttribute("codeOptions", codeList);
        model.addAttribute("form", form);
        return "tsunami";
    }
}
