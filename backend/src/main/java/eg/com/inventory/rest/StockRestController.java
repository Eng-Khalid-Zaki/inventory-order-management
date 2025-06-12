package eg.com.inventory.rest;

import eg.com.inventory.dto.StockDTO;
import eg.com.inventory.entity.StockId;
import eg.com.inventory.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockRestController {
    private StockService stockService;

    public StockRestController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public List<StockDTO> getAllStocks() {
        return stockService.getAllStocks();
    }

    @GetMapping("/{storeId}/{productId}")
    public StockDTO getStockById(@PathVariable int storeId, @PathVariable int productId) {
        StockId stockId = new StockId(storeId, productId);
        return stockService.getStockById(stockId);
    }

    @PostMapping
    public StockDTO addStock(@RequestBody StockDTO stockDTO) {
        return stockService.addStock(stockDTO);
    }

    @PutMapping("/{storeId}/{productId}")
    public StockDTO updateStock(@PathVariable int storeId, @PathVariable int productId, @RequestBody StockDTO stockDTO) {
        return stockService.updateStock(storeId, productId, stockDTO);
    }

    @DeleteMapping("/{storeId}/{productId}")
    public ResponseEntity<String> deleteStock(@PathVariable int storeId, @PathVariable int productId) {
        stockService.deleteStock(storeId, productId);
        return ResponseEntity.ok("Stock was deleted successfully");
    }

}
