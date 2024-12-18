package model.bo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.bean.Documents;
import model.dao.DocumentDAO;

public class DocumentBO {
	private DocumentDAO documentDAO;
	public DocumentBO()
	
	{
		this.documentDAO = new DocumentDAO();
	}
	public int saveDocument(Documents document)
	{
		return this.documentDAO.saveDocument(document);
	}
	public Documents getDocumentbyID(int id)
	{
		return this.documentDAO.getDocumentbyID(id);
	}
	public List<Documents> getDocumentforUsers(int userid)
	{
		return this.documentDAO.getDocumentbyUserID(userid);
	}
	public void updateDocument(int docId, String status, String outputPath) throws ClassNotFoundException, SQLException
	{
		 this.documentDAO.updateDocument(docId,status,outputPath);
	}
	public void updateStatus(int docID,String status) throws ClassNotFoundException, SQLException
	{
		this.documentDAO.updateStatus(docID, status);
		
	}
	public void deleteDocument(int docID) throws ClassNotFoundException, SQLException
	
	{
		this.documentDAO.deleteDocument(docID);
	}
	public Documents createDocument(HttpServletRequest request, Part filePart) throws IOException {
        Documents doc = new Documents();
        HttpSession session = request.getSession();
        int userid = (int) session.getAttribute("userid");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uploadDir = request.getServletContext().getRealPath("/uploads");
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdir();
        }
        String filePath = uploadDir + File.separator + fileName;
        filePart.write(filePath);
        
        doc.setUserId(userid);
        doc.setInputPath(filePath);
        doc.setStatus("pending");
        doc.setOutputPath("");
        doc.setFileName(fileName);
        
        return doc;
    }

}
