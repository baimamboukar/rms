/*
 📌📌📌 THE ICT UNIVERSITY, FALL 20201 📌📌📌

 🚩 ♨♨♨♨♨ JAVA PROGRAMMING I ♨♨♨♨♨ 🚩

 🚩 INSTRUCTOR: ENGR. Daniel Moune

 📌﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋📌

 🎯 NAMES: BAIMAM BOUKAR JEAN JACQUES
 🎯 EMAIL: baimam.jeanjacque@ictuniversity.edu.cm
 🎯 WHATSAPP: (+237) 690535759
 🎯 TWITTER: @baimamjj
 🎯 LINKEDIN: linkedin.com/in/baimamboukar
 🎯 WEBSITE: www.baimamboukar.me
 */

package com.baimamboukar.java.rms.src.models;

public class ResultData {
    /*
     * 🎯🎯🎯 RESULT DATA CLASS 🎯🎯🎯
     * Thuis class contains the statistic data related to a result
     * And also the corresponding getters and setters
     * 
     * @author: BAIMAMBOUKAR
     * 
     * @version: 1.0
     * 
     * @since: First Release
     * 
     */
    private final String acount;
    private final String bcount;
    private final String ccount;
    private final String dcount;
    private final String fcount;

    /**
     * 🚩🚩🚩 CONSTRUCTOR 🚩🚩🚩
     * This method is the constructor of the class result.
     * It is called when a new object of type result is created.
     * 
     * @author Baimam Boukar JJ
     * @since FIRST RELEASE
     * @param acount
     * @param bcount
     * @param ccount
     * @param dcount
     * @param fcount
     * @param bpluscount
     * @param cpluscount
     * @param dpluscount
     */
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

    /**
     * 🚩🚩🚩 GETTERS AND SETTERS 🚩🚩🚩
     * These are the getters and setters of the class result
     */
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
