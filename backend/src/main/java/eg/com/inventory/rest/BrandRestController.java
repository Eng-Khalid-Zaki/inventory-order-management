package eg.com.inventory.rest;

import eg.com.inventory.dto.BrandDTO;
import eg.com.inventory.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandRestController {

    private BrandService brandService;

    public BrandRestController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public List<BrandDTO> getAll() {
        return brandService.getAll();
    }

    @PostMapping
    public BrandDTO addBrand(@RequestBody BrandDTO brandDTO) {
        return brandService.addBrand(brandDTO);
    }

    @PutMapping("/{id}")
    public BrandDTO updateBrand(@PathVariable int id, @RequestBody BrandDTO brandDTO) {
        return brandService.updateBrand(id, brandDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        brandService.deleteBrand(id);
        return ResponseEntity.ok("Brand deleted successfully");
    }

}
