package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.ChillCar;

import com.chengxusheji.mapper.ChillCarMapper;
@Service
public class ChillCarService {

	@Resource ChillCarMapper chillCarMapper;
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

    /*添加冷藏车记录*/
    public void addChillCar(ChillCar chillCar) throws Exception {
    	chillCarMapper.addChillCar(chillCar);
    }

    /*按照查询条件分页查询冷藏车记录*/
    public ArrayList<ChillCar> queryChillCar(String carNo,String carType,String pinpai,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(!carNo.equals("")) where = where + " and t_chillCar.carNo like '%" + carNo + "%'";
    	if(!carType.equals("")) where = where + " and t_chillCar.carType like '%" + carType + "%'";
    	if(!pinpai.equals("")) where = where + " and t_chillCar.pinpai like '%" + pinpai + "%'";
    	int startIndex = (currentPage-1) * this.rows;
    	return chillCarMapper.queryChillCar(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<ChillCar> queryChillCar(String carNo,String carType,String pinpai) throws Exception  { 
     	String where = "where 1=1";
    	if(!carNo.equals("")) where = where + " and t_chillCar.carNo like '%" + carNo + "%'";
    	if(!carType.equals("")) where = where + " and t_chillCar.carType like '%" + carType + "%'";
    	if(!pinpai.equals("")) where = where + " and t_chillCar.pinpai like '%" + pinpai + "%'";
    	return chillCarMapper.queryChillCarList(where);
    }

    /*查询所有冷藏车记录*/
    public ArrayList<ChillCar> queryAllChillCar()  throws Exception {
        return chillCarMapper.queryChillCarList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(String carNo,String carType,String pinpai) throws Exception {
     	String where = "where 1=1";
    	if(!carNo.equals("")) where = where + " and t_chillCar.carNo like '%" + carNo + "%'";
    	if(!carType.equals("")) where = where + " and t_chillCar.carType like '%" + carType + "%'";
    	if(!pinpai.equals("")) where = where + " and t_chillCar.pinpai like '%" + pinpai + "%'";
        recordNumber = chillCarMapper.queryChillCarCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取冷藏车记录*/
    public ChillCar getChillCar(String carNo) throws Exception  {
        ChillCar chillCar = chillCarMapper.getChillCar(carNo);
        return chillCar;
    }

    /*更新冷藏车记录*/
    public void updateChillCar(ChillCar chillCar) throws Exception {
        chillCarMapper.updateChillCar(chillCar);
    }

    /*删除一条冷藏车记录*/
    public void deleteChillCar (String carNo) throws Exception {
        chillCarMapper.deleteChillCar(carNo);
    }

    /*删除多条冷藏车信息*/
    public int deleteChillCars (String carNos) throws Exception {
    	String _carNos[] = carNos.split(",");
    	for(String _carNo: _carNos) {
    		chillCarMapper.deleteChillCar(_carNo);
    	}
    	return _carNos.length;
    }
}
