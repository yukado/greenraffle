package com.yukado.greenraffle.repo;

import com.yukado.greenraffle.model.Cart;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CartRepo extends CrudRepository<Cart, Long> {

    List<Cart> findAll();

    List<Cart> findByUserId(Long userId);

    Cart deleteByUserId(Long userId);
}
