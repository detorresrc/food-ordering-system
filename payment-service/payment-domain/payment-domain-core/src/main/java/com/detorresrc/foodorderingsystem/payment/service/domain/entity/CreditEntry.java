package com.detorresrc.foodorderingsystem.payment.service.domain.entity;

import com.detorresrc.foodorderingsystem.entity.BaseEntity;
import com.detorresrc.foodorderingsystem.payment.service.domain.valueobject.CreditEntryId;
import com.detorresrc.foodorderingsystem.valueobject.CustomerId;
import com.detorresrc.foodorderingsystem.valueobject.Money;
import lombok.Getter;

@Getter
public class CreditEntry extends BaseEntity<CreditEntryId> {
    private final CustomerId customerId;
    private Money totalCreditAmount;

    public void addCreditAmount(Money creditAmount) {
        this.totalCreditAmount = this.totalCreditAmount.add(creditAmount);
    }

    public void subtractCreditAmount(Money creditAmount) {
        this.totalCreditAmount = this.totalCreditAmount.subtract(creditAmount);
    }

    private CreditEntry(Builder builder) {
        setId(builder.creditEntryId);
        customerId = builder.customerId;
        totalCreditAmount = builder.totalCreditAmount;
    }

    public static final class Builder {
        private CreditEntryId creditEntryId;
        private CustomerId customerId;
        private Money totalCreditAmount;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(CreditEntryId val) {
            creditEntryId = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder totalCreditAmount(Money val) {
            totalCreditAmount = val;
            return this;
        }

        public CreditEntry build() {
            return new CreditEntry(this);
        }
    }
}
