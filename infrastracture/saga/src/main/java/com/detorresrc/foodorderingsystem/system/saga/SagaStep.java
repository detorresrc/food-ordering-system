package com.detorresrc.foodorderingsystem.system.saga;

public interface SagaStep<T> {
    void process(T data);

    void rollback(T data);
}
