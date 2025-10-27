package com.tricolv2.tricolv2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.tricolv2.tricolv2.dto.SupplierDTO;
import com.tricolv2.tricolv2.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/api/v2/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping("/create")
    public ResponseEntity<SupplierDTO> createSupplier(@RequestBody SupplierDTO supplierDTO) {
        SupplierDTO supplier = supplierService.createSupplier(supplierDTO);
        return ResponseEntity.ok(supplier);
    }

    @GetMapping
    public  ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        List<SupplierDTO> suppliers = supplierService.getSuppliers();
        return  ResponseEntity.ok(suppliers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable Long id) {
        SupplierDTO supplierDTO = supplierService.getSupplierById(id).orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
        return ResponseEntity.ok(supplierDTO);

    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> updateSupplier(@PathVariable Long id, @RequestBody SupplierDTO supplierDTO) {
        SupplierDTO updatedSupplier = supplierService.updateSupplier(id, supplierDTO);
        return ResponseEntity.ok(updatedSupplier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }

    // search suppliers by query (society or contact agent)
    @GetMapping("/search")
    public ResponseEntity<List<SupplierDTO>> searchSuppliers(@RequestParam("q") String query) {
        List<SupplierDTO> suppliers = supplierService.searchSuppliers(query);
        return ResponseEntity.ok(suppliers);
    }
}
