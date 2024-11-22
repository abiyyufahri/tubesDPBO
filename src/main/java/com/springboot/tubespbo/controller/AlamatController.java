package com.springboot.tubespbo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.tubespbo.local_model.Sessiondata;
import com.springboot.tubespbo.model.Alamat;
import com.springboot.tubespbo.model.Customer;
import com.springboot.tubespbo.repository.CustomerRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AlamatController {

    @Autowired
    CustomerRepository customerRepository;

    @PostMapping("/dashboard/tambah_alamat")
    public String addAlamatToCustomer(
            @RequestParam("namaJalan") String namaJalan,
            @RequestParam("nomorRumah") String nomorRumah,
            @RequestParam("kota") String kota,
            @RequestParam("provinsi") String provinsi,
            @RequestParam("kodePos") String kodePos,
            @RequestParam("negara") String negara,
            HttpSession session) {

        Sessiondata sessiondata = (Sessiondata) session.getAttribute("loggedUser");

        if (sessiondata != null) {
            Customer customer = (Customer) sessiondata.getUser();
            if (customer != null) {
                Alamat alamat = new Alamat(namaJalan, nomorRumah, kota, provinsi, kodePos, negara);
                customer.setAlamat(alamat);
                customerRepository.save(customer);
                Sessiondata newSessiondata = new Sessiondata(customer, "Customer");
                session.setAttribute("loggedUser", newSessiondata);
            } else {
                throw new RuntimeException("Customer not found");
            }
        }

        return "redirect:/dashboard";
    }

}
