package airbnb;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import java.util.List;

 @RestController
 public class PaymentController {

    @Autowired
    PaymentRepository paymentRepository;

    @RequestMapping(value = "/paycheck/chkPayment",
                        method = RequestMethod.GET,
                        produces = "application/json;charset=UTF-8")

    public boolean chkPayment(HttpServletRequest request, HttpServletResponse response) throws Exception {
                System.out.println("##### /paycheck/chkPayment  called #####");

                // Parameter로 받은 PayID 추출
                long payId = Long.valueOf(request.getParameter("payId"));
                System.out.println("######################## chkPayment roomId : " + payId);

                // PayID 데이터 조회
                Optional<Payment> res = paymentRepository.findById(payId);
                Payment payment = res.get(); // 조회한 payment 데이터
                System.out.println("######################## chkPayment room.getStatus() : " + payment.getStatus());

                // Payment 상태가 paid이면 true (결재한 이력 있는 경우)
                boolean result = false;
                if(payment.getStatus().equals("paid")) {
                        result = true;
                } 

                System.out.println("######################## chkPayment Return : " + result);
                return result;
        }
 }
