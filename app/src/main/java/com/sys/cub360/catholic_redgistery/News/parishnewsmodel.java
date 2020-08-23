package com.sys.cub360.catholic_redgistery.News;

public class parishnewsmodel {
    String parish,descparish,infopriest,aboutparish;

    public parishnewsmodel(String parish, String descparish, String infopriest, String aboutparish) {
        this.parish = parish;
        this.descparish = descparish;
        this.infopriest = infopriest;
        this.aboutparish = aboutparish;
    }

    public String getParish() {
        return parish;
    }

    public String getDescparish() {
        return descparish;
    }

    public String getInfopriest() {
        return infopriest;
    }

    public String getAboutparish() {
        return aboutparish;
    }
}
