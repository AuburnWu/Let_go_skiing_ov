package product.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import product.service.product.ProductService;
import product.vo.Product;

@WebServlet("/productAdd")
@MultipartConfig
public class ProductInsertServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	    private ProductService productService = new ProductService();

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("application/json;charset=utf-8");
	        request.setCharacterEncoding("UTF-8");

	        String productClass = request.getParameter("productClass");
	        String productName = request.getParameter("productName");
	        Integer productPrice = parseIntegerParameter(request.getParameter("productPrice"));
	        Integer productQuantity = parseIntegerParameter(request.getParameter("productQuantity"));
	        String productStatus = request.getParameter("productStatus");
	        // 處理圖片上傳
	        Part imagePart = request.getPart("productImage");
	        byte[] productImage = null;
	        if (imagePart != null) {
	            InputStream imageInputStream = imagePart.getInputStream();
	            productImage = convertInputStreamToByteArray(imageInputStream);
	        }
	        String productDetail = request.getParameter("productDetail");

	        Product product = productService.insert(productClass, productName, productPrice, productImage, productDetail, productStatus,productQuantity);

	        JsonObject jsonResponse = new JsonObject();
	        if (product != null) {
	            jsonResponse.addProperty("status", "success");
	            jsonResponse.addProperty("message", "成功");
	        } else {
	            jsonResponse.addProperty("status", "failure");
	            jsonResponse.addProperty("message", "失敗");
	        }

	        Gson gson = new Gson();
	        String jsonString = gson.toJson(jsonResponse);
	        response.getWriter().write(jsonString);
	    }

	    private byte[] convertInputStreamToByteArray(InputStream inputStream) throws IOException {
	        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
	            int nRead;
	            byte[] data = new byte[5000000];
	            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
	                buffer.write(data, 0, nRead);
	            }
	            buffer.flush();
	            return buffer.toByteArray();
	        }
	    }

	    private Integer parseIntegerParameter(String parameter) {
	        if (parameter != null && !parameter.isEmpty()) {
	            try {
	                return Integer.parseInt(parameter);
	            } catch (NumberFormatException e) {
	                e.printStackTrace();
	            }
	        }
	        return null;
	    }

	    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	        doPost(req, res);
	    }
	
}
