package com.msys.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.msys.entity.OrderItem;


@Transactional
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	public OrderItem findByQuantity(int quantity);
}
