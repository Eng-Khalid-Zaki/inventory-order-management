package eg.com.inventory.controller;

import eg.com.inventory.dto.CategoryDTO;
import eg.com.inventory.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoriesRestController {

    private final CategoryService categoryService;

    public CategoriesRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.addCategory(categoryDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable int id, @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}