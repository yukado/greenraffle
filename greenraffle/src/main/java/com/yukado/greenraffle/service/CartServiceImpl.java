package com.yukado.greenraffle.service;

import com.yukado.greenraffle.dto.CartDto;
import com.yukado.greenraffle.model.Cart;
import com.yukado.greenraffle.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;

    @Override
    public Cart save(CartDto cartDto) {
        Cart cart = new Cart(cartDto.getUserId(), cartDto.getUserName(), cartDto.getTicketCity(),
                cartDto.getRaffleId(), cartDto.getPrice(), cartDto.getWinamount(), cartDto.getQuantity(),
                cartDto.getStartDateTime(), cartDto.getEndDateTime(), cartDto.getDescription());
        return cartRepo.save(cart);
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepo.findById(id);
    }

    @Override
    public List<Cart> findByUserId(Long userId) {
        return cartRepo.findByUserId(userId);
    }

    @Override
    public Cart deleteByUserId(Long userId) {
        return cartRepo.deleteByUserId(userId);
    }

    @Override
    public List<Cart> findAll() {
        return cartRepo.findAll();
    }

    @Override
    @Transactional
    public void clearCartByUserId(Long userId) {
        // Einträge des Warenkorbs abrufen
        List<Cart> carts = cartRepo.findByUserId(userId);
        // Alle gefundenen Einträge löschen
        for (Cart cart : carts) {
            cartRepo.delete(cart);
        }
        // Alternativ: cartRepo.deleteAll(carts);
    }
}
