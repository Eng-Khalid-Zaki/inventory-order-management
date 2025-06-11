package eg.com.inventory.service;

import eg.com.inventory.dto.CategoryDTO;
import eg.com.inventory.entity.Category;
import eg.com.inventory.mapper.CategoryMapper;
import eg.com.inventory.repository.CategoryRepo;
import eg.com.inventory.repository.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;
    private ProductRepo productRepo;

    public CategoryService(CategoryRepo categoryRepo, ProductRepo productRepo) {
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
    }

    public List<CategoryDTO> getAllCategories() {
        return CategoryMapper.toDTOList(categoryRepo.findAll());
    }

    public CategoryDTO getCategoryById(int id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No category found with ID: " + id));
        return CategoryMapper.toDTO(category);
    }

    @Transactional
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.toEntity(categoryDTO);
        Category savedCategory = categoryRepo.save(category);
        return CategoryMapper.toDTO(savedCategory);
    }

    @Transactional
    public CategoryDTO updateCategory(int id, CategoryDTO categoryDTO) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No category found with ID: " + id));

        category.setCategoryName(categoryDTO.getCategoryName());

        Category updatedCategory = categoryRepo.save(category);
        return CategoryMapper.toDTO(updatedCategory);
    }

    @Transactional
    public void deleteCategory(int id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No category found with ID: " + id));

        productRepo.deleteAll(category.getProducts());

        categoryRepo.delete(category);
    }
}