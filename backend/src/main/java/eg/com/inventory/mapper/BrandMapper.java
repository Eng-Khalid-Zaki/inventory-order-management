package eg.com.inventory.mapper;

import eg.com.inventory.dto.BrandDTO;
import eg.com.inventory.dto.ProductDTO;
import eg.com.inventory.entity.Brand;
import eg.com.inventory.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class BrandMapper {

    public static BrandDTO toDTO(Brand brand) {
        return brand != null ?
                new BrandDTO(brand.getId(), brand.getBrandName(),
                        brand.getProducts().stream().map(ProductMapper::toDTO).collect(Collectors.toList()))
                : null;
    }

    public static Brand toEntity(BrandDTO brandDTO) {
        if (brandDTO == null) {
            return null;
        }
        Brand brand = new Brand();
        brand.setBrandName(brandDTO.getBrandName());
        return brand;
    }

    public static List<BrandDTO> toDTOList(List<Brand> brands) {
        return brands.stream().map(BrandMapper::toDTO).collect(Collectors.toList());
    }
}