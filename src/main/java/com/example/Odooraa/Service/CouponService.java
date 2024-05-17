package com.example.Odooraa.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Odooraa.Repository.CouponRepository;
import com.example.Odooraa.entities.Coupon;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    public Coupon findByCode(String code) {
        return couponRepository.findByCode(code);
    }
}
