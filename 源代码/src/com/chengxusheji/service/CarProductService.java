package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.Product;
import com.chengxusheji.po.ChillCar;
import com.chengxusheji.po.CarProduct;

import com.chengxusheji.mapper.CarProductMapper;
@Service
public class CarProductService {

	@Resource CarProductMapper carProductMapper;
    /*每页显示记录数目*/
    private int rows = 10;;
    public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

    /*保存查询后总的页数*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    /*保存查询到的总记录数*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*添加冷藏车商品记录*/
    public void addCarProduct(CarProduct carProduct) throws Exception {
    	carProductMapper.addCarProduct(carProduct);
    }

    /*按照查询条件分页查询冷藏车商品记录*/
    public ArrayList<CarProduct> queryCarProduct(Product productObj,ChillCar carObj,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(null != productObj && productObj.getProductId()!= null && productObj.getProductId()!= 0)  where += " and t_carProduct.productObj=" + productObj.getProductId();
    	if(null != carObj &&  carObj.getCarNo() != null  && !carObj.getCarNo().equals(""))  where += " and t_carProduct.carObj='" + carObj.getCarNo() + "'";
    	int startIndex = (currentPage-1) * this.rows;
    	return carProductMapper.queryCarProduct(where, startIndex, this.rows);
    }
    
    /*按照查询条件分页查询温度报警的冷藏车商品记录*/
    public ArrayList<CarProduct> queryWarningCarProduct(Product productObj,ChillCar carObj,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(null != productObj && productObj.getProductId()!= null && productObj.getProductId()!= 0)  where += " and t_carProduct.productObj=" + productObj.getProductId();
    	if(null != carObj &&  carObj.getCarNo() != null  && !carObj.getCarNo().equals(""))  where += " and t_carProduct.carObj='" + carObj.getCarNo() + "'";
    	int startIndex = (currentPage-1) * this.rows;
    	return carProductMapper.queryWarningCarProduct(where, startIndex, this.rows);
    }
    

    /*按照查询条件查询所有记录*/
    public ArrayList<CarProduct> queryCarProduct(Product productObj,ChillCar carObj) throws Exception  { 
     	String where = "where 1=1";
    	if(null != productObj && productObj.getProductId()!= null && productObj.getProductId()!= 0)  where += " and t_carProduct.productObj=" + productObj.getProductId();
    	if(null != carObj &&  carObj.getCarNo() != null && !carObj.getCarNo().equals(""))  where += " and t_carProduct.carObj='" + carObj.getCarNo() + "'";
    	return carProductMapper.queryCarProductList(where);
    }

    /*查询所有冷藏车商品记录*/
    public ArrayList<CarProduct> queryAllCarProduct()  throws Exception {
        return carProductMapper.queryCarProductList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(Product productObj,ChillCar carObj) throws Exception {
     	String where = "where 1=1";
    	if(null != productObj && productObj.getProductId()!= null && productObj.getProductId()!= 0)  where += " and t_carProduct.productObj=" + productObj.getProductId();
    	if(null != carObj &&  carObj.getCarNo() != null && !carObj.getCarNo().equals(""))  where += " and t_carProduct.carObj='" + carObj.getCarNo() + "'";
        recordNumber = carProductMapper.queryCarProductCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }
    
    /*当前查询条件下温度超标的计算总的页数和记录数*/
    public void queryWarningTotalPageAndRecordNumber(Product productObj,ChillCar carObj) throws Exception {
     	String where = "where 1=1";
    	if(null != productObj && productObj.getProductId()!= null && productObj.getProductId()!= 0)  where += " and t_carProduct.productObj=" + productObj.getProductId();
    	if(null != carObj &&  carObj.getCarNo() != null && !carObj.getCarNo().equals(""))  where += " and t_carProduct.carObj='" + carObj.getCarNo() + "'";
        recordNumber = carProductMapper.queryWarningCarProductCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }
    

    /*根据主键获取冷藏车商品记录*/
    public CarProduct getCarProduct(int id) throws Exception  {
        CarProduct carProduct = carProductMapper.getCarProduct(id);
        return carProduct;
    }

    /*更新冷藏车商品记录*/
    public void updateCarProduct(CarProduct carProduct) throws Exception {
        carProductMapper.updateCarProduct(carProduct);
    }

    /*删除一条冷藏车商品记录*/
    public void deleteCarProduct (int id) throws Exception {
        carProductMapper.deleteCarProduct(id);
    }

    /*删除多条冷藏车商品信息*/
    public int deleteCarProducts (String ids) throws Exception {
    	String _ids[] = ids.split(",");
    	for(String _id: _ids) {
    		carProductMapper.deleteCarProduct(Integer.parseInt(_id));
    	}
    	return _ids.length;
    }
}
