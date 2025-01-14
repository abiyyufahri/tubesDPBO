package com.springboot.tubespbo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.JmsProperties.Listener.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.tubespbo.local_model.JasaDetail;
import com.springboot.tubespbo.local_model.Sessiondata;
import com.springboot.tubespbo.model.Customer;
import com.springboot.tubespbo.model.PenyediaJasa;
import com.springboot.tubespbo.model.RiwayatPesanan;
import com.springboot.tubespbo.model.Voucher;
import com.springboot.tubespbo.repository.CustomerRepository;
import com.springboot.tubespbo.repository.PenyediaJasaRepository;
import com.springboot.tubespbo.repository.RiwayatPesananRepository;
import com.springboot.tubespbo.repository.VoucherRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {

    @Autowired
    VoucherRepository voucherRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RiwayatPesananRepository riwayatPesananRepository;

    @Autowired
    PenyediaJasaRepository penyediaJasaRepository;

    @GetMapping("/dashboard")
    public String index(HttpSession session, Model model) {
        try {
            if (session.getAttribute("loggedUser") == null) {
                return "redirect:/login"; 
            }
            Sessiondata sessiondata = (Sessiondata) session.getAttribute("loggedUser");
            
            if (sessiondata.getRole() == "Customer") {
                Optional<Customer> customer = customerRepository.findById(sessiondata.getUser().getId());
                List<RiwayatPesanan> riwayatPesanans = null;
                if (customer.isPresent()) {
                    riwayatPesanans = riwayatPesananRepository.findByCustomerIdAndStatus(customer.get().getId(), 1);
                    if (riwayatPesanans.isEmpty()) {
                        riwayatPesanans = riwayatPesananRepository.findByCustomerIdAndStatus(customer.get().getId(), 2);
                    }
                }
                model.addAttribute("pesananAktif", riwayatPesanans);
                return "homeDashboard"; 
            }else{
                Optional<PenyediaJasa> penyediaJasa = penyediaJasaRepository.findById(sessiondata.getUser().getId());
                
                List<RiwayatPesanan> allPesanan = null;
                RiwayatPesanan sedangDiambil = null;
                if(penyediaJasa.isPresent()){
                    allPesanan = riwayatPesananRepository.findByJenisJasa(penyediaJasa.get().getJenisKeahlian());
                    sedangDiambil = riwayatPesananRepository.findBySedangDiambil(penyediaJasa.get().getJenisKeahlian(),penyediaJasa.get().getId());
                }
    
                model.addAttribute("sedangDiambil", sedangDiambil);
                model.addAttribute("pesanan", allPesanan);
                return "penyediaJasaDashboard";
            }   
        } catch (Exception e) {
            System.err.println(e);
            return "redirect:/login";
        }
    }

    @GetMapping("/dashboard/riwayat")
    public String riwayatPesanan(HttpSession session, Model model) {
        try {
            Sessiondata sessiondata = (Sessiondata) session.getAttribute("loggedUser");
            if (session.getAttribute("loggedUser") == null) {
                return "redirect:/login"; 
            }
            List<RiwayatPesanan> riwayatPesanans = null;
            
            if("Customer".equals(sessiondata.getRole())){
                Optional<Customer> customer = customerRepository.findById(sessiondata.getUser().getId());
                if(customer.isPresent()){
                    if(customer.get().getAlamat() == null){
                        return "redirect:/dashboard";
                    }   
                    riwayatPesanans = riwayatPesananRepository.findByCustomerId(customer.get().getId());
                }
            }
            else if("Penyedia Jasa".equals(sessiondata.getRole())){
                Optional<PenyediaJasa> penyediaJasa = penyediaJasaRepository.findById(sessiondata.getUser().getId());
                if(penyediaJasa.isPresent()){
                    if(penyediaJasa.get().getJenisKeahlian() == null){
                        return "redirect:/dashboard";
                    }
                    riwayatPesanans = riwayatPesananRepository.findByPenyediaJasaId(sessiondata.getUser().getId());
                }
            }
            model.addAttribute("riwayatPesanan", riwayatPesanans);
            riwayatPesanans.sort((d1, d2) -> d2.getCreatedAt().compareTo(d1.getCreatedAt()));
            return "riwayatDashboard";    
        } catch (Exception e) {
            System.err.println(e);
            return "redirect:/login";
        }
    }

    @GetMapping("/dashboard/voucher")
    public String voucher(HttpSession session, Model model) {
        try {
            Sessiondata sessiondata = (Sessiondata) session.getAttribute("loggedUser");
            if (session.getAttribute("loggedUser") == null) {
                return "redirect:/login"; 
            }

            if(sessiondata.getRole() == "Penyedia Jasa"){
                return "redirect:/dashboard"; 
            }
            Optional<Customer> customer = customerRepository.findById(sessiondata.getUser().getId());
            List<Voucher> voucher = null;
            if(customer.isPresent()){
                if(customer.get().getAlamat() == null){
                    return "redirect:/dashboard";
                }
                voucher = voucherRepository.findByCustomerId(customer.get().getId());
            }
            voucher.sort((d1, d2) -> d2.getCreatedAt().compareTo(d1.getCreatedAt()));
            model.addAttribute("vouchers", voucher);
            return "voucherDashboard";
        } catch (Exception e) {
            System.err.println(e);
            return "redirect:/login";
        } 
    }


    @GetMapping("/dashboard/pesan_jasa/{id}")
    public String pesanJasa(@PathVariable("id") int id, HttpSession session, Model model) {
        try {
            if (session.getAttribute("loggedUser") == null) {
                return "redirect:/login";
            }
            Sessiondata sessiondata = (Sessiondata) session.getAttribute("loggedUser");
            if(sessiondata.getRole() == "Penyedia Jasa"){
                return "redirect:/dashboard"; 
            }
    
            Optional<Customer> customer = customerRepository.findById(sessiondata.getUser().getId());
            if(!customer.isPresent()){
                return "redirect:/login";
            }
    
            if(customer.get().getAlamat() == null){
                return "redirect:/dashboard";
            }
    
            List<Voucher> voucher;
            voucher = voucherRepository.findByCustomerIdAndStatusAktif(customer.get().getId(),true);
    
            Map<Integer, JasaDetail> jasaMap = Map.of(
                1, new JasaDetail("1","Jasa Pembersih Ruangan", 10000),
                2, new JasaDetail("2","Jasa Cat Ruangan", 20000),
                3, new JasaDetail("3","Jasa Servis AC", 30000),
                4, new JasaDetail("4","Jasa Perbaiki TV", 100000),
                5, new JasaDetail("5","Jasa Perbaiki Perabot", 50000),
                6, new JasaDetail("6","Jasa Pemotong Rumput", 25000)
            );
    
        
            JasaDetail jenisjasa = jasaMap.get(id);
            if (jenisjasa == null) {
                return "redirect:/dashboard";
            }
    
            model.addAttribute("jenisjasa", jenisjasa);
            model.addAttribute("voucher", voucher);
            return "pesanJasaDashboard";   
        } catch (Exception e) {
            System.err.println(e);
            return "redirect:/login";
        }
    }

}
