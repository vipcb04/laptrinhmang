package service;

import model.bean.Documents;
import model.bo.DocumentBO;
import util.QueueManager;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.doc.*;
import com.spire.doc.documents.*;
import com.spire.doc.fields.TextRange;
import com.spire.pdf.graphics.PdfImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DocumentService extends Thread {

    @Override
    public void run() {
        while (true) {
            try {
                Documents doc = QueueManager.getnextDocument();
                
                processDocument(doc);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void processDocument(Documents doc) throws Exception {
    	DocumentBO dbo = new DocumentBO();  
    	dbo.updateStatus(doc.getId(), "In Progress");
        File pdfFile = new File(doc.getInputPath());
        PdfDocument pdfDocument = new PdfDocument();
        pdfDocument.loadFromFile(pdfFile.getAbsolutePath());
        pdfDocument.getConvertOptions().setConvertToWordUsingFlow(true);       
        String outputPath = doc.getInputPath().replace(".pdf", ".docx");
        pdfDocument.saveToFile(outputPath,com.spire.pdf.FileFormat.DOCX);     
             
        dbo.updateDocument(doc.getId(), "Completed", outputPath);
        System.out.println("Conversion completed: " + outputPath);    
        pdfDocument.close();
        
    }

    


}

