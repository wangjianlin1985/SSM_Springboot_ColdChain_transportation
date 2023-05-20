package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.Sswd;

public interface SswdMapper {
	/*添加实时温度信息*/
	public void addSswd(Sswd sswd) throws Exception;

	/*按照查询条件分页查询实时温度记录*/
	public ArrayList<Sswd> querySswd(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有实时温度记录*/
	public ArrayList<Sswd> querySswdList(@Param("where") String where) throws Exception;

	/*按照查询条件的实时温度记录数*/
	public int querySswdCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条实时温度记录*/
	public Sswd getSswd(int sswdId) throws Exception;

	/*更新实时温度记录*/
	public void updateSswd(Sswd sswd) throws Exception;

	/*删除实时温度记录*/
	public void deleteSswd(int sswdId) throws Exception;

}
