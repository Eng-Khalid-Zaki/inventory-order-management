package eg.com.inventory.service;

import eg.com.inventory.dto.CustomerDTO;
import eg.com.inventory.entity.Customer;
import eg.com.inventory.exception.EntityNotFoundException;
import eg.com.inventory.exception.InvalidDataFormatException;
import eg.com.inventory.mapper.CustomerMapper;
import eg.com.inventory.repository.CustomerRepo;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepo customerRepo;

    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Transactional
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepo.findAll();
        return CustomerMapper.toDTOList(customers);
    }

    @Transactional
    public CustomerDTO getCustomerById(int id) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("No customer found with this id: " + id));
        return CustomerMapper.toDTO(customer);
    }

    @Transactional
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        if(customerDTO.getId() != 0) {
            throw new InvalidDataFormatException("Id should be zero for create operation");
        }
        if(customerDTO.getFirstName().trim().isEmpty()) {
            throw new InvalidDataFormatException("First name cannot be empty");
        }
        if(customerDTO.getLastName().trim().isEmpty()) {
            throw new InvalidDataFormatException("Last name cannot be empty");
        }
        if(customerDTO.getEmail().trim().isEmpty()) {
            throw new InvalidDataFormatException("Email cannot be empty");
        }
        if(customerDTO.getStreet().trim().isEmpty()) {
            throw new InvalidDataFormatException("Street cannot be empty");
        }
        if(customerDTO.getCity().trim().isEmpty()) {
            throw new InvalidDataFormatException("City cannot be empty");
        }
        if(customerDTO.getState().trim().isEmpty()) {
            throw new InvalidDataFormatException("State cannot be empty");
        }
        Customer customer = CustomerMapper.toEntity(customerDTO);
        Customer savedCustomer = customerRepo.save(customer);
        return CustomerMapper.toDTO(savedCustomer);
    }

    @Transactional
    public CustomerDTO updateCustomer(int id, CustomerDTO customerDTO) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("No customer found with this id: " + id));
        if(customerDTO.getFirstName().trim().isEmpty()) {
            throw new InvalidDataFormatException("First name cannot be empty");
        }
        if(customerDTO.getLastName().trim().isEmpty()) {
            throw new InvalidDataFormatException("Last name cannot be empty");
        }
        if(customerDTO.getEmail().trim().isEmpty()) {
            throw new InvalidDataFormatException("Email cannot be empty");
        }
        if(customerDTO.getStreet().trim().isEmpty()) {
            throw new InvalidDataFormatException("Street cannot be empty");
        }
        if(customerDTO.getCity().trim().isEmpty()) {
            throw new InvalidDataFormatException("City cannot be empty");
        }
        if(customerDTO.getState().trim().isEmpty()) {
            throw new InvalidDataFormatException("State cannot be empty");
        }
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setPhone(customerDTO.getPhone());
        customer.setEmail(customerDTO.getEmail());
        customer.setStreet(customerDTO.getStreet());
        customer.setCity(customerDTO.getCity());
        customer.setState(customerDTO.getState());
        customer.setZipCode(customerDTO.getZipCode());
        Customer updatedCustomer = customerRepo.save(customer);
        return CustomerMapper.toDTO(updatedCustomer);
    }

    @Transactional
    public ResponseEntity<String> deleteCustomer(int id) {
        customerRepo.deleteById(id);
        return ResponseEntity.ok("Customer deleted successfully");
    }
}
