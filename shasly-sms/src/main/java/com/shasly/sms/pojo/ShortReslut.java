package com.shasly.sms.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lvyifan
 *
 * @Author lvyifan
 */
public class ShortReslut implements Serializable {

    private String respCode;
    private String respDesc;
    private String failCount;
    private List<String> failList;
    private String smsId;

    public ShortReslut() {

    }

    public ShortReslut(String respCode, String respDesc, String failCount, List<String> failList, String smsId) {

        this.respCode = respCode;
        this.respDesc = respDesc;
        this.failCount = failCount;
        this.failList = failList;
        this.smsId = smsId;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public String getFailCount() {
        return failCount;
    }

    public void setFailCount(String failCount) {
        this.failCount = failCount;
    }

    public List<String> getFailList() {
        return failList;
    }

    public void setFailList(List<String> failList) {
        this.failList = failList;
    }

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    @Override
    public String toString() {
        return "ShortReslut{" +
                "respCode='" + respCode + '\'' +
                ", respDesc='" + respDesc + '\'' +
                ", failCount='" + failCount + '\'' +
                ", failList=" + failList +
                ", smsId='" + smsId + '\'' +
                '}';
    }
}
