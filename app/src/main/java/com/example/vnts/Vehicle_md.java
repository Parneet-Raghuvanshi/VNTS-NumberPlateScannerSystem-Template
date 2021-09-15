package com.example.vnts;

import java.nio.DoubleBuffer;

public class Vehicle_md {

    public String fueltype;
    public String ownername;
    public String registeringauth;
    public String registrationdate;
    public String vclass;
    public String vechileage;
    public String vechilemodel;
    public String vhistory;
    public String vno;

    public Vehicle_md() {
    }

    public Vehicle_md(String fueltype, String ownername, String registeringauth, String registrationdate, String vclass, String vechileage, String vechilemodel, String vhistory, String vno) {
        this.fueltype = fueltype;
        this.ownername = ownername;
        this.registeringauth = registeringauth;
        this.registrationdate = registrationdate;
        this.vclass = vclass;
        this.vechileage = vechileage;
        this.vechilemodel = vechilemodel;
        this.vhistory = vhistory;
        this.vno = vno;
    }

    public String getFueltype() {
        return fueltype;
    }

    public void setFueltype(String fueltype) {
        this.fueltype = fueltype;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getRegisteringauth() {
        return registeringauth;
    }

    public void setRegisteringauth(String registeringauth) {
        this.registeringauth = registeringauth;
    }

    public String getRegistrationdate() {
        return registrationdate;
    }

    public void setRegistrationdate(String registrationdate) {
        this.registrationdate = registrationdate;
    }

    public String getVclass() {
        return vclass;
    }

    public void setVclass(String vclass) {
        this.vclass = vclass;
    }

    public String getVechileage() {
        return vechileage;
    }

    public void setVechileage(String vechileage) {
        this.vechileage = vechileage;
    }

    public String getVechilemodel() {
        return vechilemodel;
    }

    public void setVechilemodel(String vechilemodel) {
        this.vechilemodel = vechilemodel;
    }

    public String getVhistory() {
        return vhistory;
    }

    public void setVhistory(String vhistory) {
        this.vhistory = vhistory;
    }

    public String getVno() {
        return vno;
    }

    public void setVno(String vno) {
        this.vno = vno;
    }
}
