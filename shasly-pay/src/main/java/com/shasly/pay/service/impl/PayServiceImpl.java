package com.shasly.pay.service.impl;

import com.shasly.pay.service.PayService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PayServiceImpl implements PayService {

    @Override
    public BigDecimal findOrderByOId(String oid) {
        return null;
    }
}
