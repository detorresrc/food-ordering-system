package com.detorresrc.foodorderingsystem.event.publisher;

import com.detorresrc.foodorderingsystem.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {
    void publish(T domainEvent);
}
