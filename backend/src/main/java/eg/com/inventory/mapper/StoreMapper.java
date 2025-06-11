package eg.com.inventory.mapper;

import eg.com.inventory.dto.StoreDTO;
import eg.com.inventory.entity.Store;
import eg.com.inventory.entity.Stock;
import eg.com.inventory.exception.EntityNotFoundException;
import eg.com.inventory.repository.StockRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StoreMapper {

    public static StoreDTO toDTO(Store store) {
        if (store == null) {
            return null;
        }

        return new StoreDTO(
                store.getId(),
                store.getStoreName(),
                store.getPhone(),
                store.getEmail(),
                store.getStreet(),
                store.getCity(),
                store.getZipCode(),
                Optional.ofNullable(store.getStocks())
                        .map(stocks -> stocks.stream()
                                .map(StockMapper::toDTO)
                                .collect(Collectors.toList()))
                        .orElse(List.of())
        );
    }

    public static Store toEntity(StoreDTO storeDTO, StockRepo stockRepository) {
        if (storeDTO == null) {
            return null;
        }

        Store store = new Store(
                storeDTO.getStoreName(),
                storeDTO.getPhone(),
                storeDTO.getEmail(),
                storeDTO.getStreet(),
                storeDTO.getCity(),
                storeDTO.getZipCode()
        );


        List<Stock> stockList = storeDTO.getStocks() != null
                ? storeDTO.getStocks().stream()
                .map(stockDTO -> stockRepository.findById(stockDTO.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Stock not found with ID: " + stockDTO.getId())))
                .collect(Collectors.toList())
                : List.of();

        store.setStocks(stockList);

        return store;
    }

    public static List<StoreDTO> toDTOList(List<Store> stores) {
        return Optional.ofNullable(stores)
                .map(list -> list.stream()
                        .map(StoreMapper::toDTO)
                        .collect(Collectors.toList()))
                .orElse(List.of());
    }

    public static List<Store> toEntityList(List<StoreDTO> storeDTOs, StockRepo stockRepository) {
        return Optional.ofNullable(storeDTOs)
                .map(list -> list.stream()
                        .map(storeDTO -> StoreMapper.toEntity(storeDTO, stockRepository))
                        .collect(Collectors.toList()))
                .orElse(List.of());
    }
}