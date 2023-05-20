package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.ChillCar;

public interface ChillCarMapper {
	/*添加冷藏车信息*/
	public void addChillCar(ChillCar chillCar) throws Exception;

	/*按照查询条件分页查询冷藏车记录*/
	public ArrayList<ChillCar> queryChillCar(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有冷藏车记录*/
	public ArrayList<ChillCar> queryChillCarList(@Param("where") String where) throws Exception;

	/*按照查询条件的冷藏车记录数*/
	public int queryChillCarCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条冷藏车记录*/
	public ChillCar getChillCar(String carNo) throws Exception;

	/*更新冷藏车记录*/
	public void updateChillCar(ChillCar chillCar) throws Exception;

	/*删除冷藏车记录*/
	public void deleteChillCar(String carNo) throws Exception;

}
