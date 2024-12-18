package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Documents;
import model.bo.DocumentBO;

@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String id = request.getParameter("id");
        String delID = request.getParameter("deleteID");
        if(delID != null)
        {		
        	 
        	  DocumentBO docBO = new DocumentBO();
              try {
				docBO.deleteDocument(Integer.parseInt(delID));
			} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              response.sendRedirect("ResultServlet");
        }
        else if (id != null) {
            try {
               
                DocumentBO docBO = new DocumentBO();
                Documents doc = docBO.getDocumentbyID(Integer.parseInt(id));
                
                
                if (doc != null && doc.getStatus().equals("Completed")) {
                    File file = new File(doc.getOutputPath()); 
                    if (file.exists()) {
                        
                        String encodedFileName = URLEncoder.encode(file.getName(), "UTF-8").replaceAll("\\+", "%20");
                        
                        
                        response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                        response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");
                        response.setContentLength((int) file.length());
                     
                        try (FileInputStream fileInputStream = new FileInputStream(file);
                             OutputStream outputStream = response.getOutputStream()) {
                            byte[] buffer = new byte[4096];
                            int bytesRead;
                            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                                outputStream.write(buffer, 0, bytesRead);
                            }
                        }
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found.");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Document not found or not completed.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing the download.");
            }
		
        }
        else        	
        {
        	DocumentBO docBO = new DocumentBO();
    		HttpSession session = request.getSession();
    		int userid = (int) session.getAttribute("userid");
    		List<Documents> listdoc = docBO.getDocumentforUsers(userid);
    		for(Documents i : listdoc)
    		{
    			System.out.println(i.getFileName());
    		}
    		request.setAttribute("listdoc", listdoc);
    		request.getRequestDispatcher("result.jsp").forward(request, response);
        	
        }
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
