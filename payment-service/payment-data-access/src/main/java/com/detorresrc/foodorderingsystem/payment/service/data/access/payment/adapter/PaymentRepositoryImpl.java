package com.detorresrc.foodorderingsystem.payment.service.data.access.payment.adapter;

import com.detorresrc.foodorderingsystem.payment.service.data.access.payment.mapper.PaymentDataAccessMapper;
import com.detorresrc.foodorderingsystem.payment.service.data.access.payment.repository.PaymentJpaRepository;
import com.detorresrc.foodorderingsystem.payment.service.domain.entity.Payment;
import com.detorresrc.foodorderingsystem.payment.service.domain.ports.output.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {
    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentDataAccessMapper paymentDataAccessMapper;

    @Override
    public Payment save(Payment payment) {
        return paymentDataAccessMapper
            .paymentEntityToPayment(paymentJpaRepository
                .save(paymentDataAccessMapper.paymentToPaymentEntity(payment)));
    }

    @Override
    public Optional<Payment> findByOrderId(UUID orderId) {
        return paymentJpaRepository.findByOrderId(orderId)
            .map(paymentDataAccessMapper::paymentEntityToPayment);
    }
}
