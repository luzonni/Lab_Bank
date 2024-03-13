package com.zonni.model;

import java.util.ArrayList;
import java.util.List;

public class Conta {

    private static int AMOUNT = 0;
    private int id;
    protected Client titular;
    protected int numero;
    protected String agencia;
    protected double saldo;

    private List<Double> historico;

    public Conta(Client titular, String agencia, int numero) {
        this.titular = titular;
        this.agencia = agencia;
        this.numero = numero;
        this.saldo = 0.0;
        this.historico = new ArrayList<Double>();
        this.id = Conta.AMOUNT;
        Conta.AMOUNT++;
    }

    public int getId() {
        return this.id;
    }

    public Client getTitular() {
        return titular;
    }

    public int getNumero() {
        return numero;
    }

    public String getAgencia() {
        return agencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        this.saldo += valor;
        this.historico.add(valor);
    }

    public void sacar(double valor) {
        if(this.saldo >= valor) {
            this.saldo -= valor;
            this.historico.add(valor);
        }else
            throw new RuntimeException("Saldo insuficiente!");
    }


    public double calculaRendimento() {
        return this.saldo * 0.1;
    }
    public double calculaMovimentacao() {
        if(historico.isEmpty())
            return 0.0;
        double amount = 0;
        for(int i = 0; i < historico.size(); i++) {
           amount+= historico.get(i);
        }
        return amount;
    }

    @Override
    public String toString() {
        return "Conta com tipo:" + this.getClass().getSimpleName() +" ID: " + this.id + " e Cliente: {\n    " + this.titular.toString() + "\n} \ncom agencia: " +
                this.agencia + " e numero: " + this.numero;
    }
}
