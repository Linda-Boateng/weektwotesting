package com.example.weektwotesting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl {
    private final OrderRepository orderRepository;



    public Order saveOrder(Order order) {
     return  orderRepository.save(order);
}}
