package eg.com.inventory.rest;

import eg.com.inventory.dto.StockDTO;
import eg.com.inventory.dto.StoreDTO;
import eg.com.inventory.entity.Store;
import eg.com.inventory.mapper.StoreMapper;
import eg.com.inventory.service.StoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreRestController {
    private StoreService storeService;

    public StoreRestController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping
    public List<StoreDTO> getAllStores() {
        return storeService.getAll();
    }

    @GetMapping("/{id}")
    public StoreDTO getStoreById(@PathVariable int id) {
        return storeService.getStoreById(id);
    }

    @PostMapping
    public StoreDTO addStore(@RequestBody StoreDTO storeDTO) {
        return storeService.addStore(storeDTO);
    }


    @PutMapping("/{id}")
    public StoreDTO updateStore(@PathVariable int id, @RequestBody StoreDTO storeDTO) {
        return storeService.updateStore(id, storeDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStore(@PathVariable int id) {
        storeService.deleteStore(id);
        return ResponseEntity.ok("Store deleted successfully");
    }

}
