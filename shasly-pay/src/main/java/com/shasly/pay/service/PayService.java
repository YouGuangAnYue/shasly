package com.shasly.pay.service;

import java.math.BigDecimal;

public interface PayService {
    BigDecimal findOrderByOId(String oid);
}
