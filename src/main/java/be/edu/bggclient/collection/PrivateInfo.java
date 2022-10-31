package be.edu.bggclient.collection;

import java.time.LocalDate;

public final class PrivateInfo {
    private final String pricePaidCurrency;
    private final String pricePaid;
    private final String currentValueCurrency;
    private final String currentValue;
    private final int quantity;
    private final LocalDate acquisitionDate;
    private final String acquiredFrom;
    private final LocalDate inventoryDate;
    private final String inventoryLocation;
    private final String comment;

    private PrivateInfo(Builder builder) {
        this.pricePaidCurrency = builder.pricePaidCurrency;
        this.pricePaid = builder.pricePaid;
        this.currentValueCurrency = builder.currentValueCurrency;
        this.currentValue = builder.currentValue;
        this.quantity = builder.quantity;
        this.acquisitionDate = builder.acquisitionDate;
        this.acquiredFrom = builder.acquiredFrom;
        this.inventoryDate = builder.inventoryDate;
        this.inventoryLocation = builder.inventoryLocation;
        this.comment = builder.comment;
    }

    public String getPricePaidCurrency() {
        return pricePaidCurrency;
    }

    public String getPricePaid() {
        return pricePaid;
    }

    public String getCurrentValueCurrency() {
        return currentValueCurrency;
    }

    public String getCurrentValue() {
        return currentValue;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getAcquisitionDate() {
        return acquisitionDate;
    }

    public String getAcquiredFrom() {
        return acquiredFrom;
    }

    public LocalDate getInventoryDate() {
        return inventoryDate;
    }

    public String getInventoryLocation() {
        return inventoryLocation;
    }

    public String getComment() {
        return comment;
    }

    public static final class Builder {
        private String pricePaidCurrency;
        private String pricePaid;
        private String currentValueCurrency;
        private String currentValue;
        private int quantity;
        private LocalDate acquisitionDate;
        private String acquiredFrom;
        private LocalDate inventoryDate;
        private String inventoryLocation;
        private String comment;

        public Builder withPricePaidCurrency(String pricePaidCurrency) {
            this.pricePaidCurrency = pricePaidCurrency;
            return this;
        }

        public Builder withPricePaid(String pricePaid) {
            this.pricePaid = pricePaid;
            return this;
        }

        public Builder withCurrentValueCurrency(String currentValueCurrency) {
            this.currentValueCurrency = currentValueCurrency;
            return this;
        }

        public Builder withCurrentValue(String currentValue) {
            this.currentValue = currentValue;
            return this;
        }

        public Builder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder withAcquisitionDate(LocalDate acquisitionDate) {
            this.acquisitionDate = acquisitionDate;
            return this;
        }

        public Builder withAcquiredFrom(String acquiredFrom) {
            this.acquiredFrom = acquiredFrom;
            return this;
        }

        public Builder withInventoryDate(LocalDate inventoryDate) {
            this.inventoryDate = inventoryDate;
            return this;
        }

        public Builder withInventoryLocation(String inventoryLocation) {
            this.inventoryLocation = inventoryLocation;
            return this;
        }

        public Builder withComment(String comment) {
            this.comment = comment;
            return this;
        }

        public PrivateInfo build() {
            return new PrivateInfo(this);
        }
    }
}
