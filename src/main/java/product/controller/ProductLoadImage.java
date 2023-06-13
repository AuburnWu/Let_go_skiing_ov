package product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;

import core.HibernateUtil;
import product.dao.ProductDaoImpl;
@WebServlet("/loadImage")
public class ProductLoadImage extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        
        String productIDParam = request.getParameter("productID");
        Integer productID = null;
        if (productIDParam != null && !productIDParam.isEmpty()) {
            productID = Integer.valueOf(productIDParam);
        }

        ProductDaoImpl productDaoImpl = new ProductDaoImpl(); // 创建 ProductDaoImpl 实例

        // 确保 productID 不为 null
        if (productID == null) {
            throw new ServletException("Product ID cannot be null or empty");
        }

        byte[] imageData = productDaoImpl.loadingImage(productID, session); // 使用 session 调用新的 loadingImage 方法

        // 将图片转换成 Base64 编码的字符串
        String base64Image = "";
        if (imageData != null) {
            base64Image = Base64.getEncoder().encodeToString(imageData);
        }

        // 创建 JSON 对象并设置图片数据属性
        JSONObject json = new JSONObject();
        json.put("imageData", base64Image);

        // 设置响应内容类型为 JSON
        response.setContentType("application/json");

        // 获取响应的字符输出流
        PrintWriter out = response.getWriter();

        // 将 JSON 对象转换成字符串并输出到响应
        out.print(json.toString());

        // 关闭输出流
        out.close();

        // Close session
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}