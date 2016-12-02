package com.sarah.shopping_cart.models;

public abstract class Item {

    private final long id;
    private final String name;
    private final int price;

    Item(long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Item: ID= " + id + ", name= " + name;
    }

    @SuppressWarnings("unchecked")
    static abstract class Builder<K extends Builder, V extends Item> {

        protected long id;
        protected String name;
        protected int price;

        public K setId(long id) {
            this.id = id;
            return (K) this;
        }

        public K setName(String name) {
            this.name = name;
            return (K) this;
        }

        public K setPrice(int price) {
            this.price = price;
            return (K) this;
        }

        public abstract V build();
    }
}
