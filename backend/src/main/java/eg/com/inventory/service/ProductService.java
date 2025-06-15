package eg.com.inventory.service;

import eg.com.inventory.dto.ProductDTO;
import eg.com.inventory.entity.Brand;
import eg.com.inventory.entity.Category;
import eg.com.inventory.entity.Product;
import eg.com.inventory.exception.InvalidDataFormatException;
import eg.com.inventory.mapper.ProductMapper;
import eg.com.inventory.repository.BrandRepo;
import eg.com.inventory.repository.CategoryRepo;
import eg.com.inventory.repository.ProductRepo;
import eg.com.inventory.repository.StockRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    private final BrandRepo brandRepo;
    private final CategoryRepo categoryRepo;
    private final StockRepo stockRepo;

    public ProductService(ProductRepo productRepo, BrandRepo brandRepo, CategoryRepo categoryRepo, StockRepo stockRepo) {
        this.productRepo = productRepo;
        this.brandRepo = brandRepo;
        this.categoryRepo = categoryRepo;
        this.stockRepo = stockRepo;
    }

    public List<ProductDTO> getAllProducts() {
        return ProductMapper.toDTOList(productRepo.findAll());
    }

    public ProductDTO getProductById(int id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No product found with ID: " + id));
        return ProductMapper.toDTO(product);
    }

    @Transactional
    public ProductDTO addProduct(ProductDTO productDTO) {
        Brand brand = brandRepo.findById(productDTO.getBrandId())
                .orElseThrow(() -> new EntityNotFoundException("Brand not found with ID: " + productDTO.getBrandId()));

        Category category = categoryRepo.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with ID: " + productDTO.getCategoryId()));

        if(productDTO.getProductName().trim().isEmpty()) {
            throw new InvalidDataFormatException("The product name should not be empty");
        }

        if (productDTO.getListPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidDataFormatException("Product list price should be greater than or equal to 0");
        }

        Product product = ProductMapper.toEntity(productDTO, brand, category);


        Product savedProduct = productRepo.save(product);

        return ProductMapper.toDTO(savedProduct);
    }

    @Transactional
    public ProductDTO updateProduct(int id, ProductDTO productDTO) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No product found with ID: " + id));


        if(productDTO.getProductName().trim().isEmpty()) {
            throw new InvalidDataFormatException("The product name should not be empty");
        }
        product.setProductName(productDTO.getProductName());

        product.setModelYear(productDTO.getModelYear());

        if (productDTO.getListPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidDataFormatException("Product list price should be greater than or equal to 0");
        }
        product.setListPrice(productDTO.getListPrice());

        Brand brand = brandRepo.findById(productDTO.getBrandId())
                .orElseThrow(() -> new EntityNotFoundException("Brand not found with ID: " + productDTO.getBrandId()));
        product.setBrand(brand);

        Category category = categoryRepo.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found with ID: " + productDTO.getCategoryId()));
        product.setCategory(category);

        Product updatedProduct = productRepo.save(product);
        return ProductMapper.toDTO(updatedProduct);
    }

    @Transactional
    public void deleteProduct(int id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No product found with ID: " + id));


        stockRepo.deleteAll(product.getStocks());


        productRepo.delete(product);
    }

    @Transactional
    public List<ProductDTO> getProductsByCategory(int categoryId) {
        return ProductMapper.toDTOList(productRepo.findByCategoryId(categoryId));
    }

    @Transactional
    public List<ProductDTO> getProductsByBrand(int brandId) {
        return ProductMapper.toDTOList(productRepo.findByBrandId(brandId));
    }
}