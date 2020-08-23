package com.sys.cub360.catholic_redgistery.Readmodels;

public class paariish {
    String Dioces,Province,Totalno,children,communtant,date,denary,men,parish,timeofmass,totaalc,women,youth;

    public paariish(String dioces, String province, String totalno, String children, String communtant, String date, String denary, String men, String parish, String timeofmass, String totaalc, String women, String youth) {
        Dioces = dioces;
        Province = province;
        Totalno = totalno;
        this.children = children;
        this.communtant = communtant;
        this.date = date;
        this.denary = denary;
        this.men = men;
        this.parish = parish;
        this.timeofmass = timeofmass;
        this.totaalc = totaalc;
        this.women = women;
        this.youth = youth;
    }

    public String getDioces() {
        return Dioces;
    }

    public String getProvince() {
        return Province;
    }

    public String getTotalno() {
        return Totalno;
    }

    public String getChildren() {
        return children;
    }

    public String getCommuntant() {
        return communtant;
    }

    public String getDate() {
        return date;
    }

    public String getDenary() {
        return denary;
    }

    public String getMen() {
        return men;
    }

    public String getParish() {
        return parish;
    }

    public String getTimeofmass() {
        return timeofmass;
    }

    public String getTotaalc() {
        return totaalc;
    }

    public String getWomen() {
        return women;
    }

    public String getYouth() {
        return youth;
    }
}
