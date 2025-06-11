package eg.com.inventory.mapper;

import eg.com.inventory.dto.CategoryDTO;
import eg.com.inventory.dto.ProductDTO;
import eg.com.inventory.entity.Category;
import eg.com.inventory.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryDTO toDTO(Category category) {
        return category != null ?
                new CategoryDTO(category.getId(), category.getCategoryName(),
                        category.getProducts().stream().map(ProductMapper::toDTO).collect(Collectors.toList()))
                : null;
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