package com.example.toovendendo;

public class ItemPedido {
    private String item;
    private int quantidade;
    private double vlUnit;

    public ItemPedido() {
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getVlUnit() {
        return vlUnit;
    }

    public void setVlUnit(double vlUnit) {
        this.vlUnit = vlUnit;
    }
}
