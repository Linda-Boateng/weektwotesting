package com.example.weektwotesting;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@SpringJUnitConfig
@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc
class OrderServiceImplIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderServiceImpl orderService;

  @Test
  void testAddOrder() throws Exception {
        // Arrange
        Order order = new Order();
        order.setId(1L);
        order.setProductName("Test Product");
        order.setQuantity(5);

        Mockito.doNothing().when(orderService).saveOrder(Mockito.any(Order.class));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/order")
                        .content(asJsonString(order))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // Helper method to convert object to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
