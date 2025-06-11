package eg.com.inventory.mapper;

import eg.com.inventory.dto.StockDTO;
import eg.com.inventory.entity.Product;
import eg.com.inventory.entity.Stock;
import eg.com.inventory.entity.StockId;
import eg.com.inventory.entity.Store;
import eg.com.inventory.exception.EntityNotFoundException;
import eg.com.inventory.repository.ProductRepo;
import eg.com.inventory.repository.StoreRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StockMapper {

    public static StockDTO toDTO(Stock stock) {
        return Optional.ofNullable(stock)
                .map(s -> new StockDTO(
                        s.getId(),
                        s.getId().getStoreId(),
                        s.getId().getProductId(),
                        s.getQuantity()
                )).orElse(null);
    }

    public static Stock toEntity(StockDTO stockDTO, Store store, Product product) {
        if (stockDTO == null || store == null || product == null) {
            return null;
        }

        Stock stock = new Stock();
        stock.setQuantity(stockDTO.getQuantity());
        stock.setId(new StockId(store.getId(), product.getId())); // Use existing IDs from store & product
        stock.setStore(store);
        stock.setProduct(product);

        return stock;
    }

    public static List<StockDTO> toDTOList(List<Stock> stocks) {
        return Optional.ofNullable(stocks)
                .map(list -> list.stream()
                        .map(StockMapper::toDTO)
                        .collect(Collectors.toList()))
                .orElse(List.of());
    }

    public static List<Stock> toEntityList(List<StockDTO> stockDTOs, StoreRepo storeRepository, ProductRepo productRepository) {
        return Optional.ofNullable(stockDTOs)
                .map(list -> list.stream()
                        .map(stockDTO -> {
                            Store store = storeRepository.findById(stockDTO.getStoreId())
                                    .orElseThrow(() -> new EntityNotFoundException("Store not found with ID: " + stockDTO.getStoreId()));

                            Product product = productRepository.findById(stockDTO.getProductId())
                                    .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + stockDTO.getProductId()));

                            return StockMapper.toEntity(stockDTO, store, product);
                        })
                        .collect(Collectors.toList()))
                .orElse(List.of());
    }
}
