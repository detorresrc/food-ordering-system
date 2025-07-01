package com.detorresrc.foodorderingsystem.system.outbox;

public interface OutboxScheduler {
    void processOutboxMessage();
}
