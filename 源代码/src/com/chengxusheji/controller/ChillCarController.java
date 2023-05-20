package com.chengxusheji.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.chengxusheji.utils.ExportExcelUtil;
import com.chengxusheji.utils.UserException;
import com.chengxusheji.service.ChillCarService;
import com.chengxusheji.po.ChillCar;

//ChillCar管理控制层
@Controller
@RequestMapping("/ChillCar")
public class ChillCarController extends BaseController {

    /*业务层对象*/
    @Resource ChillCarService chillCarService;

	@InitBinder("chillCar")
	public void initBinderChillCar(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("chillCar.");
	}
	/*跳转到添加ChillCar视图*/
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request) throws Exception {
		model.addAttribute(new ChillCar());
		return "ChillCar_add";
	}

	/*客户端ajax方式提交添加冷藏车信息*/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@Validated ChillCar chillCar, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
		boolean success = false;
		if (br.hasErrors()) {
			message = "输入信息不符合要求！";
			writeJsonResponse(response, success, message);
			return ;
		}
		if(chillCarService.getChillCar(chillCar.getCarNo()) != null) {
			message = "车辆编号已经存在！";
			writeJsonResponse(response, success, message);
			return ;
		}
		try {
			chillCar.setCarPhoto(this.handlePhotoUpload(request, "carPhotoFile"));
		} catch(UserException ex) {
			message = "图片格式不正确！";
			writeJsonResponse(response, success, message);
			return ;
		}
        chillCarService.addChillCar(chillCar);
        message = "冷藏车添加成功!";
        success = true;
        writeJsonResponse(response, success, message);
	}
	/*ajax方式按照查询条件分页查询冷藏车信息*/
	@RequestMapping(value = { "/list" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void list(String carNo,String carType,String pinpai,Integer page,Integer rows, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (page==null || page == 0) page = 1;
		if (carNo == null) carNo = "";
		if (carType == null) carType = "";
		if (pinpai == null) pinpai = "";
		if(rows != 0)chillCarService.setRows(rows);
		List<ChillCar> chillCarList = chillCarService.queryChillCar(carNo, carType, pinpai, page);
	    /*计算总的页数和总的记录数*/
	    chillCarService.queryTotalPageAndRecordNumber(carNo, carType, pinpai);
	    /*获取到总的页码数目*/
	    int totalPage = chillCarService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = chillCarService.getRecordNumber();
        response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象
		JSONObject jsonObj=new JSONObject();
		jsonObj.accumulate("total", recordNumber);
		JSONArray jsonArray = new JSONArray();
		for(ChillCar chillCar:chillCarList) {
			JSONObject jsonChillCar = chillCar.getJsonObject();
			jsonArray.put(jsonChillCar);
		}
		jsonObj.accumulate("rows", jsonArray);
		out.println(jsonObj.toString());
		out.flush();
		out.close();
	}

	/*ajax方式按照查询条件分页查询冷藏车信息*/
	@RequestMapping(value = { "/listAll" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void listAll(HttpServletResponse response) throws Exception {
		List<ChillCar> chillCarList = chillCarService.queryAllChillCar();
        response.setContentType("text/json;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = new JSONArray();
		for(ChillCar chillCar:chillCarList) {
			JSONObject jsonChillCar = new JSONObject();
			jsonChillCar.accumulate("carNo", chillCar.getCarNo());
			jsonArray.put(jsonChillCar);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}

	/*前台按照查询条件分页查询冷藏车信息*/
	@RequestMapping(value = { "/frontlist" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String frontlist(String carNo,String carType,String pinpai,Integer currentPage, Model model, HttpServletRequest request) throws Exception  {
		if (currentPage==null || currentPage == 0) currentPage = 1;
		if (carNo == null) carNo = "";
		if (carType == null) carType = "";
		if (pinpai == null) pinpai = "";
		List<ChillCar> chillCarList = chillCarService.queryChillCar(carNo, carType, pinpai, currentPage);
	    /*计算总的页数和总的记录数*/
	    chillCarService.queryTotalPageAndRecordNumber(carNo, carType, pinpai);
	    /*获取到总的页码数目*/
	    int totalPage = chillCarService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = chillCarService.getRecordNumber();
	    request.setAttribute("chillCarList",  chillCarList);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("recordNumber", recordNumber);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("carNo", carNo);
	    request.setAttribute("carType", carType);
	    request.setAttribute("pinpai", pinpai);
		return "ChillCar/chillCar_frontquery_result"; 
	}

	 
	
     /*前台查询ChillCar信息*/
	@RequestMapping(value="/{carNo}/frontshow",method=RequestMethod.GET)
	public String frontshow(@PathVariable String carNo,Model model,HttpServletRequest request) throws Exception {
		/*根据主键carNo获取ChillCar对象*/
        ChillCar chillCar = chillCarService.getChillCar(carNo);

        request.setAttribute("chillCar",  chillCar);
        return "ChillCar/chillCar_frontshow";
	}

	/*ajax方式显示冷藏车修改jsp视图页*/
	@RequestMapping(value="/{carNo}/update",method=RequestMethod.GET)
	public void update(@PathVariable String carNo,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
        /*根据主键carNo获取ChillCar对象*/
        ChillCar chillCar = chillCarService.getChillCar(carNo);

        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONObject jsonChillCar = chillCar.getJsonObject();
		out.println(jsonChillCar.toString());
		out.flush();
		out.close();
	}

	/*ajax方式更新冷藏车信息*/
	@RequestMapping(value = "/{carNo}/update", method = RequestMethod.POST)
	public void update(@Validated ChillCar chillCar, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
    	boolean success = false;
		if (br.hasErrors()) { 
			message = "输入的信息有错误！";
			writeJsonResponse(response, success, message);
			return;
		}
		String carPhotoFileName = this.handlePhotoUpload(request, "carPhotoFile");
		if(!carPhotoFileName.equals("upload/NoImage.jpg"))chillCar.setCarPhoto(carPhotoFileName); 


		try {
			chillCarService.updateChillCar(chillCar);
			message = "冷藏车更新成功!";
			success = true;
			writeJsonResponse(response, success, message);
		} catch (Exception e) {
			e.printStackTrace();
			message = "冷藏车更新失败!";
			writeJsonResponse(response, success, message); 
		}
	}
    /*删除冷藏车信息*/
	@RequestMapping(value="/{carNo}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable String carNo,HttpServletRequest request) throws UnsupportedEncodingException {
		  try {
			  chillCarService.deleteChillCar(carNo);
	            request.setAttribute("message", "冷藏车删除成功!");
	            return "message";
	        } catch (Exception e) { 
	            e.printStackTrace();
	            request.setAttribute("error", "冷藏车删除失败!");
				return "error";

	        }

	}

	/*ajax方式删除多条冷藏车记录*/
	@RequestMapping(value="/deletes",method=RequestMethod.POST)
	public void delete(String carNos,HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
		String message = "";
    	boolean success = false;
        try { 
        	int count = chillCarService.deleteChillCars(carNos);
        	success = true;
        	message = count + "条记录删除成功";
        	writeJsonResponse(response, success, message);
        } catch (Exception e) { 
            //e.printStackTrace();
            message = "有记录存在外键约束,删除失败";
            writeJsonResponse(response, success, message);
        }
	}

	/*按照查询条件导出冷藏车信息到Excel*/
	@RequestMapping(value = { "/OutToExcel" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void OutToExcel(String carNo,String carType,String pinpai, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
        if(carNo == null) carNo = "";
        if(carType == null) carType = "";
        if(pinpai == null) pinpai = "";
        List<ChillCar> chillCarList = chillCarService.queryChillCar(carNo,carType,pinpai);
        ExportExcelUtil ex = new ExportExcelUtil();
        String _title = "ChillCar信息记录"; 
        String[] headers = { "车辆编号","车辆种类","品牌","排量","车辆照片","当前温度"};
        List<String[]> dataset = new ArrayList<String[]>(); 
        for(int i=0;i<chillCarList.size();i++) {
        	ChillCar chillCar = chillCarList.get(i); 
        	dataset.add(new String[]{chillCar.getCarNo(),chillCar.getCarType(),chillCar.getPinpai(),chillCar.getPl(),chillCar.getCarPhoto(),chillCar.getDqwd() + ""});
        }
        /*
        OutputStream out = null;
		try {
			out = new FileOutputStream("C://output.xls");
			ex.exportExcel(title,headers, dataset, out);
		    out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		OutputStream out = null;//创建一个输出流对象 
		try { 
			out = response.getOutputStream();//
			response.setHeader("Content-disposition","attachment; filename="+"ChillCar.xls");//filename是下载的xls的名，建议最好用英文 
			response.setContentType("application/msexcel;charset=UTF-8");//设置类型 
			response.setHeader("Pragma","No-cache");//设置头 
			response.setHeader("Cache-Control","no-cache");//设置头 
			response.setDateHeader("Expires", 0);//设置日期头  
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			ex.exportExcel(rootPath,_title,headers, dataset, out);
			out.flush();
		} catch (IOException e) { 
			e.printStackTrace(); 
		}finally{
			try{
				if(out!=null){ 
					out.close(); 
				}
			}catch(IOException e){ 
				e.printStackTrace(); 
			} 
		}
    }
}
