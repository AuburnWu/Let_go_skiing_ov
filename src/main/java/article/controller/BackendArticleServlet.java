package article.controller;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import article.dao.ArticleDao;
import article.dao.impl.ArticleDaoImpl;
import article.vo.Article;

//http://localhost:8080/ski/BackendArticle
@WebServlet("/BackendArticle")
public class BackendArticleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String action=req.getParameter("action");
        String type=req.getParameter("type");
        String artitcleStatus=req.getParameter("artitcleStatus");
//        System.out.println(artitcleStatus);
        String fowardPath="";
        switch(action) {
            case "showAll":
//                System.out.println("showAll1");
                fowardPath=showAll(req,res);
                break;
            case "searchIDAndTitle":
//                System.out.println("searchIDAndTitle");
                if(type.equals("Number")) {
                    fowardPath=searchID(req,res,1);
                }else if(type.equals("String")) {
                    fowardPath=searchTitle(req,res,"2");
                }else if(type.equals("上下架")) {
                	fowardPath=searchStatus(req,res,"3");
                }
                break;
            case "updateStatus1":
//                System.out.println("updateStatus1");
                fowardPath=updateStatus1(req,res);
//                System.out.println("updateStatus1");
                break;
            case "updateStatus0":
//                System.out.println("updateStatus0");
                fowardPath=updateStatus0(req,res);
//                System.out.println("updateStatus0");
                break;
            default:{
                System.out.println("收到未知請求");
                break;
            }
        }
    }

    // 顯示全部
    private String showAll(HttpServletRequest req,HttpServletResponse res) throws IOException {
        res.setContentType("text/html; charset=utf-8");
        ArticleDao dao = new ArticleDaoImpl();
        List<Article> list1 = new ArrayList<Article>();
        System.out.println("顯示全部!");
        try {
            list1 = dao.selectAll();
            System.out.println(list1);
        } catch (ClassNotFoundException e) {
            System.out.println("顯示錯誤!");
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(list1);
        res.getWriter().write(jsonStr);
//        System.out.println("success");
        return jsonStr;
    }


    // 搜尋ID
    private String searchID(HttpServletRequest req,HttpServletResponse res ,Integer type) throws IOException {
        res.setContentType("text/html; charset=utf-8");
        ArticleDao dao = new ArticleDaoImpl();
        Article article5 = null; // 新增物件
        try {
            Integer article4 = Integer.parseInt(req.getParameter("articleID"));
            article5 = dao.selectByArticleID(article4); // 將結果存到物件
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(article5); // 將儲存到的物件轉成json
//		res.setContentType("application/json");
//		res.setCharacterEncoding("UTF-8");
        res.getWriter().write(jsonStr);
//        System.out.println("success");
        return jsonStr;

    }


    // 搜尋內容
    private String searchTitle(HttpServletRequest req,HttpServletResponse res ,String type) throws IOException {
        res.setContentType("text/html; charset=utf-8");
        ArticleDao dao = new ArticleDaoImpl();
        List<Article> list3 = new ArrayList<Article>();
        try {
            String article5 = req.getParameter("articleTitle");
            list3 = dao.selectByArticleTitle(article5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(list3);
        res.getWriter().write(jsonStr);
//        System.out.println("success");
        return jsonStr;
    }
    
 // 搜尋狀態
    private String searchStatus(HttpServletRequest req,HttpServletResponse res ,String type) throws IOException {
        res.setContentType("text/html; charset=utf-8");
        ArticleDao dao = new ArticleDaoImpl();
        List<Article> list3 = new ArrayList<Article>();
        try {
            String article5 = req.getParameter("articleStatus");
            list3 = dao.selectByArticleStatus(article5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(list3);
        res.getWriter().write(jsonStr);
//        System.out.println("success");
        return jsonStr;
    }

    // 修改狀態為1(下架文章)
    private String updateStatus1(HttpServletRequest req,HttpServletResponse res) throws IOException {
        res.setContentType("text/html; charset=utf-8");
        ArticleDao dao = new ArticleDaoImpl();
        List<Article> list1 = new ArrayList<Article>();
        try {

            Article article2 = new Article(Integer.parseInt(req.getParameter("articleID")),Integer.parseInt(req.getParameter("userID")), Integer.parseInt(req.getParameter("articleTypeID")),req.getParameter("articleTitle"),req.getParameter("articleContent"), req.getParameter("articleDateTime"),req.getParameter("articleModified"),Integer.parseInt(req.getParameter("articleLike")),"1");
            dao.updateByArticleID(article2);
            list1 = dao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(list1);
        res.getWriter().write(jsonStr);
//        System.out.println("success");
        return jsonStr;
    }

    // 修改狀態為0(重新上架文章)
    private String updateStatus0(HttpServletRequest req,HttpServletResponse res) throws IOException {
        res.setContentType("text/html; charset=utf-8");
//        Date date = new Date();
//        Timestamp tsDateTime=new Timestamp(date.getTime());
//        Timestamp tsModified=new Timestamp(date.getTime());
//        tsDateTime = Timestamp.valueOf(req.getParameter("articleDateTime"));
//        tsModified = Timestamp.valueOf(req.getParameter("articleModified"));
        ArticleDao dao = new ArticleDaoImpl();
        List<Article> list1 = new ArrayList<Article>();
        try {
//        	System.out.println("-------------------------------------------------");
//        	System.out.println(req.getParameter("articleID"));
//        	System.out.println(req.getParameter("userID"));
//        	System.out.println(req.getParameter("articleTypeID"));
//        	System.out.println(req.getParameter("articleTitle"));
//        	System.out.println(req.getParameter("articleContent"));
//        	System.out.println(req.getParameter("articleDateTime"));
//        	System.out.println(req.getParameter("articleModified"));
//        	System.out.println(req.getParameter("articleLike"));
//        	System.out.println(req.getParameter("articleStatus"));
//        	System.out.println("-------------------------------------------------");
//        	String articleTitle = String.valueOf(req.getParameter("articleTitle"));
            Article article2 = new Article(Integer.parseInt(req.getParameter("articleID")),Integer.parseInt(req.getParameter("userID")),
            		Integer.parseInt(req.getParameter("articleTypeID")), req.getParameter("articleTitle"),req.getParameter("articleContent"),
            		req.getParameter("articleDateTime"), req.getParameter("articleModified"),
            		Integer.parseInt(req.getParameter("articleLike")),"0");
            dao.updateByArticleID(article2);
            list1 = dao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(list1);
        res.getWriter().write(jsonStr);
//        System.out.println("success");
        return jsonStr;
    }
}
