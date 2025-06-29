package com.detorresrc.foodorderingsystem.order.service.domain;

import com.detorresrc.foodorderingsystem.order.service.domain.dto.create.CreateOrderCommand;
import com.detorresrc.foodorderingsystem.order.service.domain.dto.create.CreateOrderResponse;
import com.detorresrc.foodorderingsystem.order.service.domain.dto.create.OrderAddress;
import com.detorresrc.foodorderingsystem.order.service.domain.dto.create.OrderItem;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Customer;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Order;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Product;
import com.detorresrc.foodorderingsystem.order.service.domain.entity.Restaurant;
import com.detorresrc.foodorderingsystem.order.service.domain.exception.OrderDomainException;
import com.detorresrc.foodorderingsystem.order.service.domain.mapper.OrderDataMapper;
import com.detorresrc.foodorderingsystem.order.service.domain.ports.input.service.OrderApplicationService;
import com.detorresrc.foodorderingsystem.order.service.domain.ports.output.repository.CustomerRepository;
import com.detorresrc.foodorderingsystem.order.service.domain.ports.output.repository.OrderRepository;
import com.detorresrc.foodorderingsystem.order.service.domain.ports.output.repository.RestaurantRepository;
import com.detorresrc.foodorderingsystem.valueobject.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = OrderTestConfiguration.class)
public class OrderApplicationServiceTest {

    @Autowired
    private OrderApplicationService orderApplicationService;

    @Autowired
    private OrderDataMapper orderDataMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private CreateOrderCommand createOrderCommand;
    private CreateOrderCommand createOrderCommandWrongPrice;
    private CreateOrderCommand createOrderCommandWrongProductPrice;
    private final UUID CUSTOMER_ID = UUID.fromString("6ba6ce23-d1c9-4933-a5fe-69fa4b2be373");
    private final UUID RESTAURANT_ID = UUID.fromString("98fb12c8-69cb-43d4-8c2e-952eb66e33a6");
    private final UUID PRODUCT_ID = UUID.fromString("16a487da-e428-4e66-891d-be9759efe5ef");
    private final UUID ORDER_ID = UUID.fromString("9e4196d9-2069-4f93-a57a-8f33a8c06f8f");
    private final BigDecimal PRICE = new BigDecimal("200.00");

    @BeforeAll
    public void init() {
        createOrderCommand = CreateOrderCommand.builder()
            .customerId(CUSTOMER_ID)
            .restaurantId(RESTAURANT_ID)
            .address(OrderAddress.builder()
                .street("street_1")
                .postalCode("3022")
                .city("Bulacan")
                .build())
            .price(PRICE)
            .items(List.of(
                OrderItem.builder()
                    .productId(PRODUCT_ID)
                    .quantity(1)
                    .price(new BigDecimal("50.00"))
                    .subTotal(new BigDecimal("50.00"))
                    .build(),
                OrderItem.builder()
                    .productId(PRODUCT_ID)
                    .quantity(3)
                    .price(new BigDecimal("50.00"))
                    .subTotal(new BigDecimal("150.00"))
                    .build()
            ))
            .build();

        createOrderCommandWrongPrice = CreateOrderCommand.builder()
            .customerId(CUSTOMER_ID)
            .restaurantId(RESTAURANT_ID)
            .address(OrderAddress.builder()
                .street("street_1")
                .postalCode("3022")
                .city("Bulacan")
                .build())
            .price(new BigDecimal("250.00"))
            .items(List.of(
                OrderItem.builder()
                    .productId(PRODUCT_ID)
                    .quantity(1)
                    .price(new BigDecimal("50.00"))
                    .subTotal(new BigDecimal("50.00"))
                    .build(),
                OrderItem.builder()
                    .productId(PRODUCT_ID)
                    .quantity(3)
                    .price(new BigDecimal("50.00"))
                    .subTotal(new BigDecimal("150.00"))
                    .build()
            ))
            .build();

        createOrderCommandWrongProductPrice = CreateOrderCommand.builder()
            .customerId(CUSTOMER_ID)
            .restaurantId(RESTAURANT_ID)
            .address(OrderAddress.builder()
                .street("street_1")
                .postalCode("3022")
                .city("Bulacan")
                .build())
            .price(new BigDecimal("210.00"))
            .items(List.of(
                OrderItem.builder()
                    .productId(PRODUCT_ID)
                    .quantity(1)
                    .price(new BigDecimal("60.00"))
                    .subTotal(new BigDecimal("60.00"))
                    .build(),
                OrderItem.builder()
                    .productId(PRODUCT_ID)
                    .quantity(3)
                    .price(new BigDecimal("50.00"))
                    .subTotal(new BigDecimal("150.00"))
                    .build()
            ))
            .build();

        Customer customer = new Customer();
        customer.setId(new CustomerId(CUSTOMER_ID));

        Restaurant restaurant = Restaurant.Builder.builder()
            .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
            .products(List.of(
                new Product(new ProductId(PRODUCT_ID), "product-1", new Money(new BigDecimal("50.00"))),
                new Product(new ProductId(PRODUCT_ID), "product-2", new Money(new BigDecimal("50.00")))
            ))
            .active(true)
            .build();

        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        order.setId(new OrderId(ORDER_ID));

        when(customerRepository.findCustomer(any(UUID.class)))
            .thenReturn(Optional.of(customer));

        when(restaurantRepository.findRestaurantInformation(any(Restaurant.class)))
            .thenReturn(Optional.of(restaurant));

        when(orderRepository.save(any(Order.class)))
            .thenReturn(order);
    }

    @Test
    public void testCreateOrder() {
        CreateOrderResponse createOrderResponse = orderApplicationService.createOrder(createOrderCommand);

        assertThat(createOrderResponse.getOrderStatus()).isEqualTo(OrderStatus.PENDING);
        assertThat(createOrderResponse.getMessage()).isEqualTo("Order created successfully");
        assertThat(createOrderResponse.getOrderTrackingId()).isNotNull();
    }

    @Test
    public void testCreateOrderWithWrongTotalPrice() {
        assertThatThrownBy(() -> orderApplicationService.createOrder(createOrderCommandWrongPrice))
            .isInstanceOf(OrderDomainException.class)
            .hasMessage("Order total price must be equal to the sum of item prices");
    }

    @Test
    public void testCreateOrderWithWrongProductPrice() {
        assertThatThrownBy(() -> orderApplicationService.createOrder(createOrderCommandWrongProductPrice))
            .isInstanceOf(OrderDomainException.class)
            .hasMessage("Order item price is invalid");
    }

    @Test
    public void testCreateOrderWithPassiveRestaurant() {
        Restaurant restaurant = Restaurant.Builder.builder()
            .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
            .products(List.of(
                new Product(new ProductId(PRODUCT_ID), "product-1", new Money(new BigDecimal("50.00"))),
                new Product(new ProductId(PRODUCT_ID), "product-2", new Money(new BigDecimal("50.00")))
            ))
            .active(false)
            .build();

        when(restaurantRepository.findRestaurantInformation(any(Restaurant.class)))
            .thenReturn(Optional.of(restaurant));

        assertThatThrownBy(() -> orderApplicationService.createOrder(createOrderCommand))
            .isInstanceOf(OrderDomainException.class)
            .hasMessage("Restaurant is not active: " + createOrderCommand.getRestaurantId());
    }
}
