package eg.com.inventory.mapper;

import eg.com.inventory.dto.CustomerDTO;
import eg.com.inventory.dto.OrderDTO;
import eg.com.inventory.entity.Customer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {

    public static CustomerDTO toDTO(Customer customer) {
        if (customer == null) {
            return null;
        }

        List<OrderDTO> orderDTOs = customer.getOrders() != null
                ? customer.getOrders().stream().map(OrderMapper::toDTO).collect(Collectors.toList())
                : new ArrayList<>();

        return new CustomerDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getStreet(),
                customer.getCity(),
                customer.getState(),
                customer.getZipCode(),
                orderDTOs,
                customer.getCreatedAt() != null ? customer.getCreatedAt().toLocalDate() : null,
                customer.getUpdatedAt() != null ? customer.getUpdatedAt().toLocalDate() : null
        );
    }

    public static Customer toEntity(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setPhone(customerDTO.getPhone());
        customer.setEmail(customerDTO.getEmail());
        customer.setStreet(customerDTO.getStreet());
        customer.setCity(customerDTO.getCity());
        customer.setState(customerDTO.getState());
        customer.setZipCode(customerDTO.getZipCode());


        customer.setCreatedAt(customerDTO.getCreatedAt() != null ? customerDTO.getCreatedAt().atStartOfDay() : LocalDateTime.now());
        customer.setUpdatedAt(customerDTO.getUpdatedAt() != null ? customerDTO.getUpdatedAt().atStartOfDay() : null);

        return customer;
    }

    public static List<CustomerDTO> toDTOList(List<Customer> customers) {
        return customers != null
                ? customers.stream().map(CustomerMapper::toDTO).collect(Collectors.toList())
                : new ArrayList<>();
    }

    public static List<Customer> toEntityList(List<CustomerDTO> customerDTOs) {
        return customerDTOs != null
                ? customerDTOs.stream().map(CustomerMapper::toEntity).collect(Collectors.toList())
                : new ArrayList<>();
    }
}