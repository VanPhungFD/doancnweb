package com.shoptt.rest;

import com.shoptt.entity.*;
import com.shoptt.repository.*;
import com.shoptt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class InvoiceRest {

    @Autowired
    private TrangThaiRepository trangThaiRepository;

    @Autowired
    private TrangThaiDonHangRepository trangThaiDonHangRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private DetailInvoiceRepository detailInvoiceRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user/addInvoice")
    public Invoice save(@RequestBody Invoice invoice){
        Invoice result = invoiceRepository.save(invoice);
        return result;
    }

    @PostMapping("/user/addInvoicett")
    public Invoice saveOffline(@RequestBody Invoice invoice){
        invoice.setTrangThai(trangThaiRepository.findById(1L).get());
        invoice.setPayType(0);
        invoice.setCreatedDate(new Date(System.currentTimeMillis()));
        Invoice result = invoiceRepository.save(invoice);
        return result;
    }

    @GetMapping("/user/invoiceMySelf")
    public List<Invoice> findByUser(){
        User user = userService.getUserWithAuthority();
        return invoiceRepository.findByUserId(user.getId());
    }

    @GetMapping("/admin/allInvoice")
    public List<Invoice> findAll(){
        return invoiceRepository.findAlls();
    }

    @GetMapping("/admin/allInvoicefull")
    public List<Invoice> findByTt(@RequestParam(value = "id", required = false) Long id,
                                  @RequestParam(value = "start", required = false) Date start,
                                  @RequestParam(value = "end", required = false) Date end){
        System.out.println("=== start: "+start);
        if(start == null && end == null && id != null){
            return invoiceRepository.findByTT(id);
        }
        else if(id == null && start != null && end != null){
            return invoiceRepository.findByNgay(start, end);
        }
        else{
            return invoiceRepository.findByTTAndNgay(id, start, end);
        }
    }

    @DeleteMapping("/user/huydon")
    public void huyDon(@RequestParam("id") Long id) throws Exception {
        User user = userService.getUserWithAuthority();
        Invoice invoice = invoiceRepository.findById(id).get();
        if(invoice.getAddressUser().getUser().getId() != user.getId()){
            throw new Exception("khong du quyen");
        }
        if(invoice.getTrangThai().getId() == 1 || invoice.getTrangThai().getId() == 2){
            invoice.setTrangThai(trangThaiRepository.findById(6L).get());
            invoiceRepository.save(invoice);
            List<DetailInvoice> list = detailInvoiceRepository.findByInvoiceId(invoice.getId());
            for(DetailInvoice d : list){
                Product p = productRepository.findById(d.getProduct().getId()).get();
                p.setQuantity(p.getQuantity() + d.getQuantity());
                productRepository.save(p);
            }
        }
        else{
            throw new Exception("khong the huy");
        }
    }

    @PostMapping("/admin/updatetrangthai")
    public void updateTrangThaiDh(@RequestParam("id") Long id, @RequestParam("trangthaiid") Long trangthaiId) throws Exception {
        Invoice invoice = invoiceRepository.findById(id).get();
        TrangThai trangThai = trangThaiRepository.findById(trangthaiId).get();
        invoice.setTrangThai(trangThai);
        invoiceRepository.save(invoice);
    }

    @GetMapping("/admin/calMoneySixMonth")
    public List<String> getAmountSixMonth(){
        List<String> list = new ArrayList<>();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String currunYear = timestamp.toString().split(" ")[0].split("-")[0];
        String currunMonth = timestamp.toString().split(" ")[0].split("-")[1];
        System.out.println("--- month: "+currunMonth);
        System.out.println("--- year: "+currunYear);
        Integer month = Integer.valueOf(currunMonth);
        Integer year = Integer.valueOf(currunYear);
        for(int i =0; i< 6; i++){
            Double money = invoiceRepository.calStatiticsOnMonth(month,year);
            if(money == null){
                money = 0D;
            }
            String str = month.toString()+","+money.toString();
            list.add(str);
            --month;
            if(month == 0){
                month = 12;
                --year;
            }
        }
        return list;
    }

    @GetMapping("/admin/thongkeindex")
    public List<Double> thongKeIndex(){
        List<Double> list = new ArrayList<>();
        list.add(invoiceRepository.tongDonHang());
        list.add(productRepository.tongSp());
        list.add(userRepository.tongUser());
        list.add(userRepository.tongUser());
        return list;
    }

    @GetMapping("/admin/donhangmoi")//lấy ra các đơn hàng mới trong tháng hiện tại.
    public List<Invoice> donHangMoi(){
        String date = new Date(System.currentTimeMillis()).toString();
        Integer month = Integer.valueOf(date.split("-")[1]);
        Integer year = Integer.valueOf(date.split("-")[0]);
        return invoiceRepository.findByThisMonth(month, year);
    }
}
