package com.sorsix.bloodscreening.model;

import javax.persistence.*;

@Entity
@Table(name = "blood_tests")
public class BloodTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "lab_id")
    private int labId;

    @Column(name = "doctor_id")
    private int doctorId;

    @Column(name = "patient_id")
    private int patientId;

    /*Explains the state of the blood test:
    * 0 - created blood test (only the IDs populated)
    * 1 - data filled by lab
    * 2 - comments added by doctor*/
    @Column(name = "status")
    private int status;

    @Column(name = "wbc")
    private double wbc;

    @Column(name = "wbc_comment")
    private String wbcComment;

    @Column(name = "rbc")
    private double rbc;

    @Column(name = "rbc_comment")
    private String rbcComment;

    @Column(name = "hgb")
    private double hgb;

    @Column(name = "hgb_comment")
    private String hgbComment;

    @Column(name = "hct")
    private double hct;

    @Column(name = "hct_comment")
    private String hctComment;

    @Column(name = "plt")
    private double plt;

    @Column(name = "plt_comment")
    private String pltComment;

    @Column(name = "mpv")
    private double mpv;

    @Column(name = "mpv_comment")
    private String mpvComment;

    @Column(name = "gra_pct")
    private double graPct;

    @Column(name = "gra_pct_comment")
    private String graPctComment;

    @Column(name = "lym_pct")
    private double lymPct;

    @Column(name = "lym_pct_comment")
    private String lymPctComment;

    @Column(name = "mid_pct")
    private double midPct;

    @Column(name = "mid_pct_comment")
    private String midPctComment;

    @Column(name = "gran")
    private double gran;

    @Column(name = "gran_comment")
    private String granComment;

    @Column(name = "lym")
    private double lym;

    @Column(name = "lym_comment")
    private String lymComment;

    @Column(name = "mid")
    private double mid;

    @Column(name = "mid_comment")
    private String midComment;

    @Column(name = "mcv")
    private double mcv;

    @Column(name = "mcv_comment")
    private String mcvComment;

    @Column(name = "mch")
    private double mch;

    @Column(name = "mch_comment")
    private String mchComment;

    @Column(name = "mchc")
    private double mchc;

    @Column(name = "mchc_comment")
    private String mchcComment;

    @Column(name = "rdw_pct")
    private double rdwPct;

    @Column(name = "rdw_pct_comment")
    private String rdwPctComment;

    public BloodTest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getWbc() {
        return wbc;
    }

    public void setWbc(double wbc) {
        this.wbc = wbc;
    }

    public String getWbcComment() {
        return wbcComment;
    }

    public void setWbcComment(String wbcComment) {
        this.wbcComment = wbcComment;
    }

    public double getRbc() {
        return rbc;
    }

    public void setRbc(double rbc) {
        this.rbc = rbc;
    }

    public String getRbcComment() {
        return rbcComment;
    }

    public void setRbcComment(String rbcComment) {
        this.rbcComment = rbcComment;
    }

    public double getHgb() {
        return hgb;
    }

    public void setHgb(double hgb) {
        this.hgb = hgb;
    }

    public String getHgbComment() {
        return hgbComment;
    }

    public void setHgbComment(String hgbComment) {
        this.hgbComment = hgbComment;
    }

    public double getHct() {
        return hct;
    }

    public void setHct(double hct) {
        this.hct = hct;
    }

    public String getHctComment() {
        return hctComment;
    }

    public void setHctComment(String hctComment) {
        this.hctComment = hctComment;
    }

    public double getPlt() {
        return plt;
    }

    public void setPlt(double plt) {
        this.plt = plt;
    }

    public String getPltComment() {
        return pltComment;
    }

    public void setPltComment(String pltComment) {
        this.pltComment = pltComment;
    }

    public double getMpv() {
        return mpv;
    }

    public void setMpv(double mpv) {
        this.mpv = mpv;
    }

    public String getMpvComment() {
        return mpvComment;
    }

    public void setMpvComment(String mpvComment) {
        this.mpvComment = mpvComment;
    }

    public double getGraPct() {
        return graPct;
    }

    public void setGraPct(double graPct) {
        this.graPct = graPct;
    }

    public String getGraPctComment() {
        return graPctComment;
    }

    public void setGraPctComment(String graPctComment) {
        this.graPctComment = graPctComment;
    }

    public double getLymPct() {
        return lymPct;
    }

    public void setLymPct(double lymPct) {
        this.lymPct = lymPct;
    }

    public String getLymPctComment() {
        return lymPctComment;
    }

    public void setLymPctComment(String lymPctComment) {
        this.lymPctComment = lymPctComment;
    }

    public double getMidPct() {
        return midPct;
    }

    public void setMidPct(double midPct) {
        this.midPct = midPct;
    }

    public String getMidPctComment() {
        return midPctComment;
    }

    public void setMidPctComment(String midPctComment) {
        this.midPctComment = midPctComment;
    }

    public double getGran() {
        return gran;
    }

    public void setGran(double gran) {
        this.gran = gran;
    }

    public String getGranComment() {
        return granComment;
    }

    public void setGranComment(String granComment) {
        this.granComment = granComment;
    }

    public double getLym() {
        return lym;
    }

    public void setLym(double lym) {
        this.lym = lym;
    }

    public String getLymComment() {
        return lymComment;
    }

    public void setLymComment(String lymComment) {
        this.lymComment = lymComment;
    }

    public double getMid() {
        return mid;
    }

    public void setMid(double mid) {
        this.mid = mid;
    }

    public String getMidComment() {
        return midComment;
    }

    public void setMidComment(String midComment) {
        this.midComment = midComment;
    }

    public double getMcv() {
        return mcv;
    }

    public void setMcv(double mcv) {
        this.mcv = mcv;
    }

    public String getMcvComment() {
        return mcvComment;
    }

    public void setMcvComment(String mcvComment) {
        this.mcvComment = mcvComment;
    }

    public double getMch() {
        return mch;
    }

    public void setMch(double mch) {
        this.mch = mch;
    }

    public String getMchComment() {
        return mchComment;
    }

    public void setMchComment(String mchComment) {
        this.mchComment = mchComment;
    }

    public double getMchc() {
        return mchc;
    }

    public void setMchc(double mchc) {
        this.mchc = mchc;
    }

    public String getMchcComment() {
        return mchcComment;
    }

    public void setMchcComment(String mchcComment) {
        this.mchcComment = mchcComment;
    }

    public double getRdwPct() {
        return rdwPct;
    }

    public void setRdwPct(double rdwPct) {
        this.rdwPct = rdwPct;
    }

    public String getRdwPctComment() {
        return rdwPctComment;
    }

    public void setRdwPctComment(String rdwPctComment) {
        this.rdwPctComment = rdwPctComment;
    }
}
