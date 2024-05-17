package com.example.Odooraa.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Odooraa.entities.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Coupon findByCode(String code);
}
