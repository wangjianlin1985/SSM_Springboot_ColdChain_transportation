package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.ProductClass;

public interface ProductClassMapper {
	/*添加产品分类信息*/
	public void addProductClass(ProductClass productClass) throws Exception;

	/*按照查询条件分页查询产品分类记录*/
	public ArrayList<ProductClass> queryProductClass(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有产品分类记录*/
	public ArrayList<ProductClass> queryProductClassList(@Param("where") String where) throws Exception;

	/*按照查询条件的产品分类记录数*/
	public int queryProductClassCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条产品分类记录*/
	public ProductClass getProductClass(int productClassId) throws Exception;

	/*更新产品分类记录*/
	public void updateProductClass(ProductClass productClass) throws Exception;

	/*删除产品分类记录*/
	public void deleteProductClass(int productClassId) throws Exception;

}
