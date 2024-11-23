package com.springboot.tubespbo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.tubespbo.local_model.Sessiondata;
import com.springboot.tubespbo.model.Customer;
import com.springboot.tubespbo.model.Pembayaran;
import com.springboot.tubespbo.model.RiwayatPesanan;
import com.springboot.tubespbo.model.Voucher;
import com.springboot.tubespbo.repository.CustomerRepository;
import com.springboot.tubespbo.repository.VoucherRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class VoucherController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    VoucherRepository voucherRepository;

    @PostMapping("/dashboard/voucher/tambah_voucher")
    public String pesanJasa(
            @RequestParam("kode") String kode,
            @RequestParam("potongan") int potongan,
            @RequestParam("kadaluarsa") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate kadaluarsa,
            HttpSession session,
            RedirectAttributes redirAttrs,
            Model model) {
        Sessiondata sessiondata;
        sessiondata = (Sessiondata) session.getAttribute("loggedUser");
        if (sessiondata == null) {
            return "redirect:/login";
        }
        Customer customer = (Customer) sessiondata.getUser();
        Voucher voucher = new Voucher(kode, potongan, kadaluarsa, customer);
        customer.addVoucher(voucher);
        voucherRepository.save(voucher);
        customerRepository.save(customer);

        Sessiondata newSessiondata = new Sessiondata(customer, "Customer");
        session.setAttribute("loggedUser", newSessiondata);

        return "redirect:/dashboard/voucher";
    }
}
