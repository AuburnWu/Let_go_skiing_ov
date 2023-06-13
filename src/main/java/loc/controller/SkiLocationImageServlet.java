package loc.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import loc.model.SkiLocation;
import loc.service.SkiLocationService;

@WebServlet("/loc/backend_upload_image")
@MultipartConfig
public class SkiLocationImageServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
	private SkiLocationService service;

	public void init() {
		service = new SkiLocationService();
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("aaa");
        
		String pointID = request.getParameter("pointID");
		Part imagePart = request.getPart("Image");
		System.out.println(pointID);
		System.out.println(imagePart);
		    byte[] Image = null;
		    if (imagePart != null) {
		        InputStream imageInputStream = imagePart.getInputStream();
		        Image = convertInputStreamToByteArray(imageInputStream);
		    }
		    
		    SkiLocation skiLocation = new SkiLocation();
			skiLocation.setPointID(Integer.parseInt(pointID));
			skiLocation.setPointPicture(Image);
			if (service.updateImage(skiLocation)) {
			    // 返回成功的JSON响应
			    out.print("{\"status\": \"success\"}");
			} else {
			    // 返回失败的JSON响应
			    out.print("{\"status\": \"failure\"}");
			}
			    
			    out.flush();
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
