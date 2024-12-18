package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.websocket.Session;
import javax.servlet.annotation.MultipartConfig;

import model.bean.Documents;
import model.bo.*;
import service.DocumentService;
import util.QueueManager;

@WebServlet("/upload")
@MultipartConfig
public class DocumentUploadServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
       
        	request.getRequestDispatcher("upload.jsp").forward(request, response);
        
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DocumentService service = new DocumentService();
		service.start();
		
		 Part[] files = request.getParts().toArray(new Part[0]);
		 for (Part filePart : files) {
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uploadDir = getServletContext().getRealPath("/uploads");
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdir();
        }
        String filePath = uploadDir + File.separator + fileName;
        System.out.println(filePath);
        filePart.write(filePath);
        Documents doc = new Documents();
        HttpSession session = request.getSession();
        int userid = (int) session.getAttribute("userid");
        System.out.println(userid);
        doc.setUserId(userid);
        doc.setInputPath(filePath);
        doc.setStatus("pending");
        doc.setOutputPath("");
        doc.setFileName(fileName);
        DocumentBO docBO = new DocumentBO();
        int docID = docBO.saveDocument(doc);
        System.out.println("luu file: "+ docID);
        doc.setId(docID);
        QueueManager.addDocument(doc);

		 }
		  response.sendRedirect("ResultServlet");
	}

}