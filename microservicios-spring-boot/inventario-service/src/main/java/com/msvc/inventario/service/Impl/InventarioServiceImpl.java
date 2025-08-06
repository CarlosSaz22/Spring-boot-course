package com.msvc.inventario.service.Impl;

import com.msvc.inventario.dto.InventarioResponse;
import com.msvc.inventario.repository.InventarioRepository;

import com.msvc.inventario.service.InventarioServiceI;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InventarioServiceImpl implements InventarioServiceI {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventarioResponse> isInStock(List<String> codigoSku) {
        /*
        log.info("wait started");
        Thread.sleep(10000);
        log.info("wait end");

         */
        return inventarioRepository.findByCodigoSkuIn(codigoSku).stream()
                .map(inventario ->
                        InventarioResponse.builder()
                                .codigoSku(inventario.getCodigoSku())
                                .inStock(inventario.getCantidad() > 0).build()
                ).collect(Collectors.toList());
    }
}
