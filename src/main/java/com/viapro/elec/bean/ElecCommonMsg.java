package com.viapro.elec.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: N2048
 * Date: 13-8-22
 * Time: PM 12:53
 * To change this template use File | Settings | File Templates.
 */
public class ElecCommonMsg implements Serializable {
    private String comID;       //主键ID
    private String stationRun;  //站点运行情况
    private String devRun;      //设备运行情况
    private Date createDate;    //创建日期

    public String getComID() {
        return comID;
    }

    public void setComID(String comID) {
        this.comID = comID;
    }

    public String getStationRun() {
        return stationRun;
    }

    public void setStationRun(String stationRun) {
        this.stationRun = stationRun;
    }

    public String getDevRun() {
        return devRun;
    }

    public void setDevRun(String devRun) {
        this.devRun = devRun;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
