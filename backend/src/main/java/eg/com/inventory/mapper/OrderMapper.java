package eg.com.inventory.mapper;

import eg.com.inventory.dto.OrderDTO;
import eg.com.inventory.entity.Order;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDTO toDTO(Order order) {
        if (order == null) {
            return null;
        }

        return new OrderDTO(
                order.getId(),
                order.getCustomer().getId(),
                order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName(),
                order.getOrderStatus(),
                order.getOrderDate(),
                order.getRequiredDate(),
                order.getShippedDate(),
                order.getStore() != null ? order.getStore().getId() : 0,
                order.getStore() != null ? order.getStore().getStoreName() : "Unknown Store",
                order.getStaff() != null ? order.getStaff().getId() : 0
        );
    }

    public static Order toEntity(OrderDTO orderDTO) {
        if (orderDTO == null) {
            return null;
        }

        Order order = new Order();
        order.setOrderStatus(orderDTO.getOrderStatus());
        order.setRequiredDate(orderDTO.getRequiredDate());
        order.setShippedDate(orderDTO.getShippedDate());

        return order;
    }

    public static List<OrderDTO> toDTOList(List<Order> orders) {
        return orders == null ? null : orders.stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }
}