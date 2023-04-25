package com.example.toovendendo;

import static java.lang.Math.round;

import android.widget.EditText;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Pedido {
    private int  nrPedido;
    private String nome;
    private String cpf;
    private ArrayList<ItemPedido> itensPedido = new ArrayList<>();
    private double vlTotal;
    private int qtdItens;
    private int qtdParcelas;
    private double vlParcelas;
    private double vlPedido;

    public Pedido() {
    }

    public int getNrPedido() {
        return nrPedido;
    }

    public void setNrPedido(int nrPedido) {
        this.nrPedido = nrPedido;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ArrayList<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(ArrayList<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public double getVlTotal() {
        return vlTotal;
    }

    public void setVlTotal(double vlTotal) {
        this.vlTotal = vlTotal;
    }

    public int getQtdItens() {
        return qtdItens;
    }

    public void setQtdItens(int qtdItens) {
        this.qtdItens = qtdItens;
    }

    public int getQtdParcelas() {
        return qtdParcelas;
    }

    public void setQtdParcelas(int qtdParcelas) {
        this.qtdParcelas = qtdParcelas;
    }

    public double getVlParcelas() {
        return vlParcelas;
    }

    public void setVlParcelas(double vlParcelas) {
        this.vlParcelas = vlParcelas;
    }

    public double getVlPedido() {
        return vlPedido;
    }

    public void setVlPedido(double vlPedido) {
        this.vlPedido = vlPedido;
    }

    public void addItem(String descItem, int quantidade, double vlUnit){
        ItemPedido item = new ItemPedido();

        item.setItem(descItem);
        item.setQuantidade(quantidade);
        item.setVlUnit(vlUnit);

        getItensPedido().add(item);

        calculaQtdItens();
        calculaVlPedido();
    }

    public String listaItensPedido(){
        String resultado = "";

        for (ItemPedido item: getItensPedido()) {
            resultado += "Item: " + item.getItem()
                    + " | Qtd: " + String.valueOf(item.getQuantidade())
                    + " | Valor Unit.: R$" + String.valueOf(item.getVlUnit())
                    + "\n";
        }
        return resultado;
    }

    public Pedido pesquisaPedido(ArrayList<Pedido> pedidos, int nrPedido){

        for (Pedido pedido:pedidos) {
            if (pedido.getNrPedido() == nrPedido){
                return pedido;
            }
        }
        return null;
    }

    public void calculaVlPedido(){
        double resultado = 0;

        for (ItemPedido item: getItensPedido()) {
            resultado += (item.getQuantidade() * item.getVlUnit());
        }

        setVlTotal(resultado);
    }

    public void calculaQtdItens(){
        int resultado = 0;

        for (ItemPedido item: getItensPedido()) {
            resultado += item.getQuantidade();
        }

        setQtdItens(resultado);
    }

    public void geraNrPedido(ArrayList<Pedido> pedidos){
        int maxNrPedido = 0;

        for (Pedido pedido:pedidos) {
            if (maxNrPedido<pedido.getNrPedido()){
                maxNrPedido = pedido.getNrPedido();
            }
        }

        maxNrPedido += 1;
        setNrPedido(maxNrPedido);
    }

    public void calculaVlAVista(){
        setVlPedido(getVlTotal()*.95);
    }

    public void calculaVlAPrazo(){
        setVlPedido(getVlTotal()*1.05);
    }

    public void calculaVlParcela(){
        setVlParcelas(round(getVlPedido()/getQtdParcelas()));
    }
}
