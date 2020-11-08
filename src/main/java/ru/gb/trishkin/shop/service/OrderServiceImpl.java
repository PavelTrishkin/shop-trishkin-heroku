package ru.gb.trishkin.shop.service;

import org.springframework.stereotype.Service;
import ru.gb.trishkin.shop.dao.OrderRepository;
import ru.gb.trishkin.shop.domain.Order;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }
}
