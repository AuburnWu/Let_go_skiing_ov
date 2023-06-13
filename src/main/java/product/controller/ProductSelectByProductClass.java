package product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import product.dao.ProductDaoImpl;
import product.service.product.ProductService;
import product.vo.Product;


@WebServlet("/productSelectByClass")
public class ProductSelectByProductClass extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService = new ProductService();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");

        String productClass = request.getParameter("productClass");

        

        List<Product> productList = productService.selectByProductClass(productClass);

        Gson gson = new Gson();
        String json = gson.toJson(productList);

        response.setContentType("application/json;charset=utf-8");

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}






