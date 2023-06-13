package loc.controller;

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

import loc.model.SkiLocation;
import loc.service.SkiLocationService;

@WebServlet("/loc/backend_addmap")
@MultipartConfig
public class SkiLocationInsertServlet extends HttpServlet{
//	SkiLocationService skiLocationService = new SkiLocationService();	
	private static final long serialVersionUID = 1L;
	private SkiLocationService skiLocationService;
	
    public void init() throws ServletException {
        // 在 init 方法中初始化 Service 層
        skiLocationService = new SkiLocationService();
    }
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
	String name = request.getParameter("name");
	String area = request.getParameter("area");
	Double latitude = Double.valueOf(request.getParameter("latitude"));
	Double longitude =Double.valueOf(request.getParameter("longitude")) ;
	String info = request.getParameter("info");
	Integer rating = Integer.parseInt(request.getParameter("rating"));
	Integer starMonth = Integer.parseInt(request.getParameter("starMonth"));
	Integer endMonth = Integer.parseInt(request.getParameter("endMonth"));
	String skiView = request.getParameter("skiView");
//	byte[] skiImage = request.getParameter("skiImage").getBytes();
	
	// 處理圖片上傳
    Part imagePart = request.getPart("skiImage");
    byte[] skiImage = null;
    if (imagePart != null) {
        InputStream imageInputStream = imagePart.getInputStream();
        skiImage = convertInputStreamToByteArray(imageInputStream);
    }

	SkiLocation skiLocation = new SkiLocation();
	skiLocation.setPointName(name);
	skiLocation.setLongitude(longitude);
	skiLocation.setLatitude(latitude);
	skiLocation.setPointArea(area);
	skiLocation.setPointView(skiView);
	skiLocation.setPointInfo(info);
	skiLocation.setStarMonth(starMonth);
	skiLocation.setEndMonth(endMonth);
	skiLocation.setPointPicture(skiImage);
	skiLocation.setPointRating(rating);
	skiLocation.setPointStatus("下架中");
	
	skiLocationService.addMap(skiLocation);
	
	
	
	}
    private byte[] convertInputStreamToByteArray(InputStream inputStream) throws IOException {
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
            int nRead;
            byte[] data = new byte[8000000];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            return buffer.toByteArray();
        }
    }
}
