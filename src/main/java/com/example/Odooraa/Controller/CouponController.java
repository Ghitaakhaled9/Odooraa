package com.example.Odooraa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Odooraa.Service.CouponService;
import com.example.Odooraa.entities.Coupon;
import com.example.Odooraa.entities.Panier;

@Controller
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/applyCoupon")
    public ResponseEntity<?> applyCoupon(@RequestParam String couponCode, @RequestParam double currentTotal) {
        System.out.println("couponnnn" + couponCode);
        Coupon coupon = couponService.findByCode(couponCode);
        System.out.println("coupon" + couponCode);
        if (coupon == null) {
            return ResponseEntity.badRequest().body("Invalid coupon code.");
        }

        // Apply coupon discount to current total and calculate new total
        double newTotal = calculateTotalWithCoupon(currentTotal, coupon);
        System.out.println("currentTotal" + currentTotal);

        // Return the updated total with a success message
        return ResponseEntity.ok().body("Coupon applied successfully. New total: " + newTotal);
    }

    private double calculateTotalWithCoupon(double currentTotal, Coupon coupon) {
        // Coupon discount calculation logic
        double discountAmount = currentTotal * (coupon.getValue() / 100);
        System.out.println("discountAmount" + discountAmount);
        return currentTotal - discountAmount;
    }

}