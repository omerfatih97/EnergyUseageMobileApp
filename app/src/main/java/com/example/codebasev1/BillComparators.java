package com.example.codebasev1;

import com.example.codebasev1.data.Fatura;
import java.util.Comparator;

public final class BillComparators {

    private BillComparators() {
        //no instance
    }

    public static Comparator<Fatura> getDurumComparator() {
        return new BillStatusComparator();
    }

    public static Comparator<Fatura> getDonemComparator() {
        return new BillPeriodComparator();
    }

    public static Comparator<Fatura> getTutarComparator() {
        return new BillAmountComparator();
    }

    private static class BillStatusComparator implements Comparator<Fatura> {

        @Override
        public int compare(final Fatura fatura1, final Fatura fatura2) {
            return fatura1.getStatus().compareTo(fatura2.getStatus());
        }
    }

    private static class BillPeriodComparator implements Comparator<Fatura> {

        @Override
        public int compare(final Fatura fatura1, final Fatura fatura2) {

            int ay=1,ay1=1,yil1,yil2;
            yil1=Integer.valueOf(fatura1.getPeriod().substring(fatura1.getPeriod().length()-4));
            yil2=Integer.valueOf(fatura2.getPeriod().substring(fatura2.getPeriod().length()-4));
//months
            switch (fatura1.getPeriod().substring(0,3)){
                case "Oca": ay=1; break;
                case "Şub": ay=2; break;
                case "Mar": ay=3; break;
                case "Nis": ay=4; break;
                case "May": ay=5; break;
                case "Haz": ay=6; break;
                case "Tem": ay=7; break;
                case "Ağu": ay=8; break;
                case "Eyl": ay=9; break;
                case "Eki": ay=10; break;
                case "Kas": ay=11; break;
                case "Ara": ay=12; break;
            }
            switch (fatura2.getPeriod().substring(0,3)){
                case "Oca": ay1=1; break;
                case "Şub": ay1=2; break;
                case "Mar": ay1=3; break;
                case "Nis": ay1=4; break;
                case "May": ay1=5; break;
                case "Haz": ay1=6; break;
                case "Tem": ay1=7; break;
                case "Ağu": ay1=8; break;
                case "Eyl": ay1=9; break;
                case "Eki": ay1=10; break;
                case "Kas": ay1=11; break;
                case "Ara": ay1=12; break;
            }

            if (yil1 < yil2) return -1;
            else if (yil1 > yil2) return 1;
            else {
                if (ay < ay1) return -1;
                else if (ay > ay1) return 1;
                return 0;}

        }
    }

    private static class BillAmountComparator implements Comparator<Fatura> {

        @Override
        public int compare(final Fatura fatura1, final Fatura fatura2) {
            if (fatura1.getAmount() < fatura2.getAmount()) return -1;
            if (fatura1.getAmount() > fatura2.getAmount()) return 1;
            return 0;
        }
    }
}
