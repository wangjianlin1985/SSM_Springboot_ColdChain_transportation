package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class CarProduct {
    /*记录id*/
    private Integer id;
    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    /*产品名称*/
    private Product productObj;
    public Product getProductObj() {
        return productObj;
    }
    public void setProductObj(Product productObj) {
        this.productObj = productObj;
    }

    /*所在冷藏车*/
    private ChillCar carObj;
    public ChillCar getCarObj() {
        return carObj;
    }
    public void setCarObj(ChillCar carObj) {
        this.carObj = carObj;
    }

    /*产品数量*/
    @NotNull(message="必须输入产品数量")
    private Integer productNum;
    public Integer getProductNum() {
        return productNum;
    }
    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    /*备注信息*/
    private String productMemo;
    public String getProductMemo() {
        return productMemo;
    }
    public void setProductMemo(String productMemo) {
        this.productMemo = productMemo;
    }

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonCarProduct=new JSONObject(); 
		jsonCarProduct.accumulate("id", this.getId());
		jsonCarProduct.accumulate("productObj", this.getProductObj().getProductName());
		jsonCarProduct.accumulate("productObjPri", this.getProductObj().getProductId());
		jsonCarProduct.accumulate("carObj", this.getCarObj().getCarNo());
		jsonCarProduct.accumulate("carObjPri", this.getCarObj().getCarNo());
		jsonCarProduct.accumulate("productNum", this.getProductNum());
		jsonCarProduct.accumulate("dqwd", this.getCarObj().getDqwd());
		jsonCarProduct.accumulate("zdwd", this.getProductObj().getZdwd());
		jsonCarProduct.accumulate("zgwd", this.getProductObj().getZgwd());
		jsonCarProduct.accumulate("productMemo", this.getProductMemo());
		return jsonCarProduct;
    }}