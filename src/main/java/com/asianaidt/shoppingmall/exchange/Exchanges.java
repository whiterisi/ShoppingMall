package com.asianaidt.shoppingmall.exchange;

public enum Exchanges {
    KRW(1296), USD(1);

    private double value;

    private Exchanges(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    
}
