package com.bodoque007.springboot.mvcDemo.model;

import com.bodoque007.springboot.mvcDemo.validation.BeginsWith;
import jakarta.validation.constraints.*;

public class Customer {
    private String firstName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName;

    @Max(value= 100, message = "number must be no greater than 100")
    @Min(value= 0, message = "number must be at least 0")
    public Integer favoriteNumber;

    @BeginsWith
    public String couponCode;
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getFavoriteNumber() {
        return favoriteNumber;
    }

    public void setFavoriteNumber(Integer favoriteNumber) {
        this.favoriteNumber = favoriteNumber;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
}
