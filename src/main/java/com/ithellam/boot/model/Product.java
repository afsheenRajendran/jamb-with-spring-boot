package com.ithellam.boot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
    @Id
    @GeneratedValue
    // private UUID productId;
    private Long productId;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String color;

    @Column
    private EProductCategory category;

    public Product() {

    }

    public Product(final Long productId,  final String name, final String color, final EProductCategory category) {
        this.productId = productId;
        this.name = name;
        this.color = color;
        this.category = category;
    }

    public static Builder builder() {
        return new Builder();
    }

//    public static Builder builder(final Product record) {
//        return (Builder) new Builder().fromRecord(record);
//    }

    public Long getProductId() {
        return this.productId;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public EProductCategory getCategory() {
        return category;
    }

//    @Override
//    protected ToStringHelper getToStringHelper() {
//        return super.getToStringHelper()
//        .add("containerId", containerId)
//        .add("name", name)
//        .add("color", color)
//        .add("category", category);
//    }

    public static class Builder { //extends AbstractPersistedObjectBuilder<Product, UUID> {
        private Long productId;
        private String name;
        private String color;
        private EProductCategory category;

        private Builder() {
        }

        public Product build() {
            // return new Product(getVersion(), getUniqueId(), containerId, name, color, category);
            return new Product(productId, name, color, category);
        }

        protected void onFromRecord(final Product record) {
            withName(record.getName())
            .withColor(record.getColor())
            .withCategory(record.getCategory());
        }

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withColor(final String color) {
            this.color = color;
            return this;
        }

        public Builder withProductId(final Long productId) {
            this.productId = productId;
            return this;
        }

        public Builder withCategory(final EProductCategory category) {
            this.category = category;
            return this;
        }
    }
}
