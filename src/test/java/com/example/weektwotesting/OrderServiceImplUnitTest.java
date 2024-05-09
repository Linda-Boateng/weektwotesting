package com.example.weektwotesting;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class OrderServiceImplUnitTest {
    @MockBean
    private OrderRepository orderRepository;
    
    @Autowired
    OrderServiceImpl orderService;

    @Test
    void testSaveOrder() {
        Order order = new Order();
        order.setId(123L);

        when(orderRepository.save(Mockito.any())).thenReturn(order);

        orderService.saveOrder(order);

        Mockito.verify(orderRepository,Mockito.times(1)).save(Mockito.any());
    }
}
