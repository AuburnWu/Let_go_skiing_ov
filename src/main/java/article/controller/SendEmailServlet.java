package article.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Properties;

import com.google.gson.Gson;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.vo.Member;



@WebServlet("/SendEmailServlet")
public class SendEmailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
//    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        doPost(req, res);
//    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	try {
 		   // 設定使用SSL連線至 Gmail smtp Server
 		   Properties props = new Properties();
 		   props.put("mail.smtp.host", "smtp.gmail.com");
 		   props.put("mail.smtp.socketFactory.port", "465");
 		   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
 		   props.put("mail.smtp.auth", "true");
 		   props.put("mail.smtp.port", "465");

        // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
        // ●1) 登入你的Gmail的: 
        // ●2) 點選【管理你的 Google 帳戶】
        // ●3) 點選左側的【安全性】
        
        // ●4) 完成【兩步驟驗證】的所有要求如下:
        //     ●4-1) (請自行依照步驟要求操作之.....)
        
        // ●5) 完成【應用程式密碼】的所有要求如下:
        //     ●5-1) 下拉式選單【選取應用程式】--> 選取【郵件】
        //     ●5-2) 下拉式選單【選取裝置】--> 選取【Windows 電腦】
        //     ●5-3) 最後按【產生】密碼
 	     final String myGmail = "helpMeStartALiveStreamingRrr@gmail.com";
 	     final String myGmail_password = "jwjzpochmugvdtny";
 		   Session session = Session.getInstance(props, new Authenticator() {
 			   protected PasswordAuthentication getPasswordAuthentication() {
 				   return new PasswordAuthentication(myGmail, myGmail_password);
 			   }
 		   });
 		   String to = req.getParameter("email");
 		   Message message = new MimeMessage(session);
 		   message.setFrom(new InternetAddress(myGmail));
// 		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("a0987869583@gmail.com"));
 		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse((to)));
 		  
 		   //設定信中的主旨  
 		   message.setSubject(req.getParameter("subject"));
 		   //設定信中的內容 
 		   message.setText(req.getParameter("message"));
 		   System.out.println("--------------------");
 		   System.out.println(req.getParameter("email"));
 		   System.out.println("--------------------");
 		   System.out.println(req.getParameter("subject"));
 		   System.out.println(req.getParameter("message"));
 		   
 		   Transport.send(message);
 		   System.out.println("傳送成功!");
      }catch (MessagingException e){
 	     System.out.println("傳送失敗!");
 	     e.printStackTrace();
      }
    	
//    	resp.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自所有網域的請求
//		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // 允許的 HTTP 方法
//		resp.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 允許的請求頭
//		resp.setHeader("Access-Control-Allow-Credentials", "true"); // 是否允許帶有憑證的請求
//
//		// 設置中文編碼
//		req.setCharacterEncoding("UTF-8");
//		resp.setContentType("application/json;charset=UTF-8");
//
//		// 從 HttpServletRequest 中取得 JSON 字串
//		BufferedReader br = req.getReader();
//		Gson gson = new Gson();
//		Member member = gson.fromJson(br, Member.class);
//
//		member = service.forgotPassword(member);
//
//		if (member == null) {
//			member = new Member();
//			member.setMessage("無會員資訊");
//			member.setSuccessful(false);
//			String memberJson = gson.toJson(member);
//			resp.getWriter().write(memberJson);
//			resp.getWriter().flush();
//			return;
//		}
//		
//		
//		String userID = String.valueOf(member.getUserID());
//		String encodedUserID = Base64Util.encode(userID);
//		
//		String resetPasswordUrl = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
//        + req.getContextPath() + "/member/passwordChange?userID=" + encodedUserID + "&from=mail";
//
//		
//		String to = member.getEmail(); 
//		String subject = "【忘記密碼通知】Let's go skiing";
//		String ch_name = member.getUserName();
//		
//		 String messageText = "Hello " + ch_name + "，您好：\r\n\r\n"
//		            + "您申請了忘記密碼通知，如果您沒有申請，請忽略這封信件。\r\n"
//		            + "如果您要重設密碼，請點選下方連結：\r\n\r\n"
//		            + resetPasswordUrl
//		            + "\r\n\r\n"
//		            + "若有任何問題歡迎隨時回覆此郵件與我們聯繫，謝謝。\r\n"
//		            + "Let's go skiing 會員中心 "; 
//
//		service.sendMail(to, subject, messageText);
//		
//		member.setMessage("郵件已成功發送！");
//		member.setSuccessful(true);
//		String memberJson = gson.toJson(member);
//		resp.getWriter().write(memberJson);
//		resp.getWriter().flush();
    	
    	
    	
//    	System.out.println("Rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr1");
//        String recipientEmail = request.getParameter("email");
//        String subject = request.getParameter("subject");
//        String message = request.getParameter("message");
//
//        // 發送郵件的SMTP設定
//        String host = "smtp.gmail.com";
//        String port = "587";
//        String username = "helpMeStartALiveStreamingRrr";// 你的Gmail帳號
//        String password = "helpMeStartALiveStreamingRrr";// 你的Gmail密碼
//
//        // 設定郵件屬性
//        Properties properties = new Properties();
//        properties.put("mail.smtp.host", host);
//        properties.put("mail.smtp.port", port);
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//
//        // 建立驗證器
//        Authenticator authenticator = new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        };
//
//        // 建立郵件會話
//        Session session = Session.getInstance(properties, authenticator);
//
//        try {
//            // 建立郵件訊息
//            MimeMessage mimeMessage = new MimeMessage(session);
//            mimeMessage.setFrom(new InternetAddress(username));
//            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
//            mimeMessage.setSubject(subject);
//            mimeMessage.setText(message);
//
//            // 發送郵件
//            Transport.send(mimeMessage);
//
//            response.getWriter().println("郵件已成功發送！");
//            System.out.println("Rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
//        } catch (MessagingException e) {
//            response.getWriter().println("發送郵件時發生錯誤: " + e.getMessage());
//        }
    }
}
