package kr.ac.dankook.ace.lab3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.dankook.ace.lab3.dao.VolcanoDataImporter;
import kr.ac.dankook.ace.lab3.dao.VolcanoDateFinder;
import kr.ac.dankook.ace.lab3.dao.VolcanoTypeFinder;
import kr.ac.dankook.ace.lab3.dao.VolcanoVeiFinder;
import kr.ac.dankook.ace.lab3.dto.Volcano;
import kr.ac.dankook.ace.lab3.dto.VolcanoSearchForm;

@Controller
public class VolcanoController {

    @Autowired
    VolcanoDataImporter volcanoDataImporter;
    @Autowired
    VolcanoVeiFinder veiFinder;
    @Autowired
    VolcanoDateFinder dateFinder;
    @Autowired
    VolcanoTypeFinder typeFinder;

    @GetMapping("/volcano")
    public String showList(Model model) {
        List<Volcano> list = volcanoDataImporter.load();
        List<Integer> veiList = veiFinder.extractVeiValues(list);
        List<Integer> yearList = dateFinder.extractYearValues(list);
        List<String> typeList = typeFinder.extractTypeValues(list);
        model.addAttribute("data", list);
        model.addAttribute("veiOptions", veiList);
        model.addAttribute("yearOptions", yearList);
        model.addAttribute("typeOptions", typeList);
        model.addAttribute("form", new VolcanoSearchForm());
        return "volcano";
    }

    @PostMapping("/volcano")
    public String processSearch(@RequestParam String action, @ModelAttribute("form") VolcanoSearchForm form, Model model) {
        List<Volcano> list = volcanoDataImporter.load();
        List<Integer> veiList = veiFinder.extractVeiValues(list);
        List<Integer> yearList = dateFinder.extractYearValues(list);
        List<String> typeList = typeFinder.extractTypeValues(list);

        List<Volcano> filtered = list.stream()
        .filter(v -> form.getExactVei() == null || v.getVei() == form.getExactVei())
        .filter(v -> form.getVeiMin() == null || v.getVei() >= form.getVeiMin())
        .filter(v -> form.getVeiMax() == null || v.getVei() <= form.getVeiMax())
        .filter(v -> form.getStartYear() == null || v.getYear() >= form.getStartYear())
        .filter(v -> form.getEndYear() == null || v.getYear() <= form.getEndYear())
        .filter(v -> form.getType().isEmpty() || form.getType().equals(v.getType()))
        .toList();

        if ("save".equals(action)) {
            volcanoDataImporter.save(filtered);
        }    

        model.addAttribute("veiOptions", veiList);
        model.addAttribute("yearOptions", yearList);
        model.addAttribute("typeOptions", typeList);
        model.addAttribute("data", filtered);
        model.addAttribute("form", form);
        return "volcano";
    }
}
