package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class Product {
    /*产品id*/
    private Integer productId;
    public Integer getProductId(){
        return productId;
    }
    public void setProductId(Integer productId){
        this.productId = productId;
    }

    /*产品分类*/
    private ProductClass productClassObj;
    public ProductClass getProductClassObj() {
        return productClassObj;
    }
    public void setProductClassObj(ProductClass productClassObj) {
        this.productClassObj = productClassObj;
    }

    /*产品名称*/
    @NotEmpty(message="产品名称不能为空")
    private String productName;
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /*产品照片*/
    private String productPhoto;
    public String getProductPhoto() {
        return productPhoto;
    }
    public void setProductPhoto(String productPhoto) {
        this.productPhoto = productPhoto;
    }

    /*产品介绍*/
    @NotEmpty(message="产品介绍不能为空")
    private String productDesc;
    public String getProductDesc() {
        return productDesc;
    }
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    /*最低温度*/
    @NotNull(message="必须输入最低温度")
    private Float zdwd;
    public Float getZdwd() {
        return zdwd;
    }
    public void setZdwd(Float zdwd) {
        this.zdwd = zdwd;
    }

    /*最高温度*/
    @NotNull(message="必须输入最高温度")
    private Float zgwd;
    public Float getZgwd() {
        return zgwd;
    }
    public void setZgwd(Float zgwd) {
        this.zgwd = zgwd;
    }

    /*发布时间*/
    @NotEmpty(message="发布时间不能为空")
    private String addTime;
    public String getAddTime() {
        return addTime;
    }
    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonProduct=new JSONObject(); 
		jsonProduct.accumulate("productId", this.getProductId());
		jsonProduct.accumulate("productClassObj", this.getProductClassObj().getProductClassName());
		jsonProduct.accumulate("productClassObjPri", this.getProductClassObj().getProductClassId());
		jsonProduct.accumulate("productName", this.getProductName());
		jsonProduct.accumulate("productPhoto", this.getProductPhoto());
		jsonProduct.accumulate("productDesc", this.getProductDesc());
		jsonProduct.accumulate("zdwd", this.getZdwd());
		jsonProduct.accumulate("zgwd", this.getZgwd());
		jsonProduct.accumulate("addTime", this.getAddTime().length()>19?this.getAddTime().substring(0,19):this.getAddTime());
		return jsonProduct;
    }}