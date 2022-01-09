package com.baimamboukar.java.rms.src.models;

public class ResultData {
    private final String acount;
    private final String bcount;
    private final String ccount;
    private final String dcount;
    private final String fcount;

    public ResultData(String acount, String bcount, String ccount, String dcount, String fcount, String bpluscount,
            String cpluscount, String dpluscount) {
        this.acount = acount;
        this.bcount = bcount;
        this.ccount = ccount;
        this.dcount = dcount;
        this.fcount = fcount;
        this.bpluscount = bpluscount;
        this.cpluscount = cpluscount;
        this.dpluscount = dpluscount;
    }

    private final String bpluscount;
    private final String cpluscount;
    private final String dpluscount;

    public String getAcount() {
        return acount;
    }

    public String getBcount() {
        return bcount;
    }

    public String getCcount() {
        return ccount;
    }

    public String getDcount() {
        return dcount;
    }

    public String getFcount() {
        return fcount;
    }

    public String getBpluscount() {
        return bpluscount;
    }

    public String getCpluscount() {
        return cpluscount;
    }

    public String getDpluscount() {
        return dpluscount;
    }

    @Override
    public String toString() {
        return "ResultData [acount=" + acount + ", bcount=" + bcount + ", bpluscount=" + bpluscount + ", ccount="
                + ccount + ", cpluscount=" + cpluscount + ", dcount=" + dcount + ", dpluscount=" + dpluscount
                + ", fcount=" + fcount + "]";
    }

}
