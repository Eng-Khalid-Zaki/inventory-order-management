package eg.com.inventory.mapper;

//import eg.com.inventory.dto.BrandDTO;
import eg.com.inventory.dto.CategoryDTO;
import eg.com.inventory.dto.ProductDTO;
import eg.com.inventory.entity.Category;
//import eg.com.inventory.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryDTO toDTO(Category category) {
        if(category == null) {
            return null;
        }

        List<ProductDTO> productDTOs = category.getProducts() != null
                ? category.getProducts().stream().map(ProductMapper::toDTO).collect(Collectors.toList())
                : new ArrayList<>();

        return new CategoryDTO(category.getId(), category.getCategoryName(), productDTOs);
    }

    public static Category toEntity(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        return category;
    }

    public static List<CategoryDTO> toDTOList(List<Category> categories) {
        return categories.stream().map(CategoryMapper::toDTO).collect(Collectors.toList());
    }
}