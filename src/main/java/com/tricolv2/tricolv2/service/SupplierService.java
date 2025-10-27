package com.tricolv2.tricolv2.service;

import com.tricolv2.tricolv2.dto.SupplierDTO;
import com.tricolv2.tricolv2.entity.Supplier;
import com.tricolv2.tricolv2.mapper.SupplierMapper;
import com.tricolv2.tricolv2.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private SupplierMapper supplierMapper;

    // Create a new supplier
    public SupplierDTO createSupplier(SupplierDTO supplierDTO) {
        // Map DTO to entity
        Supplier supplier = supplierMapper.toEntity(supplierDTO);
        // Save entity
        Supplier savedSupplier = supplierRepository.save(supplier);
        // Map back to DTO and return
        return supplierMapper.toDTO(savedSupplier);
    }

    // Get all suppliers
    public List<SupplierDTO> getSuppliers() {
        return supplierRepository.findAll().stream()
                .map(supplier -> supplierMapper.toDTO(supplier))
                .collect(Collectors.toList());
    }

    // Get supplier by ID
    public Optional<SupplierDTO> getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .map(supplier -> supplierMapper.toDTO(supplier));
    }

    // update a supplier
    public SupplierDTO updateSupplier(Long id, SupplierDTO supplierDTO) {
        Supplier existingSupplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
        // Update fields
        existingSupplier.setSociety(supplierDTO.getSociety());
        existingSupplier.setAddress(supplierDTO.getAddress());
        existingSupplier.setSocialReason(supplierDTO.getSocialReason());
        existingSupplier.setContactAgent(supplierDTO.getContactAgent());
        existingSupplier.setEmail(supplierDTO.getEmail());
        existingSupplier.setPhone(supplierDTO.getPhone());
        existingSupplier.setCity(supplierDTO.getCity());
        existingSupplier.setIce(supplierDTO.getIce());
        // Save updated entity
        Supplier updatedSupplier = supplierRepository.save(existingSupplier);
        return supplierMapper.toDTO(updatedSupplier);
    }

    // delete a supplier
    public void deleteSupplier(Long id) {
        Supplier existingSupplier = supplierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
        supplierRepository.delete(existingSupplier);
    }

    // search suppliers by society or contact agent
    public List<SupplierDTO> searchSuppliers(String query) {
        return supplierRepository
            .findBySocietyContainingIgnoreCaseOrContactAgentContainingIgnoreCase(query, query)
            .stream()
            .map(supplierMapper::toDTO)
            .collect(Collectors.toList());
    }
}
