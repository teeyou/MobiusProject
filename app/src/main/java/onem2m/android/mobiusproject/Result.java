package onem2m.android.mobiusproject;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

class Result implements Serializable {
    @SerializedName("DEVICE_SEQ")
    int DEVICE_SEQ;
    @SerializedName("DEVICE_MODEL")
    String DEVICE_MODEL;
    @SerializedName("DEVICE_SERIAL")
    String DEVICE_SERIAL;

    @SerializedName("DEVICE_SCODE") //통합센서 번호
    String DEVICE_SCODE;

    @SerializedName("DEVICE_FIELD01") //온도 -10 ~ 85 도
    float DEVICE_FIELD01;
    @SerializedName("DEVICE_FIELD02") //습도 0 ~ 100 %
    float DEVICE_FIELD02;
    @SerializedName("DEVICE_FIELD03") //조도 0 ~ 100 %
    float DEVICE_FIELD03;
    @SerializedName("DEVICE_FIELD04") //10분당 움직임 수 0 ~
    float DEVICE_FIELD04;
    @SerializedName("DEVICE_FIELD05") //최종 움직임 감지 시간
    float DEVICE_FIELD05;
    @SerializedName("DEVICE_FIELD10") //CO2 0 ~ 6400 ppm
    float DEVICE_FIELD10;
    @SerializedName("DEVICE_FIELD11") //tVOC 0 ~ 1187 ppb
    float DEVICE_FIELD11;

    @SerializedName("DEVICE_DATA_REG_DTM")
    String DEVICE_DATA_REG_DTM;

    public int getDEVICE_SEQ() {
        return DEVICE_SEQ;
    }

    public void setDEVICE_SEQ(int DEVICE_SEQ) {
        this.DEVICE_SEQ = DEVICE_SEQ;
    }

    public String getDEVICE_MODEL() {
        return DEVICE_MODEL;
    }

    public void setDEVICE_MODEL(String DEVICE_MODEL) {
        this.DEVICE_MODEL = DEVICE_MODEL;
    }

    public String getDEVICE_SERIAL() {
        return DEVICE_SERIAL;
    }

    public void setDEVICE_SERIAL(String DEVICE_SERIAL) {
        this.DEVICE_SERIAL = DEVICE_SERIAL;
    }

    public float getDEVICE_FIELD01() {
        return DEVICE_FIELD01;
    }

    public void setDEVICE_FIELD01(float DEVICE_FIELD01) {
        this.DEVICE_FIELD01 = DEVICE_FIELD01;
    }

    public float getDEVICE_FIELD02() {
        return DEVICE_FIELD02;
    }

    public void setDEVICE_FIELD02(float DEVICE_FIELD02) {
        this.DEVICE_FIELD02 = DEVICE_FIELD02;
    }

    public float getDEVICE_FIELD03() {
        return DEVICE_FIELD03;
    }

    public void setDEVICE_FIELD03(float DEVICE_FIELD03) {
        this.DEVICE_FIELD03 = DEVICE_FIELD03;
    }

    public float getDEVICE_FIELD04() {
        return DEVICE_FIELD04;
    }

    public void setDEVICE_FIELD04(float DEVICE_FIELD04) {
        this.DEVICE_FIELD04 = DEVICE_FIELD04;
    }

    public float getDEVICE_FIELD05() {
        return DEVICE_FIELD05;
    }

    public void setDEVICE_FIELD05(float DEVICE_FIELD05) {
        this.DEVICE_FIELD05 = DEVICE_FIELD05;
    }

    public float getDEVICE_FIELD10() {
        return DEVICE_FIELD10;
    }

    public void setDEVICE_FIELD10(float DEVICE_FIELD10) {
        this.DEVICE_FIELD10 = DEVICE_FIELD10;
    }

    public float getDEVICE_FIELD11() {
        return DEVICE_FIELD11;
    }

    public void setDEVICE_FIELD11(float DEVICE_FIELD11) {
        this.DEVICE_FIELD11 = DEVICE_FIELD11;
    }

    public String getDEVICE_DATA_REG_DTM() {
        return DEVICE_DATA_REG_DTM;
    }

    public void setDEVICE_DATA_REG_DTM(String DEVICE_DATA_REG_DTM) {
        this.DEVICE_DATA_REG_DTM = DEVICE_DATA_REG_DTM;
    }

    public String getDEVICE_SCODE() {
        return DEVICE_SCODE;
    }

    public void setDEVICE_SCODE(String DEVICE_SCODE) {
        this.DEVICE_SCODE = DEVICE_SCODE;
    }
}
