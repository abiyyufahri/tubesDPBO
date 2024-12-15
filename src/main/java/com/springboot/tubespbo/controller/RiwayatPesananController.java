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
import com.springboot.tubespbo.repository.PembayaranRepository;
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

    @Autowired
    PembayaranRepository pembayaranRepository;

    @PostMapping("/dashboard/pesan_jasa/submit")
    public String pesanJasa(
            @RequestParam("jenisJasa") String jenisJasa,
            @RequestParam("waktu") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime waktu,
            @RequestParam("kuantitas") int kuantitas,
            @RequestParam("idVoucher") String idVoucher,
            @RequestParam("harga") int harga,
            @RequestParam("jenisPembayaran") String jenisPembayaran,
            HttpSession session,
            RedirectAttributes redirAttrs,
            Model model) {

        try {
            Sessiondata sessiondata = (Sessiondata) session.getAttribute("loggedUser");
            if (sessiondata == null) {
                return "redirect:/login";
            }
            Customer customer = (Customer) sessiondata.getUser();

            if (waktu.isBefore(LocalDateTime.now())) {
                redirAttrs.addFlashAttribute("message", "Waktu tidak valid");
                return "redirect:/dashboard/pesan_jasa/" + jenisJasa;
            }

            if (jenisPembayaran.equals("0")) {
                redirAttrs.addFlashAttribute("message", "Pilih jenis pembayaran");
                return "redirect:/dashboard/pesan_jasa/" + jenisJasa;
            }

            RiwayatPesanan sedangMemesan = riwayatPesananRepository.findBySedangMemesan(customer.getId());
            if (sedangMemesan != null) {
                redirAttrs.addFlashAttribute("message", "Ada pesanan yang sedang berjalan");
                return "redirect:/dashboard/pesan_jasa/" + jenisJasa;
            }

            Pembayaran pembayaran = new Pembayaran(harga * kuantitas,jenisPembayaran);

            Optional<Voucher> voucherOptional = Optional.empty();
            if (!idVoucher.equals("0")) {
                voucherOptional = voucherRepository.findById(Long.parseLong(idVoucher));
            }

            if (voucherOptional.isPresent()) {
                Voucher voucher = voucherOptional.get();

                if (voucher.isStatusAktif()) {
                    voucher.setStatusAktif(false);
                    voucherRepository.save(voucher);

                    pembayaran.setVoucher(voucher);
                } else {
                    redirAttrs.addFlashAttribute("message", "Voucher sudah digunakan atau tidak aktif");
                    return "redirect:/dashboard/pesan_jasa/" + jenisJasa;
                }
            }

            pembayaranRepository.save(pembayaran);

            RiwayatPesanan riwayatPesanan = new RiwayatPesanan(jenisJasa, 1, waktu, pembayaran, customer);

            riwayatPesananRepository.save(riwayatPesanan);

            Sessiondata newSessiondata = new Sessiondata(customer, "Customer");
            session.setAttribute("loggedUser", newSessiondata);
            return "redirect:/dashboard";
        } catch (Exception e) {
            System.err.println(e);
            return "redirect:/login";
        }
    }

    @PostMapping("/dashboard/batalkan_jasa")
    public String batalkanJasa(
            @RequestParam("id_pesanan") String idPesanan,
            HttpSession session,
            RedirectAttributes redirAttrs,
            Model model) {
        try {
            Sessiondata sessiondata;
            sessiondata = (Sessiondata) session.getAttribute("loggedUser");
            if (sessiondata == null) {
                return "redirect:/login";
            }

            Optional<RiwayatPesanan> pesanan = riwayatPesananRepository.findById(Long.parseLong(idPesanan));
            if (pesanan.isPresent()) {
                if (pesanan.get().getPenyediaJasa() != null) {
                    redirAttrs.addFlashAttribute("message", "Pesanan telah diterima penyedia jasa");
                    return "redirect:/dashboard/riwayat";
                }
                pesanan.get().setStatus(11);
                riwayatPesananRepository.save(pesanan.get());
            }
            return "redirect:/dashboard/riwayat";
        } catch (Exception e) {
            System.out.println("Error batalkan jasa : " + e);
            return "redirect:/dashboard/riwayat";
        }
    }

    @PostMapping("/dashboard/terima_jasa")
    public String terimaJasa(
            @RequestParam("riwayat") String idPesanan,
            HttpSession session,
            RedirectAttributes redirAttrs,
            Model model) {
        try {
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
        } catch (Exception e) {
            System.out.println("Error terima jasa : " + e);
            return "redirect:/dashboard";
        }
    }

    @PostMapping("/dashboard/selesai_jasa")
public String selesaiJasa(
        @RequestParam("riwayat") String idPesanan,
        HttpSession session,
        RedirectAttributes redirAttrs,
        Model model) {
    try {
        Sessiondata sessiondata = (Sessiondata) session.getAttribute("loggedUser");
        if (sessiondata == null) {
            return "redirect:/login";
        }

        Optional<RiwayatPesanan> pesanan = riwayatPesananRepository.findById(Long.parseLong(idPesanan));

        if (pesanan.isPresent()) {
            RiwayatPesanan rPesanan = pesanan.get();

            rPesanan.setStatus(10);
            if(rPesanan.getTempChatRoom() != null){
                rPesanan.getTempChatRoom().setStatus(false);
            }
            rPesanan.getPenyediaJasa().setTersedia(true);

            // Log sebelum menyimpan
            System.out.println("Sebelum menyimpan: Status jasa = " + rPesanan.getStatus());

            riwayatPesananRepository.save(rPesanan);
            session.setAttribute("loggedUser", new Sessiondata(rPesanan.getPenyediaJasa(), sessiondata.getRole()));
        } else {
            System.out.println("Pesanan dengan ID " + idPesanan + " tidak ditemukan.");
        }
        return "redirect:/dashboard";
    } catch (Exception e) {
        System.out.println("Error selesai jasa: " + e.getMessage());
        e.printStackTrace();
        return "redirect:/dashboard";
    }
}

    
}
