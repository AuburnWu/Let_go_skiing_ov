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
import article.dao.ArticleLikeDao;
import article.dao.ArticleTypeDao;
import article.dao.CommentDao;
import article.dao.ReportDao;
import article.dao.impl.ArticleDaoImpl;
import article.dao.impl.ArticleLikeDaoImpl;
import article.dao.impl.ArticleTypeDaoImpl;
import article.dao.impl.CommentDaoImpl;
import article.dao.impl.ReportDaoImpl;
import article.vo.Article;
import article.vo.ArticleLike;
import article.vo.ArticleType;
import article.vo.Comment;
import article.vo.Report;
import member.dao.MemberDao;
import member.dao.impl.MemberDaoImpl;
import member.vo.Member;

//http://localhost:8080/ski/BackendArticle
@WebServlet("/FrontendArticle")
public class FrontendArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        String action=req.getParameter("action");
        String type=req.getParameter("type");
        String artitcleStatus=req.getParameter("artitcleStatus");
//        System.out.println(artitcleStatus);
        String fowardPath="";
        switch(action) {
        	case "showAll":
        		System.out.println("showAll");
        		fowardPath=showAll(req, res);
        		break;
        	case "searchTitle":
        		fowardPath=searchTitle(req,res,"2");
        		break;
        	case "searchArticle":
        		fowardPath=searchArticle(req,res);
        		break;
        	case "getMemberUserName":
        		fowardPath=getMemberUserName(req,res);
        		break;
        	case "searchID":
        		fowardPath=searchID(req,res);
        		break;
        	case "searchArticleLikeID":
        		fowardPath=searchArticleLikeID(req,res);
        		break;
        	case "updateStatus":
        		fowardPath=updateStatus(req,res);
        		break;
        	case "updateLike":
        		fowardPath=updateLike(req,res);
        		break;
			case "addReportContent":
				fowardPath=addReportContent(req,res);
				break;
			case "searchArticleTypeID":
				fowardPath=searchArticleTypeID(req,res);
				break;
			case "useTypeIDsearchArticle":
				fowardPath=useTypeIDsearchArticle(req,res);
				break;
			case "addArticle":
				fowardPath=addArticle(req,res);
				break;
			case "showComment":
				fowardPath=showComment(req,res);
				break;			
			case "addComment":
				fowardPath=addComment(req,res);
				break;
			case "updateArticle":
				fowardPath=updateArticle(req,res);
				break;
			case "useUserIDsearchArticle":
				fowardPath=useUserIDsearchArticle(req,res);
				break;
        	default:{
        			System.out.println("收到未知請求");
        			break;
            }
        }
	}

	//顯示全部
	private String showAll (HttpServletRequest req,HttpServletResponse res) throws IOException{
    		res.setContentType("text/html; charset=utf-8");
    		ArticleDao dao = new ArticleDaoImpl();
    		List<Article> list1 = new ArrayList<Article>();
    		System.out.println("顯示成功");
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
//            System.out.println("success");
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
    
    //抓會員的名稱
    private String getMemberUserName(HttpServletRequest req,HttpServletResponse res) throws IOException {
    	res.setContentType("text/html; charset=utf-8");
        MemberDao mDao = new MemberDaoImpl();
        Member member = null; // 新增物件
        try {
            Integer userID = Integer.parseInt(req.getParameter("userID"));
            member = mDao.selectById(userID); // 將結果存到物件
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(member); // 將儲存到的物件轉成json
        res.getWriter().write(jsonStr);
//        System.out.println("success");
        return jsonStr;
    }
    
    //抓文章分類的名稱
	private String searchID(HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setContentType("text/html; charset=utf-8");
		ArticleTypeDao dao = new ArticleTypeDaoImpl();
		ArticleType articleType5 = null; // 新增物件
		try {
			Integer articleType4 = Integer.parseInt(req.getParameter("articleTypeID"));
			articleType5 = dao.selectByArticleTypeID(articleType4); // 將結果存到物件
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String jsonStr = gson.toJson(articleType5); // 將儲存到的物件轉成json
		res.getWriter().write(jsonStr);
//		System.out.println("success");
		return jsonStr;
		
	}
	
	// 搜尋文章
    private String searchArticle(HttpServletRequest req,HttpServletResponse res) throws IOException {
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
    
    //用文章ID找like的ID
    private String searchArticleLikeID(HttpServletRequest req,HttpServletResponse res) throws IOException {
    	res.setContentType("text/html; charset=utf-8");
        ArticleLikeDao dao = new ArticleLikeDaoImpl();
        ArticleLike articlelike = null; // 新增物件
//        List<ArticleLike> list = new ArrayList<ArticleLike>();
        try {
            Integer articleID = Integer.parseInt(req.getParameter("articleID"));
            articlelike = dao.selectByArticleID(articleID); // 將結果存到物件
        } catch (Exception e) {
            e.printStackTrace();
        }
    	 Gson gson = new Gson();
         String jsonStr = gson.toJson(articlelike); // 將儲存到的物件轉成json
         res.getWriter().write(jsonStr);
//         System.out.println("success");
         return jsonStr;
    }
    
    // 修改狀態
    private String updateStatus(HttpServletRequest req,HttpServletResponse res) throws IOException {
        res.setContentType("text/html; charset=utf-8");
        ArticleLikeDao lDao = new ArticleLikeDaoImpl();
//        List<ArticleLike> list1 = new ArrayList<ArticleLike>();
        try {

        	ArticleLike articleLike2 = new ArticleLike(Integer.parseInt(req.getParameter("articleLikeID")),Integer.parseInt(req.getParameter("articleID")), Integer.parseInt(req.getParameter("userID")),req.getParameter("articleLikeStatus"));
            lDao.updateByArticleLikeID(articleLike2);
//            list1 = lDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(lDao);
        res.getWriter().write(jsonStr);
//        System.out.println("success");
        return jsonStr;
    }
    
    // 修改讚數
    private String updateLike(HttpServletRequest req,HttpServletResponse res) throws IOException {
        res.setContentType("text/html; charset=utf-8");
        ArticleDao dao = new ArticleDaoImpl();
//        List<ArticleLike> list1 = new ArrayList<ArticleLike>();
        try {

        	Article article2 = new Article(Integer.parseInt(req.getParameter("articleID")),Integer.parseInt(req.getParameter("userID")), Integer.parseInt(req.getParameter("articleTypeID")),req.getParameter("articleTitle"),req.getParameter("articleContent"), req.getParameter("articleDateTime"),req.getParameter("articleModified"),Integer.parseInt(req.getParameter("articleLike")),"0");
            dao.updateByArticleID(article2);
//            list1 = lDao.selectAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String jsonStr = gson.toJson(dao);
        res.getWriter().write(jsonStr);
//        System.out.println("success");
        return jsonStr;
    }
    
	// 新增檢舉
	private String addReportContent(HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setContentType("text/html; charset=utf-8");
		ReportDao dao = new ReportDaoImpl();
		try {
			Report report = new Report(null, Integer.parseInt(req.getParameter("articleID")), Integer.parseInt(req.getParameter("userID")),req.getParameter("reportContent"),"0","");
			dao.insert(report);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String jsonStr = gson.toJson(dao);
		res.getWriter().write(jsonStr);
//		System.out.println("success");
		return jsonStr;
	}
    
	// 搜尋文章分類ID
	private String searchArticleTypeID(HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setContentType("text/html; charset=utf-8");
		ArticleTypeDao dao = new ArticleTypeDaoImpl();
		List<ArticleType> list3 = new ArrayList<ArticleType>();
		try {
			String articleType5 = req.getParameter("articleTypeContent");
			list3 = dao.selectByArticleTypeContentRepeat(articleType5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list3);
		res.getWriter().write(jsonStr);
//		System.out.println("success");
		return jsonStr;
	}
	
	//用文章分類ID查詢文章
	private String useTypeIDsearchArticle(HttpServletRequest req,HttpServletResponse res) throws IOException {
		res.setContentType("text/html; charset=utf-8");
		ArticleDao dao = new ArticleDaoImpl();
		List<Article> list3 = new ArrayList<Article>();
		try {
			Integer article5 = Integer.parseInt(req.getParameter("articleTypeID"));
			list3 = dao.selectByArticleTypeID(article5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list3);
		res.getWriter().write(jsonStr);
//		System.out.println("success");
		return jsonStr;
	}
	
		// 新增文章
		private String addArticle(HttpServletRequest req,HttpServletResponse res) throws IOException {
			res.setContentType("text/html; charset=utf-8");
			ArticleDao dao = new ArticleDaoImpl();
			int article6 = 0; // 新增物件
			try {
				Article article1 = new Article(null,Integer.parseInt(req.getParameter("userID")), Integer.parseInt(req.getParameter("articleTypeID")),req.getParameter("articleTitle"),req.getParameter("articleContent"), req.getParameter("articleDateTime"),req.getParameter("articleModified"),Integer.parseInt(req.getParameter("articleLike")),"0");
				article6 = dao.insert(article1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Gson gson = new Gson();
			String jsonStr = gson.toJson(article6);
			res.getWriter().write(jsonStr);
//			System.out.println("success");
			return jsonStr;
		}
	
		// 顯示留言
		private String showComment (HttpServletRequest req,HttpServletResponse res) throws IOException{
	    		res.setContentType("text/html; charset=utf-8");
	    		CommentDao dao = new CommentDaoImpl();
	    		List<Comment> list1 = new ArrayList<Comment>();
	    		System.out.println("顯示成功");
	    		list1 = dao.selectByComment(Integer.parseInt(req.getParameter("articleID")));
//				System.out.println(list1);
	            Gson gson = new Gson();
	            String jsonStr = gson.toJson(list1);
	            res.getWriter().write(jsonStr);
//	            System.out.println("success");
	            return jsonStr;
		}
		
		// 新增留言
		private String addComment(HttpServletRequest req,HttpServletResponse res) throws IOException {
			res.setContentType("text/html; charset=utf-8");
			CommentDao dao = new CommentDaoImpl();
			int comment5 = 0; // 新增物件
			try {
				Comment comment1 = new Comment(null,
						Integer.parseInt(req.getParameter("articleID")),
						Integer.parseInt(req.getParameter("userID")),
						req.getParameter("commentContent"),
						req.getParameter("commentDateTime"),
						req.getParameter("commentModified"),
						Integer.parseInt(req.getParameter("commentLike")));
				comment5 = dao.insert(comment1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Gson gson = new Gson();
			String jsonStr = gson.toJson(comment5);
			res.getWriter().write(jsonStr);
//			System.out.println("success");
			return jsonStr;
		}
		
		// 修改文章
		private String updateArticle(HttpServletRequest req,HttpServletResponse res) throws IOException {
			res.setContentType("text/html; charset=utf-8");
			ArticleDao dao = new ArticleDaoImpl();
			int article6 = 0; // 新增物件
			try {
				Article article1 = new Article(Integer.parseInt(req.getParameter("articleID")),Integer.parseInt(req.getParameter("userID")), Integer.parseInt(req.getParameter("articleTypeID")),req.getParameter("articleTitle"),req.getParameter("articleContent"), req.getParameter("articleDateTime"),req.getParameter("articleModified"),Integer.parseInt(req.getParameter("articleLike")),"0");
				article6 = dao.updateByArticleID(article1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Gson gson = new Gson();
			String jsonStr = gson.toJson(article6);
			res.getWriter().write(jsonStr);
//			System.out.println("success");
			return jsonStr;
		}
		
		//用使用者ID查詢文章
		private String useUserIDsearchArticle(HttpServletRequest req,HttpServletResponse res) throws IOException {
			res.setContentType("text/html; charset=utf-8");
			ArticleDao dao = new ArticleDaoImpl();
			List<Article> list3 = new ArrayList<Article>();
			try {
				Integer article5 = Integer.parseInt(req.getParameter("userID"));
				list3 = dao.selectByuserID(article5);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Gson gson = new Gson();
			String jsonStr = gson.toJson(list3);
			res.getWriter().write(jsonStr);
//			System.out.println("success");
			return jsonStr;
		}
    
    
	
}
