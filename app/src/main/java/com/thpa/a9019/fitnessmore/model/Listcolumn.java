package com.thpa.a9019.fitnessmore.model;

/**
 * Created by 9019 on 30/4/2560.
 */

public class Listcolumn {
    private String name;
    private String getedit;

    public Listcolumn(String name, String getedit) {
        this.name = name;
        this.getedit = getedit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getedit() {
        return getedit;
    }

    public void setedit(String getedit) {
        this.getedit = getedit;
    }
}
