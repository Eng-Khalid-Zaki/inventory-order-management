package eg.com.inventory.rest;

import eg.com.inventory.dto.ProductDTO;
import eg.com.inventory.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable int id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.addProduct(productDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable int id, @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.updateProduct(id, productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@PathVariable int id) {
        return ResponseEntity.ok(productService.getProductsByCategory(id));
    }

    @GetMapping("/brand/{id}")
    public ResponseEntity<List<ProductDTO>> getProductsByBrand(@PathVariable int id) {
        return ResponseEntity.ok(productService.getProductsByBrand(id));
    }
}