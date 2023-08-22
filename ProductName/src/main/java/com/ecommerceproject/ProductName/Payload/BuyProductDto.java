package com.ecommerceproject.ProductName.Payload;

public class BuyProductDto {
    private Long id;
    private String name;
    private double price;

    // Constructors, getters, setters, etc.
    // You can generate these using your IDE or manually implement them

    // Default constructor
    public BuyProductDto() {
    }

    // Parameterized constructor
    public BuyProductDto(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BuyProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}

