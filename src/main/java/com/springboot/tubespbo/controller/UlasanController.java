package com.springboot.tubespbo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.tubespbo.local_model.Sessiondata;
import com.springboot.tubespbo.model.RiwayatPesanan;
import com.springboot.tubespbo.model.Ulasan;
import com.springboot.tubespbo.repository.RiwayatPesananRepository;
import com.springboot.tubespbo.repository.UlasanRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class UlasanController {
    
    @Autowired
    RiwayatPesananRepository riwayatPesananRepository;

    @Autowired
    UlasanRepository ulasanRepository;

    @PostMapping("/dashboard/riwayat/ulasan/submit")
    public String kirimUlasan(
            @RequestParam("id_pesanan") String id_pesanan,
            @RequestParam("rating") int rating,
            @RequestParam("komentar") String komentar,
            HttpSession session,
            RedirectAttributes redirAttrs,
            Model model
    ){
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/login";
        }
        Sessiondata sessiondata = (Sessiondata) session.getAttribute("loggedUser");
        if(sessiondata.getRole() == "Penyedia Jasa"){
            return "redirect:/dashboard"; 
        }

        Optional<RiwayatPesanan> riwayatPesanan = riwayatPesananRepository.findById(Long.parseLong(id_pesanan));
        if(riwayatPesanan.isPresent()){
            RiwayatPesanan rPesanan = riwayatPesanan.get();
            Ulasan ulasan = new Ulasan(riwayatPesanan.get(),rating, komentar);
            ulasanRepository.save(ulasan);
            rPesanan.setUlasan(ulasan);
            riwayatPesananRepository.save(rPesanan);
        }else{
            redirAttrs.addFlashAttribute("message", "Ulasan gagal dikirim");
            return "redirect:/dashboard/riwayat";
        }

        return "redirect:/dashboard/riwayat";
    }
}
