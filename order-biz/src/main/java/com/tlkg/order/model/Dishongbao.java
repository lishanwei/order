package com.tlkg.order.model;

import java.util.Date;

public class Dishongbao {
    private Integer id;

    private Integer senduserid;

    private String sendusernm;

    private Integer receiveuserid;

    private String receiveusernm;

    private Integer number;

    private Integer price;

    private String message;

    private Integer sourceid;

    private Integer hbsource;

    private Integer userstatus;

    private Integer hbflag;

    private Integer hbstatus;

    private Date createtime;

    private Date modifytime;

    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSenduserid() {
        return senduserid;
    }

    public void setSenduserid(Integer senduserid) {
        this.senduserid = senduserid;
    }

    public String getSendusernm() {
        return sendusernm;
    }

    public void setSendusernm(String sendusernm) {
        this.sendusernm = sendusernm;
    }

    public Integer getReceiveuserid() {
        return receiveuserid;
    }

    public void setReceiveuserid(Integer receiveuserid) {
        this.receiveuserid = receiveuserid;
    }

    public String getReceiveusernm() {
        return receiveusernm;
    }

    public void setReceiveusernm(String receiveusernm) {
        this.receiveusernm = receiveusernm;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getSourceid() {
        return sourceid;
    }

    public void setSourceid(Integer sourceid) {
        this.sourceid = sourceid;
    }

    public Integer getHbsource() {
        return hbsource;
    }

    public void setHbsource(Integer hbsource) {
        this.hbsource = hbsource;
    }

    public Integer getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(Integer userstatus) {
        this.userstatus = userstatus;
    }

    public Integer getHbflag() {
        return hbflag;
    }

    public void setHbflag(Integer hbflag) {
        this.hbflag = hbflag;
    }

    public Integer getHbstatus() {
        return hbstatus;
    }

    public void setHbstatus(Integer hbstatus) {
        this.hbstatus = hbstatus;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}