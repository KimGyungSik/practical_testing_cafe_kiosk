package sample.cafekiosk.spring.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static sample.cafekiosk.spring.domain.order.OrderStatus.PAYMENT_COMPLETED;

@ActiveProfiles("test")
@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @DisplayName("해당 일자에 결제완료된 주문들을 가져온다.")
    @Test
    void findOrdersBy() {
        // given
        LocalDateTime startTime = LocalDateTime.of(2023, 10, 19, 0, 0);
        LocalDateTime orderTime = LocalDateTime.of(2023, 10, 19, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 10, 20, 0, 0);
        Order order1 = createOrder(orderTime);
        Order order2 = createOrder(orderTime);
        Order order3 = createOrder(orderTime);
        orderRepository.saveAll(List.of(order1, order2, order3));

        // when
        List<Order> orders = orderRepository.findOrdersBy(startTime, endTime, PAYMENT_COMPLETED);

        // then
        assertThat(orders).hasSize(3)
                .extracting("id", "orderStatus", "totalPrice", "registeredDateTime")
                .containsExactlyInAnyOrder(
                        tuple(1L, PAYMENT_COMPLETED, 0, orderTime),
                        tuple(2L, PAYMENT_COMPLETED, 0, orderTime),
                        tuple(3L, PAYMENT_COMPLETED, 0, orderTime)
                );
    }

    private Order createOrder(LocalDateTime orderDate) {
        return Order.builder()
                .products(null)
                .orderStatus(PAYMENT_COMPLETED)
                .registeredDateTime(orderDate)
                .build();
    }

}