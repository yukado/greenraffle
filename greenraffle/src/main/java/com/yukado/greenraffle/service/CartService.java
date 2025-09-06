package com.yukado.greenraffle.service;

import com.yukado.greenraffle.dto.CartDto;
import com.yukado.greenraffle.model.Cart;
import com.yukado.greenraffle.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CartService {

    Cart save(CartDto cartDto);
    Optional<Cart> findById(Long id);
    List<Cart> findAll();
    List<Cart> findByUserId(Long userid);
    Cart deleteByUserId(Long userId);
    void clearCartByUserId(Long userId);
}
