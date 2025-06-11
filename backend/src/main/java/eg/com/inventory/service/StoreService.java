package eg.com.inventory.service;

import eg.com.inventory.dto.StoreDTO;
import eg.com.inventory.entity.Store;
import eg.com.inventory.exception.InvalidDataFormatException;
import eg.com.inventory.exception.EntityNotFoundException;
import eg.com.inventory.mapper.StoreMapper;
import eg.com.inventory.repository.StockRepo;
import eg.com.inventory.repository.StoreRepo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreService {
    private StoreRepo storeRepo;
    private StockRepo stockRepo;

    public StoreService(StoreRepo storeRepo, StockRepo stockRepo) {
        this.storeRepo = storeRepo;
        this.stockRepo = stockRepo;
    }

    @Transactional
    public List<StoreDTO> getAll() {
        List<Store> allStores = storeRepo.findAll();
        return allStores.stream()
                .map(StoreMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Transactional
    public StoreDTO getStoreById(int id) {
        Store currentStore = storeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No store found with this id: " + id));
        return StoreMapper.toDTO(currentStore);
    }

    @Transactional
    public StoreDTO addStore(StoreDTO storeDTO) {
        Store store = StoreMapper.toEntity(storeDTO, stockRepo);
        if(store.getId() != 0) {
            throw new InvalidDataFormatException("ID must not be included in the request body");
        }
        Store savedStore = storeRepo.saveAndFlush(store);
        return StoreMapper.toDTO(savedStore);
    }

    @Transactional
    public StoreDTO updateStore(int id, StoreDTO storeDTO) {
        Store currentStore = storeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No store found with this id: " + id));

        currentStore.setStoreName(storeDTO.getStoreName());
        currentStore.setPhone(storeDTO.getPhone());
        currentStore.setEmail(storeDTO.getEmail());
        currentStore.setStreet(storeDTO.getStreet());
        currentStore.setCity(storeDTO.getCity());
        currentStore.setZipCode(storeDTO.getZipCode());

        Store updatedStore = storeRepo.save(currentStore);
        return StoreMapper.toDTO(updatedStore);
    }

    @Transactional
    public void deleteStore(int id) {
        Store store = storeRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No store found with ID: " + id));


        stockRepo.deleteAll(store.getStocks());


        storeRepo.delete(store);
    }

}
