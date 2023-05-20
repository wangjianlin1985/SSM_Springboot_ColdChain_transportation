package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.ChillCar;
import com.chengxusheji.po.UserInfo;
import com.chengxusheji.po.Sswd;

import com.chengxusheji.mapper.SswdMapper;
@Service
public class SswdService {

	@Resource SswdMapper sswdMapper;
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

    /*添加实时温度记录*/
    public void addSswd(Sswd sswd) throws Exception {
    	sswdMapper.addSswd(sswd);
    }

    /*按照查询条件分页查询实时温度记录*/
    public ArrayList<Sswd> querySswd(ChillCar carObj,UserInfo userObj,String cjsj,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(null != carObj &&  carObj.getCarNo() != null  && !carObj.getCarNo().equals(""))  where += " and t_sswd.carObj='" + carObj.getCarNo() + "'";
    	if(null != userObj &&  userObj.getUser_name() != null  && !userObj.getUser_name().equals(""))  where += " and t_sswd.userObj='" + userObj.getUser_name() + "'";
    	if(!cjsj.equals("")) where = where + " and t_sswd.cjsj like '%" + cjsj + "%'";
    	int startIndex = (currentPage-1) * this.rows;
    	return sswdMapper.querySswd(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<Sswd> querySswd(ChillCar carObj,UserInfo userObj,String cjsj) throws Exception  { 
     	String where = "where 1=1";
    	if(null != carObj &&  carObj.getCarNo() != null && !carObj.getCarNo().equals(""))  where += " and t_sswd.carObj='" + carObj.getCarNo() + "'";
    	if(null != userObj &&  userObj.getUser_name() != null && !userObj.getUser_name().equals(""))  where += " and t_sswd.userObj='" + userObj.getUser_name() + "'";
    	if(!cjsj.equals("")) where = where + " and t_sswd.cjsj like '%" + cjsj + "%'";
    	return sswdMapper.querySswdList(where);
    }

    /*查询所有实时温度记录*/
    public ArrayList<Sswd> queryAllSswd()  throws Exception {
        return sswdMapper.querySswdList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(ChillCar carObj,UserInfo userObj,String cjsj) throws Exception {
     	String where = "where 1=1";
    	if(null != carObj &&  carObj.getCarNo() != null && !carObj.getCarNo().equals(""))  where += " and t_sswd.carObj='" + carObj.getCarNo() + "'";
    	if(null != userObj &&  userObj.getUser_name() != null && !userObj.getUser_name().equals(""))  where += " and t_sswd.userObj='" + userObj.getUser_name() + "'";
    	if(!cjsj.equals("")) where = where + " and t_sswd.cjsj like '%" + cjsj + "%'";
        recordNumber = sswdMapper.querySswdCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取实时温度记录*/
    public Sswd getSswd(int sswdId) throws Exception  {
        Sswd sswd = sswdMapper.getSswd(sswdId);
        return sswd;
    }

    /*更新实时温度记录*/
    public void updateSswd(Sswd sswd) throws Exception {
        sswdMapper.updateSswd(sswd);
    }

    /*删除一条实时温度记录*/
    public void deleteSswd (int sswdId) throws Exception {
        sswdMapper.deleteSswd(sswdId);
    }

    /*删除多条实时温度信息*/
    public int deleteSswds (String sswdIds) throws Exception {
    	String _sswdIds[] = sswdIds.split(",");
    	for(String _sswdId: _sswdIds) {
    		sswdMapper.deleteSswd(Integer.parseInt(_sswdId));
    	}
    	return _sswdIds.length;
    }
}
