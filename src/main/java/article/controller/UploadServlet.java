package article.controller;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2, // 設定檔案大小的門檻值，超過這個大小的檔案會被寫入到磁碟
	    maxFileSize = 1024 * 1024 * 10, // 設定單個檔案的最大大小
	    maxRequestSize = 1024 * 1024 * 50, // 設定每個 request 的最大大小
	    location = "C:/articleImg" // 設定檔案儲存的位置
	)
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 取得圖片檔案的 Part 對象
        Part imagePart = request.getPart("imageFile");
        
        // 取得圖片檔案的名稱
        String fileName = getFileName(imagePart);
        
        // 檢查 C 槽是否有 articleImg 資料夾，如果沒有則創建
        String folderPath = "C:/articleImg";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        
        // 將圖片儲存到指定資料夾中
        String filePath = folderPath + "/" + fileName;
        imagePart.write(filePath);
        
        // 在這裡可以根據需要進行其他的處理，例如資料庫儲存等
        
        response.getWriter().write("圖片上傳成功");
    }
    
    // 從 Part 中取得檔案名稱
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] parts = contentDisposition.split(";");

        for (String partInfo : parts) {
            if (partInfo.trim().startsWith("filename")) {
                return partInfo.substring(partInfo.indexOf("=") + 1).trim().replace("\"", "");
            }
        }

        return "";
    }
}
