package com.curth.couponservice.thymleaf;

import com.curth.couponservice.model.Coupon;
import com.curth.couponservice.repository.CouponRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin
@Controller
public class CouponControllerTemplate {

    @Autowired
    private CouponRepository repo;

    @GetMapping("/showCreateCoupon")
    public String showCreateCoupon() {
        return "createCoupon";
    }

    @PostMapping("/saveCoupon")
    public String save(Coupon coupon) {
        repo.save(coupon);
        return "createResponse";
    }

    @GetMapping("/showGetCoupon")
    public String showGetCoupon() {
        return "getCoupon";
    }

    @PostMapping("/getCoupon")
    public ModelAndView getCoupon(String code) {
        ModelAndView mav = new ModelAndView("couponDetails");
        System.out.println(code);
        Coupon coupon = repo.findByCode(code);
        if (coupon == null) {
            throw new ObjectNotFoundException("Coupon now found", Coupon.class);
        }
        mav.addObject(coupon);
        return mav;
    }

}
