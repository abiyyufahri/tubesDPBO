package com.springboot.tubespbo.controller;

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
import com.springboot.tubespbo.model.PenyediaJasa;
import com.springboot.tubespbo.model.RiwayatPesanan;
import com.springboot.tubespbo.model.User;
import com.springboot.tubespbo.model.Voucher;
import com.springboot.tubespbo.repository.CustomerRepository;
import com.springboot.tubespbo.repository.PenyediaJasaRepository;
import com.springboot.tubespbo.repository.RiwayatPesananRepository;
import com.springboot.tubespbo.repository.VoucherRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class RiwayatPesananController {

    @Autowired
    RiwayatPesananRepository riwayatPesananRepository;

    @Autowired
    VoucherRepository voucherRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PenyediaJasaRepository penyediaJasaRepository;

    @PostMapping("/dashboard/pesan_jasa/submit")
    public String pesanJasa(
            @RequestParam("jenisJasa") String jenisJasa,
            @RequestParam("waktu") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime waktu,
            @RequestParam("kuantitas") int kuantitas,
            @RequestParam("idVoucher") String idVoucher,
            @RequestParam("harga") int harga,
            HttpSession session,
            RedirectAttributes redirAttrs,
            Model model) {
        Sessiondata sessiondata;
        sessiondata = (Sessiondata) session.getAttribute("loggedUser");
        if (sessiondata == null) {
            return "redirect:/login";
        }
        Customer customer = (Customer) sessiondata.getUser();

        RiwayatPesanan sedangMemesan = riwayatPesananRepository.findBySedangMemesan( customer.getId());
        if (sedangMemesan != null && !jenisJasa.equals("0")) {
            redirAttrs.addFlashAttribute("message", "Ada pesanan yang sedang berjalan");
            return "redirect:/dashboard/pesan_jasa/" + jenisJasa;
        }

        Pembayaran pembayaran = new Pembayaran(harga * kuantitas);
        Optional<Voucher> voucher = null;
        if(!idVoucher.equals("0")){
            voucher = voucherRepository.findById(Long.parseLong(idVoucher));
        }
        if(voucher != null && voucher.isPresent()){
            pembayaran.setVoucher(voucher.get());
        }
        RiwayatPesanan riwayatPesanan = new RiwayatPesanan(jenisJasa, 1, waktu, pembayaran, customer);
        customer.addRiwayatPesanan(riwayatPesanan);
        customerRepository.save(customer);
        return "redirect:/dashboard";
    }

    @PostMapping("/dashboard/terima_jasa")
    public String terimaJasa(
            @RequestParam("riwayat") String idPesanan,
            HttpSession session,
            RedirectAttributes redirAttrs,
            Model model) {
        Sessiondata sessiondata;
        sessiondata = (Sessiondata) session.getAttribute("loggedUser");
        if (sessiondata == null) {
            return "redirect:/login";
        }
        PenyediaJasa penyediaJasa = (PenyediaJasa) sessiondata.getUser();

        Optional<RiwayatPesanan> pesanan = riwayatPesananRepository.findById(Long.parseLong(idPesanan));
        if (pesanan.isPresent()) {
            pesanan.get().setPenyediaJasa(penyediaJasa);
            pesanan.get().setStatus(2);
            penyediaJasa.setTersedia(false);
            riwayatPesananRepository.save(pesanan.get());
            penyediaJasaRepository.save(penyediaJasa);

            Sessiondata newSessiondata = new Sessiondata(penyediaJasa, sessiondata.getRole());
            session.setAttribute("loggedUser", newSessiondata);
        }
        return "redirect:/dashboard";
    }

    @PostMapping("/dashboard/selesai_jasa")
    public String selesaiJasa(
            @RequestParam("riwayat") String idPesanan,
            HttpSession session,
            RedirectAttributes redirAttrs,
            Model model) {
        Sessiondata sessiondata;
        sessiondata = (Sessiondata) session.getAttribute("loggedUser");
        if (sessiondata == null) {
            return "redirect:/login";
        }
        PenyediaJasa penyediaJasa = (PenyediaJasa) sessiondata.getUser();

        Optional<RiwayatPesanan> pesanan = riwayatPesananRepository.findById(Long.parseLong(idPesanan));
        
        
        if (pesanan.isPresent()) {
            pesanan.get().setPenyediaJasa(penyediaJasa);
            pesanan.get().setStatus(10);
            penyediaJasa.setTersedia(true);
            riwayatPesananRepository.save(pesanan.get());
            penyediaJasaRepository.save(penyediaJasa);

            Sessiondata newSessiondata = new Sessiondata(penyediaJasa, sessiondata.getRole());
            session.setAttribute("loggedUser", newSessiondata);
        }
        return "redirect:/dashboard";
    }
}
