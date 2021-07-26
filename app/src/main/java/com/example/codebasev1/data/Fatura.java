package com.example.codebasev1.data;

public class Fatura{

    private final String status;
    private final double amount;
    private String period;

    public Fatura(final String period, final double amount, final String status) {
        this.period = period;
        this.status = status;
        this.amount = amount;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(final String period) {
        this.period = period;
    }

    public String getStatus() {
        return status;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return getPeriod() + " " + period;
    }
}
