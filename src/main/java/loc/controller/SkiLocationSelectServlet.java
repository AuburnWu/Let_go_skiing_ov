package loc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import loc.model.SkiLocation;
import loc.service.SkiLocationService;



@WebServlet("/loc/backend_selectmap")
public class SkiLocationSelectServlet extends HttpServlet {
	private SkiLocationService skiLocationService;
	
	  @Override
	    public void init() throws ServletException {
	        skiLocationService = new SkiLocationService();
	        
	    }
	  
	  
	  @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  System.out.println("ss");
		  List<SkiLocation> skiLocation = skiLocationService.selectAllMap();
		 

	        // 將List轉換為JSON格式
	        Gson gson = new Gson();
	        String json = gson.toJson(skiLocation);
//	        System.out.println(json);

	        // 設置回應的Content-Type為application/json
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");

	        // 將JSON資料寫入回應中
	        PrintWriter out = response.getWriter();
	        out.write(json);
	        out.flush();
	    }
	

}
