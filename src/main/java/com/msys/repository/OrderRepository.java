package com.msys.repository;

import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.msys.entity.Order;

@Transactional
public interface OrderRepository extends JpaRepository<Order, Long> {	 

	public Order findByDeliveryDate(Date deliveryDate);
}
