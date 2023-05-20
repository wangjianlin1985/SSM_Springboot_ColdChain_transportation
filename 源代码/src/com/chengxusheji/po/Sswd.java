package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class Sswd {
    /*记录id*/
    private Integer sswdId;
    public Integer getSswdId(){
        return sswdId;
    }
    public void setSswdId(Integer sswdId){
        this.sswdId = sswdId;
    }

    /*冷藏车*/
    private ChillCar carObj;
    public ChillCar getCarObj() {
        return carObj;
    }
    public void setCarObj(ChillCar carObj) {
        this.carObj = carObj;
    }

    /*采集温度*/
    @NotNull(message="必须输入采集温度")
    private Float cjwd;
    public Float getCjwd() {
        return cjwd;
    }
    public void setCjwd(Float cjwd) {
        this.cjwd = cjwd;
    }

    /*采集的用户*/
    private UserInfo userObj;
    public UserInfo getUserObj() {
        return userObj;
    }
    public void setUserObj(UserInfo userObj) {
        this.userObj = userObj;
    }

    /*采集时间*/
    @NotEmpty(message="采集时间不能为空")
    private String cjsj;
    public String getCjsj() {
        return cjsj;
    }
    public void setCjsj(String cjsj) {
        this.cjsj = cjsj;
    }

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonSswd=new JSONObject(); 
		jsonSswd.accumulate("sswdId", this.getSswdId());
		jsonSswd.accumulate("carObj", this.getCarObj().getCarNo());
		jsonSswd.accumulate("carObjPri", this.getCarObj().getCarNo());
		jsonSswd.accumulate("cjwd", this.getCjwd());
		jsonSswd.accumulate("userObj", this.getUserObj().getName());
		jsonSswd.accumulate("userObjPri", this.getUserObj().getUser_name());
		jsonSswd.accumulate("cjsj", this.getCjsj().length()>19?this.getCjsj().substring(0,19):this.getCjsj());
		return jsonSswd;
    }}