package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.CarProduct;

public interface CarProductMapper {
	/*添加冷藏车商品信息*/
	public void addCarProduct(CarProduct carProduct) throws Exception;

	/*按照查询条件分页查询冷藏车商品记录*/
	public ArrayList<CarProduct> queryCarProduct(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	
	/*按照查询条件分页查询温度报警的冷藏车商品记录*/
	public ArrayList<CarProduct> queryWarningCarProduct(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;
	
	
	/*按照查询条件查询所有冷藏车商品记录*/
	public ArrayList<CarProduct> queryCarProductList(@Param("where") String where) throws Exception;

	/*按照查询条件的冷藏车商品记录数*/
	public int queryCarProductCount(@Param("where") String where) throws Exception; 
	
	/*按照查询条件的温度超标的冷藏车商品记录数*/
	public int queryWarningCarProductCount(@Param("where") String where) throws Exception; 
	

	/*根据主键查询某条冷藏车商品记录*/
	public CarProduct getCarProduct(int id) throws Exception;

	/*更新冷藏车商品记录*/
	public void updateCarProduct(CarProduct carProduct) throws Exception;

	/*删除冷藏车商品记录*/
	public void deleteCarProduct(int id) throws Exception;

}
