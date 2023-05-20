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
import com.chengxusheji.service.CarProductService;
import com.chengxusheji.po.CarProduct;
import com.chengxusheji.service.ChillCarService;
import com.chengxusheji.po.ChillCar;
import com.chengxusheji.service.ProductService;
import com.chengxusheji.po.Product;

//CarProduct管理控制层
@Controller
@RequestMapping("/CarProduct")
public class CarProductController extends BaseController {

    /*业务层对象*/
    @Resource CarProductService carProductService;

    @Resource ChillCarService chillCarService;
    @Resource ProductService productService;
	@InitBinder("productObj")
	public void initBinderproductObj(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("productObj.");
	}
	@InitBinder("carObj")
	public void initBindercarObj(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("carObj.");
	}
	@InitBinder("carProduct")
	public void initBinderCarProduct(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("carProduct.");
	}
	/*跳转到添加CarProduct视图*/
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request) throws Exception {
		model.addAttribute(new CarProduct());
		/*查询所有的ChillCar信息*/
		List<ChillCar> chillCarList = chillCarService.queryAllChillCar();
		request.setAttribute("chillCarList", chillCarList);
		/*查询所有的Product信息*/
		List<Product> productList = productService.queryAllProduct();
		request.setAttribute("productList", productList);
		return "CarProduct_add";
	}

	/*客户端ajax方式提交添加冷藏车商品信息*/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@Validated CarProduct carProduct, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
		boolean success = false;
		if (br.hasErrors()) {
			message = "输入信息不符合要求！";
			writeJsonResponse(response, success, message);
			return ;
		}
		
		if(carProductService.queryCarProduct(carProduct.getProductObj(), carProduct.getCarObj()).size() > 0) {
			message = "该冷藏车已经登记了该商品，直接去修改数量即可";
			writeJsonResponse(response, success, message);
			return ;
		}
		
        carProductService.addCarProduct(carProduct);
        message = "冷藏车商品添加成功!";
        success = true;
        writeJsonResponse(response, success, message);
	}
	/*ajax方式按照查询条件分页查询冷藏车商品信息*/
	@RequestMapping(value = { "/list" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void list(@ModelAttribute("productObj") Product productObj,@ModelAttribute("carObj") ChillCar carObj,Integer page,Integer rows, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (page==null || page == 0) page = 1;
		if(rows != 0)carProductService.setRows(rows);
		List<CarProduct> carProductList = carProductService.queryCarProduct(productObj, carObj, page);
	    /*计算总的页数和总的记录数*/
	    carProductService.queryTotalPageAndRecordNumber(productObj, carObj);
	    /*获取到总的页码数目*/
	    int totalPage = carProductService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = carProductService.getRecordNumber();
        response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象
		JSONObject jsonObj=new JSONObject();
		jsonObj.accumulate("total", recordNumber);
		JSONArray jsonArray = new JSONArray();
		for(CarProduct carProduct:carProductList) {
			JSONObject jsonCarProduct = carProduct.getJsonObject();
			jsonArray.put(jsonCarProduct);
		}
		jsonObj.accumulate("rows", jsonArray);
		out.println(jsonObj.toString());
		out.flush();
		out.close();
	}
	
	/*ajax方式按照查询条件分页查询温度报警的冷藏车商品信息*/
	@RequestMapping(value = { "/warningList" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void warningList(@ModelAttribute("productObj") Product productObj,@ModelAttribute("carObj") ChillCar carObj,Integer page,Integer rows, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (page==null || page == 0) page = 1;
		if(rows != 0)carProductService.setRows(rows);
		List<CarProduct> carProductList = carProductService.queryWarningCarProduct(productObj, carObj, page);
	    /*计算总的页数和总的记录数*/
	    carProductService.queryWarningTotalPageAndRecordNumber(productObj, carObj);
	    /*获取到总的页码数目*/
	    int totalPage = carProductService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = carProductService.getRecordNumber();
        response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象
		JSONObject jsonObj=new JSONObject();
		jsonObj.accumulate("total", recordNumber);
		JSONArray jsonArray = new JSONArray();
		for(CarProduct carProduct:carProductList) {
			JSONObject jsonCarProduct = carProduct.getJsonObject();
			jsonArray.put(jsonCarProduct);
		}
		jsonObj.accumulate("rows", jsonArray);
		out.println(jsonObj.toString());
		out.flush();
		out.close();
	}
	
	

	/*ajax方式按照查询条件分页查询冷藏车商品信息*/
	@RequestMapping(value = { "/listAll" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void listAll(HttpServletResponse response) throws Exception {
		List<CarProduct> carProductList = carProductService.queryAllCarProduct();
        response.setContentType("text/json;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = new JSONArray();
		for(CarProduct carProduct:carProductList) {
			JSONObject jsonCarProduct = new JSONObject();
			jsonCarProduct.accumulate("id", carProduct.getId());
			jsonArray.put(jsonCarProduct);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}

	/*前台按照查询条件分页查询冷藏车商品信息*/
	@RequestMapping(value = { "/frontlist" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String frontlist(@ModelAttribute("productObj") Product productObj,@ModelAttribute("carObj") ChillCar carObj,Integer currentPage, Model model, HttpServletRequest request) throws Exception  {
		if (currentPage==null || currentPage == 0) currentPage = 1;
		List<CarProduct> carProductList = carProductService.queryCarProduct(productObj, carObj, currentPage);
	    /*计算总的页数和总的记录数*/
	    carProductService.queryTotalPageAndRecordNumber(productObj, carObj);
	    /*获取到总的页码数目*/
	    int totalPage = carProductService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = carProductService.getRecordNumber();
	    request.setAttribute("carProductList",  carProductList);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("recordNumber", recordNumber);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("productObj", productObj);
	    request.setAttribute("carObj", carObj);
	    List<ChillCar> chillCarList = chillCarService.queryAllChillCar();
	    request.setAttribute("chillCarList", chillCarList);
	    List<Product> productList = productService.queryAllProduct();
	    request.setAttribute("productList", productList);
		return "CarProduct/carProduct_frontquery_result"; 
	}

     /*前台查询CarProduct信息*/
	@RequestMapping(value="/{id}/frontshow",method=RequestMethod.GET)
	public String frontshow(@PathVariable Integer id,Model model,HttpServletRequest request) throws Exception {
		/*根据主键id获取CarProduct对象*/
        CarProduct carProduct = carProductService.getCarProduct(id);

        List<ChillCar> chillCarList = chillCarService.queryAllChillCar();
        request.setAttribute("chillCarList", chillCarList);
        List<Product> productList = productService.queryAllProduct();
        request.setAttribute("productList", productList);
        request.setAttribute("carProduct",  carProduct);
        return "CarProduct/carProduct_frontshow";
	}

	/*ajax方式显示冷藏车商品修改jsp视图页*/
	@RequestMapping(value="/{id}/update",method=RequestMethod.GET)
	public void update(@PathVariable Integer id,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
        /*根据主键id获取CarProduct对象*/
        CarProduct carProduct = carProductService.getCarProduct(id);

        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONObject jsonCarProduct = carProduct.getJsonObject();
		out.println(jsonCarProduct.toString());
		out.flush();
		out.close();
	}

	/*ajax方式更新冷藏车商品信息*/
	@RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	public void update(@Validated CarProduct carProduct, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
    	boolean success = false;
		if (br.hasErrors()) { 
			message = "输入的信息有错误！";
			writeJsonResponse(response, success, message);
			return;
		}
		try {
			carProductService.updateCarProduct(carProduct);
			message = "冷藏车商品更新成功!";
			success = true;
			writeJsonResponse(response, success, message);
		} catch (Exception e) {
			e.printStackTrace();
			message = "冷藏车商品更新失败!";
			writeJsonResponse(response, success, message); 
		}
	}
    /*删除冷藏车商品信息*/
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Integer id,HttpServletRequest request) throws UnsupportedEncodingException {
		  try {
			  carProductService.deleteCarProduct(id);
	            request.setAttribute("message", "冷藏车商品删除成功!");
	            return "message";
	        } catch (Exception e) { 
	            e.printStackTrace();
	            request.setAttribute("error", "冷藏车商品删除失败!");
				return "error";

	        }

	}

	/*ajax方式删除多条冷藏车商品记录*/
	@RequestMapping(value="/deletes",method=RequestMethod.POST)
	public void delete(String ids,HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
		String message = "";
    	boolean success = false;
        try { 
        	int count = carProductService.deleteCarProducts(ids);
        	success = true;
        	message = count + "条记录删除成功";
        	writeJsonResponse(response, success, message);
        } catch (Exception e) { 
            //e.printStackTrace();
            message = "有记录存在外键约束,删除失败";
            writeJsonResponse(response, success, message);
        }
	}

	/*按照查询条件导出冷藏车商品信息到Excel*/
	@RequestMapping(value = { "/OutToExcel" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void OutToExcel(@ModelAttribute("productObj") Product productObj,@ModelAttribute("carObj") ChillCar carObj, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
        List<CarProduct> carProductList = carProductService.queryCarProduct(productObj,carObj);
        ExportExcelUtil ex = new ExportExcelUtil();
        String _title = "CarProduct信息记录"; 
        String[] headers = { "记录id","产品名称","所在冷藏车","产品数量"};
        List<String[]> dataset = new ArrayList<String[]>(); 
        for(int i=0;i<carProductList.size();i++) {
        	CarProduct carProduct = carProductList.get(i); 
        	dataset.add(new String[]{carProduct.getId() + "",carProduct.getProductObj().getProductName(),carProduct.getCarObj().getCarNo(),carProduct.getProductNum() + ""});
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
			response.setHeader("Content-disposition","attachment; filename="+"CarProduct.xls");//filename是下载的xls的名，建议最好用英文 
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
