package model.bo;

import java.sql.SQLException;
import java.util.List;

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

}
