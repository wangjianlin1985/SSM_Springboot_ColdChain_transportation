package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class ChillCar {
    /*车辆编号*/
    @NotEmpty(message="车辆编号不能为空")
    private String carNo;
    public String getCarNo(){
        return carNo;
    }
    public void setCarNo(String carNo){
        this.carNo = carNo;
    }

    /*车辆种类*/
    @NotEmpty(message="车辆种类不能为空")
    private String carType;
    public String getCarType() {
        return carType;
    }
    public void setCarType(String carType) {
        this.carType = carType;
    }

    /*品牌*/
    @NotEmpty(message="品牌不能为空")
    private String pinpai;
    public String getPinpai() {
        return pinpai;
    }
    public void setPinpai(String pinpai) {
        this.pinpai = pinpai;
    }

    /*排量*/
    @NotEmpty(message="排量不能为空")
    private String pl;
    public String getPl() {
        return pl;
    }
    public void setPl(String pl) {
        this.pl = pl;
    }

    /*车辆照片*/
    private String carPhoto;
    public String getCarPhoto() {
        return carPhoto;
    }
    public void setCarPhoto(String carPhoto) {
        this.carPhoto = carPhoto;
    }

    /*冷藏车介绍*/
    @NotEmpty(message="冷藏车介绍不能为空")
    private String carDesc;
    public String getCarDesc() {
        return carDesc;
    }
    public void setCarDesc(String carDesc) {
        this.carDesc = carDesc;
    }

    /*当前温度*/
    @NotNull(message="必须输入当前温度")
    private Float dqwd;
    public Float getDqwd() {
        return dqwd;
    }
    public void setDqwd(Float dqwd) {
        this.dqwd = dqwd;
    }

    /*备注信息*/
    private String carMemo;
    public String getCarMemo() {
        return carMemo;
    }
    public void setCarMemo(String carMemo) {
        this.carMemo = carMemo;
    }

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonChillCar=new JSONObject(); 
		jsonChillCar.accumulate("carNo", this.getCarNo());
		jsonChillCar.accumulate("carType", this.getCarType());
		jsonChillCar.accumulate("pinpai", this.getPinpai());
		jsonChillCar.accumulate("pl", this.getPl());
		jsonChillCar.accumulate("carPhoto", this.getCarPhoto());
		jsonChillCar.accumulate("carDesc", this.getCarDesc());
		jsonChillCar.accumulate("dqwd", this.getDqwd());
		jsonChillCar.accumulate("carMemo", this.getCarMemo());
		return jsonChillCar;
    }}