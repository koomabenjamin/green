package com.shapeworks.mivule.Entities;

/**
 * Created by KoomaBenjamin on 17/10/2017.
 */

public class Products {
    private String name;
    private String icon;
    private String availableQuantity;
    private String price;

    public Products() {
    }

    public Products(String name, String icon, String availableQuantity, String price) {
        this.name = name;
        this.icon = icon;
        this.availableQuantity = availableQuantity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(String availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
