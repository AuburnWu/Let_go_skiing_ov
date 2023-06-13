package product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import product.service.product.ProductService;

@WebServlet("/productDelete")
public class ProductDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService = new ProductService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        Integer productID = Integer.parseInt(request.getParameter("productID"));

        boolean deleteSuccess = productService.deleteProduct(productID);

        JsonObject jsonResponse = new JsonObject();
        if (deleteSuccess) {
            jsonResponse.addProperty("status", "success");
            jsonResponse.addProperty("message", "刪除成功");
        } else {
            jsonResponse.addProperty("status", "failure");
            jsonResponse.addProperty("message", "刪除失敗");
        }

        Gson gson = new Gson();
        String jsonString = gson.toJson(jsonResponse);
        response.getWriter().write(jsonString);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}




