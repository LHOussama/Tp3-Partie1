package com.example.ensethopital.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.ensethopital.repositories.PatientRepository;
import com.example.ensethopital.entites.Patient;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;
    @GetMapping("/index")
    public String index(Model model, @RequestParam(name = "p",defaultValue = "0")
                        int page,@RequestParam(name="s",defaultValue = "4") int size,
                        @RequestParam(name = "keyword",defaultValue ="")String kw){
        Page<Patient> pagePatient=patientRepository.findByNomContaining(kw,PageRequest.of(page,size));
        model.addAttribute("listPatients",pagePatient.getContent());
        model.addAttribute("pages",new int[pagePatient.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyWord",kw);
        return "patients";
    }
    @GetMapping("/delete")
    public String delete(long id,String keyword,int page){
        patientRepository.deleteById(id);
        return "redirect:/index?p="+page+"&keyword  ="+keyword;
    }
}
