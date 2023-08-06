package com.shoptt.rest;

import com.shoptt.dto.PaymentDto;
import com.shoptt.dto.ResponsePayment;
import com.shoptt.entity.AddressUser;
import com.shoptt.entity.Invoice;
import com.shoptt.repository.AddressUserRepository;
import com.shoptt.repository.HistoryPayRepository;
import com.shoptt.repository.InvoiceRepository;
import com.shoptt.repository.TrangThaiRepository;
import com.vnua.edu.hieu.config.Environment;
import com.vnua.edu.hieu.enums.RequestType;
import com.vnua.edu.hieu.models.PaymentResponse;
import com.vnua.edu.hieu.models.QueryStatusTransactionResponse;
import com.vnua.edu.hieu.processor.CreateOrderMoMo;
import com.vnua.edu.hieu.processor.QueryTransactionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class MomoPayment {

    @Autowired
    private HistoryPayRepository historyPayRepository;

    @Autowired
    private AddressUserRepository addressUserRepository;

    @Autowired
    private TrangThaiRepository trangThaiRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @PostMapping("/urlpayment")
    public ResponsePayment getUrlPayment(@RequestBody PaymentDto paymentDto){
        String orderId = String.valueOf(System.currentTimeMillis());
        String requestId = String.valueOf(System.currentTimeMillis());
        Environment environment = Environment.selectEnv("dev");
        PaymentResponse captureATMMoMoResponse = null;
        try {
            captureATMMoMoResponse = CreateOrderMoMo.process(environment, orderId, requestId, Long.toString(paymentDto.getAmount()), paymentDto.getContent(), paymentDto.getReturnUrl(), paymentDto.getNotifyUrl(), "", RequestType.PAY_WITH_ATM, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("url ====: "+captureATMMoMoResponse.getPayUrl());
        ResponsePayment responsePayment = new ResponsePayment(captureATMMoMoResponse.getPayUrl(),orderId,requestId);
        return responsePayment;
    }

    @GetMapping("/checkPayment")
    public Invoice checkPayment(@RequestParam("orderId") String orderId, @RequestParam("requestId") String requestId) throws Exception {
        Environment environment = Environment.selectEnv("dev");
        QueryStatusTransactionResponse queryStatusTransactionResponse = QueryTransactionStatus.process(environment, orderId, requestId);
        System.out.println("qqqq-----------------------------------------------------------"+queryStatusTransactionResponse.getMessage());
        if(queryStatusTransactionResponse.getResultCode() == 0){
            if(historyPayRepository.findByOrderIdAndRequestId(orderId, requestId).isPresent() == false){
                Invoice invoice = new Invoice();
                invoice.setCreatedDate(new Date(System.currentTimeMillis()));
                invoice.setPayType(1);
                invoice.setTotalAmount(Double.valueOf(queryStatusTransactionResponse.getAmount()));
                invoice.setTrangThai(trangThaiRepository.findById(1L).get());
                return invoice;
            }
        }
        return null;
    }
}
