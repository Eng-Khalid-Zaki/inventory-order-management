package eg.com.inventory.service;

import eg.com.inventory.dto.BrandDTO;
import eg.com.inventory.entity.Brand;
import eg.com.inventory.exception.EntityNotFoundException;
import eg.com.inventory.exception.InvalidDataFormatException;
import eg.com.inventory.mapper.BrandMapper;
import eg.com.inventory.repository.BrandRepo;
import eg.com.inventory.repository.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService {
    private BrandRepo brandRepo;
    private ProductRepo productRepo;

    public BrandService(BrandRepo brandRepo, ProductRepo productRepo) {
        this.brandRepo = brandRepo;
        this.productRepo = productRepo;
    }

    @Transactional
    public List<BrandDTO> getAll() {
        List<Brand> allBrands = brandRepo.findAll();
       return allBrands.stream().map(BrandMapper::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public BrandDTO addBrand(BrandDTO brandDTO) {
        if(brandDTO.getBrandName().trim().isEmpty()) {
            throw new InvalidDataFormatException("The brand nane should not be empty");
        }
        Brand brand = BrandMapper.toEntity(brandDTO);

        if(brand.getId() != 0) {
            throw new InvalidDataFormatException("ID must not be included in the request body");
        }
        Brand savedBrand = brandRepo.save(brand);

        return BrandMapper.toDTO(savedBrand);
    }

    @Transactional
    public BrandDTO updateBrand(int id, BrandDTO brandDTO) {
        Brand currentBrand = brandRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No brand found with this id: " + id));

        if(brandDTO.getBrandName().trim().isEmpty()) {
            throw new InvalidDataFormatException("Brand name should not be empty");
        }
        currentBrand.setBrandName(brandDTO.getBrandName());

        Brand updatedBrand = brandRepo.save(currentBrand);

        return BrandMapper.toDTO(updatedBrand);
    }

    @Transactional
    public void deleteBrand(int id) {
        Brand brand = brandRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No brand found with ID: " + id));

        productRepo.deleteAll(brand.getProducts());

        brandRepo.delete(brand);
    }

    @Transactional
    public Brand findByBrandName(String name) {
        return brandRepo.findByBrandName(name);
    }
}
