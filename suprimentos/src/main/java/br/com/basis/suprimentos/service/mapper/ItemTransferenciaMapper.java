package br.com.basis.suprimentos.service.mapper;


import br.com.basis.suprimentos.domain.ItemTransferencia;
import br.com.basis.suprimentos.service.dto.ItemTransferenciaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {MaterialMapper.class})
public interface ItemTransferenciaMapper extends EntityMapper<ItemTransferenciaDTO, ItemTransferencia> {
    @Override
    @Mapping(source = "materialId", target = "material.id")
    ItemTransferencia toEntity(ItemTransferenciaDTO dto);

    @Override
    @Mapping(source = "material.id", target = "materialId")
    ItemTransferenciaDTO toDto(ItemTransferencia entity);

    Set<ItemTransferenciaDTO> toDto(Set<ItemTransferencia> entities);

    Set<ItemTransferencia> toEntity(Set<ItemTransferenciaDTO> dtos);

    default ItemTransferencia fromId(Long id) {
        if (id == null) {
            return null;
        }
        ItemTransferencia itemTransferencia = new ItemTransferencia();
        itemTransferencia.setId(id);
        return itemTransferencia;
    }
}
