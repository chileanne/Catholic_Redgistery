package com.sys.cub360.catholic_redgistery.Readmodels;

public class denarry {
    String denary, Dioces,date,Totalno,totaalc,Province;

    public denarry(String denary, String dioces, String date, String totalno, String totaalc, String province) {
        this.denary = denary;
        Dioces = dioces;
        this.date = date;
        Totalno = totalno;
        this.totaalc = totaalc;
        Province = province;
    }

    public String getDenary() {
        return denary;
    }

    public String getDioces() {
        return Dioces;
    }

    public String getDate() {
        return date;
    }

    public String getTotalno() {
        return Totalno;
    }

    public String getTotaalc() {
        return totaalc;
    }

    public String getProvince() {
        return Province;
    }
}
