package org.spring.example.jpa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.spring.example.jpa.dto.OrderHeaderDto;
import org.spring.example.jpa.entity.OrderHeader;
import org.spring.example.jpa.repository.OrderHeaderRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderHeaderServiceImpl implements OrderHeaderService {

    private final OrderHeaderRepository orderHeaderRepository;


    @Override
    public OrderHeaderDto save(OrderHeaderDto orderHeaderDto) {

        OrderHeader save = orderHeaderRepository.save(orderHeaderDto.toEntity());

        log.info("save.toDto() = {}", save.toDto());

        return save.toDto();
    }

}
