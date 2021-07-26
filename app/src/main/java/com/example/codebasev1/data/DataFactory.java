package com.example.codebasev1.data;

import java.util.ArrayList;
import java.util.List;

public final class DataFactory {

    public static List<Fatura> createFirstList() {

        final List<Fatura> faturas = new ArrayList<>();
        faturas.add(new Fatura( "Şubat 2018", 90, "Paid"));
        faturas.add(new Fatura("Haziran 2018", 100, "Paid"));
        faturas.add(new Fatura( "Mart 2020", 110, "Paid"));
        faturas.add(new Fatura( "Ağustos 2018", 28, "Paid"));
        faturas.add( new Fatura("Temmuz 2018", 125, "Not Paid"));
        faturas.add(new Fatura("Ocak 2018", 40, "Paid"));
        faturas.add(new Fatura("Ocak 2020", 45, "Paid"));
        faturas.add(new Fatura( "Aralık 2019", 78, "Not Paid"));
        faturas.add(new Fatura("Nisan 2020", 114, "Not Paid"));
        faturas.add(new Fatura( "Ağustos 2019", 28, "Paid"));

        return faturas;
    }

    public static List<Fatura> createSecondList() {

        final List<Fatura> faturas = new ArrayList<>();
        faturas.add(new Fatura( "Şubat 2018", 90, "Paid"));
        faturas.add(new Fatura("Haziran 2018", 100, "Paid"));
        faturas.add(new Fatura( "Mart 2020", 110, "Paid"));
        faturas.add(new Fatura( "Ağustos 2018", 28, "Paid"));
        faturas.add(new Fatura( "Aralık 2019", 78, "Not Paid"));
        faturas.add(new Fatura("Nisan 2020", 114, "Not Paid"));
        faturas.add(new Fatura( "Ağustos 2019", 28, "Paid"));

        return faturas;
    }
}