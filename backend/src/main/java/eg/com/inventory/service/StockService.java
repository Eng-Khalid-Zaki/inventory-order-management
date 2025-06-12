package eg.com.inventory.service;

import eg.com.inventory.dto.StockDTO;
import eg.com.inventory.entity.Product;
import eg.com.inventory.entity.Stock;
import eg.com.inventory.entity.StockId;
import eg.com.inventory.entity.Store;
import eg.com.inventory.exception.EntityNotFoundException;
import eg.com.inventory.exception.InvalidDataFormatException;
import eg.com.inventory.mapper.StockMapper;
import eg.com.inventory.repository.ProductRepo;
import eg.com.inventory.repository.StockRepo;
import eg.com.inventory.repository.StoreRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StockService {
    private StockRepo stockRepo;
    private StoreRepo storeRepo;
    private ProductRepo productRepo;

    public StockService(StockRepo stockRepo, StoreRepo storeRepo, ProductRepo productRepo) {
        this.stockRepo = stockRepo;
        this.storeRepo = storeRepo;
        this.productRepo = productRepo;
    }

    @Transactional
    public List<StockDTO> getAllStocks() {
        List<Stock> stocks = stockRepo.findAll();
        return StockMapper.toDTOList(stocks);
    }

    @Transactional
    public StockDTO getStockById(StockId id) {
        Stock stock = stockRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("No stock found with this id: " + id));

        return StockMapper.toDTO(stock);
    }

    @Transactional
    public StockDTO addStock(StockDTO stockDTO) {
        if (stockDTO.getId() != null) {
            throw new InvalidDataFormatException("The id must be null");
        }

        if(stockRepo.existsById(new StockId(stockDTO.getStoreId(), stockDTO.getProductId()))) {
            throw new InvalidDataFormatException("This stock is already exists");
        }

        Store store = storeRepo.getReferenceById(stockDTO.getStoreId());
        Product product = productRepo.getReferenceById(stockDTO.getProductId());
        Stock stock = StockMapper.toEntity(stockDTO, store, product);
        stockRepo.saveAndFlush(stock);
        return StockMapper.toDTO(stock);
    }

    @Transactional
    public StockDTO updateStock(int storeId, int productId, StockDTO stockDTO) {
        if (stockDTO.getId() != null) {
            throw new InvalidDataFormatException("The id must be null");
        }

        Stock currentStock = stockRepo.findById(new StockId(storeId, productId))
                .orElseThrow(() -> new EntityNotFoundException("No stock found with this id"));
        currentStock.setQuantity(stockDTO.getQuantity());
        return StockMapper.toDTO(currentStock);
    }

    @Transactional
    public void deleteStock(int storeId, int productId) {
        Stock currentStock = stockRepo.findById(new StockId(storeId, productId))
                .orElseThrow(() -> new EntityNotFoundException("No stock found with this id"));

        stockRepo.delete(currentStock);
    }
}
