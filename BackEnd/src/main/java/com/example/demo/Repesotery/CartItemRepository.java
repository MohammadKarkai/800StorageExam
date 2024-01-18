package com.example.demo.Repesotery;

import com.example.demo.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    CartItem findByCart_IdAndProduct_Id(Long CartId, Long ProductId);
    List<CartItem> findByCart_Id(Long CartId);

}
