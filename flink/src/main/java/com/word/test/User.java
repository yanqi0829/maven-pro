package com.word.test;

public class User {
    public String systemCode;
    public String enpyCode;
    public String serialNumber;
    public String provinceCode;
    public String userId;
    public String certId;
    public String certType;
    public String name;

    public User() {
    }

    public User(String systemCode, String enpyCode, String serialNumber, String provinceCode, String userId, String certId, String certType, String name) {
        this.systemCode = systemCode;
        this.enpyCode = enpyCode;
        this.serialNumber = serialNumber;
        this.provinceCode = provinceCode;
        this.userId = userId;
        this.certId = certId;
        this.certType = certType;
        this.name = name;
    }

    public String getSystemCode() {return systemCode;}

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getEnpyCode() {return enpyCode;}

    public void setEnpyCode(String enpyCode) {
        this.enpyCode = enpyCode;
    }

    public String getSerialNumber() {return serialNumber;}

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getProvinceCode() {return provinceCode;}

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getUserId() {return userId;}

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCertId() {return certId;}

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getCertType() {return certType;}

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "systemCode='" + systemCode + '\'' +
                ", enpyCode='" + enpyCode + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                ", userId='" + userId + '\'' +
                ", certId='" + certId + '\'' +
                ", certType='" + certType + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
