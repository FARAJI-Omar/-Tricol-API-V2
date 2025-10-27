package com.tricolv2.tricolv2.mapper;

import com.tricolv2.tricolv2.dto.SupplierDTO;
import com.tricolv2.tricolv2.entity.Supplier;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {
    SupplierDTO toDTO(Supplier entity);
    Supplier toEntity(SupplierDTO dto);
}
