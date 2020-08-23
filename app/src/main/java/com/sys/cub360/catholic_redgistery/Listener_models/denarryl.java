package com.sys.cub360.catholic_redgistery.Listener_models;

public class denarryl {
    String denary, Dioces,date,Totalno,totaalc,pushid,Province;

    public denarryl(String denary, String dioces, String date, String totalno, String totaalc, String pushid, String province) {
        this.denary = denary;
        Dioces = dioces;
        this.date = date;
        Totalno = totalno;
        this.totaalc = totaalc;
        this.pushid = pushid;
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

    public String getPushid() {
        return pushid;
    }

    public String getProvince() {
        return Province;
    }
}
