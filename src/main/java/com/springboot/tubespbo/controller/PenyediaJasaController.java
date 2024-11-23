package com.springboot.tubespbo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.tubespbo.local_model.Sessiondata;
import com.springboot.tubespbo.model.Customer;
import com.springboot.tubespbo.model.PenyediaJasa;
import com.springboot.tubespbo.model.RiwayatPesanan;
import com.springboot.tubespbo.model.User;
import com.springboot.tubespbo.repository.PenyediaJasaRepository;
import com.springboot.tubespbo.repository.RiwayatPesananRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class PenyediaJasaController {

    @Autowired
    PenyediaJasaRepository penyediaJasaRepository;

    @Autowired
    RiwayatPesananRepository riwayatPesananRepository;
    
    @PostMapping("/dashboard/tambah_keahlian")
    public String tambahKeahlian(@RequestParam("keahlian") String keahlian,
            HttpSession session,
            Model model) {
                Sessiondata sessiondata = (Sessiondata) session.getAttribute("loggedUser");
                
                if (sessiondata == null) {
                    return "redirect:/login";
                }
                
                PenyediaJasa penyediaJasa = (PenyediaJasa) sessiondata.getUser();

                if (penyediaJasa!=null && !keahlian.equals("0")) {
                    penyediaJasa.setJenisKeahlian(keahlian);
                    penyediaJasaRepository.save(penyediaJasa);
                    Sessiondata newSessiondata = new Sessiondata(penyediaJasa, "Penyedia Jasa");
                    session.setAttribute("loggedUser", newSessiondata);
                }

                return "redirect:/dashboard";
        
    }
}
