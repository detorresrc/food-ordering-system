package com.detorresrc.foodorderingsystem.event;

public interface DomainEvent<T> {
    void fire();
}
