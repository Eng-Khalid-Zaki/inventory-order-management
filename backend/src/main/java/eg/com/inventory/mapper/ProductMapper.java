package eg.com.inventory.mapper;

import eg.com.inventory.dto.ProductDTO;
import eg.com.inventory.entity.Brand;
import eg.com.inventory.entity.Category;
import eg.com.inventory.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        return product != null ?
                new ProductDTO(product.getId(), product.getProductName(),
                        product.getBrand().getId(), product.getCategory().getId(),
                        product.getModelYear(), product.getListPrice())
                : null;
    }

    public static Product toEntity(ProductDTO productDTO, Brand brand, Category category) {
        if (productDTO == null || brand == null || category == null) {
            return null;
        }

        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setModelYear(productDTO.getModelYear());
        product.setListPrice(productDTO.getListPrice());
        product.setBrand(brand);
        product.setCategory(category);

        return product;
    }

    public static List<ProductDTO> toDTOList(List<Product> products) {
        return products.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }
}