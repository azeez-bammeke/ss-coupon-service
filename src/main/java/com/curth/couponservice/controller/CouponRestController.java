package com.curth.couponservice.controller;

import com.curth.couponservice.model.Coupon;
import com.curth.couponservice.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/couponapi")
public class CouponRestController {

    @Autowired
    CouponRepository couponRepository;

    @PostMapping("/coupon")
    public Coupon createCoupon(@RequestBody Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @GetMapping("/coupon/{code}")
    public Coupon getCouponByCode(@PathVariable String code) {
        return couponRepository.findByCode(code);
    }
}
