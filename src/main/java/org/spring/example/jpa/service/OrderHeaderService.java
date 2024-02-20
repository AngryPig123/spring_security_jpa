package org.spring.example.jpa.service;

import org.spring.example.jpa.dto.OrderHeaderDto;
import org.spring.example.jpa.entity.OrderHeader;

public interface OrderHeaderService {

    OrderHeaderDto save(OrderHeaderDto orderHeaderDto);

}
