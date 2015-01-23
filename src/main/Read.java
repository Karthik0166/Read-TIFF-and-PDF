package main;


import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

public class Read {

	public void readTif()
	{
		try {
			String folderName="D:/work/Daniel/Frio-CD-OPR-149/Frio-CD-OPR-149";
			String[] name=folderName.split("/");
			File folder = new File(folderName);
			File[] listOfFiles = folder.listFiles();
			int batch=5;
			int batchCheck=5;
			int fileNum=1;
			String fileName="";		
			String nameFlag="";
			for (File file : listOfFiles) {
			    if (file.isFile()) {
			        if(file.getName().contains(".TIF"))
			        {			    
			        	File source =new File(folderName+"/"+file.getName());
			        	if(batch==batchCheck)
			        	{
			        		fileName="D:/work/Daniel/JavaFolder/"+name[name.length-1]+"-"+fileNum;
			        		File newFolder=new File(fileName);
			        		if(!newFolder.exists()) 
			        		{
			        			System.out.println(newFolder.mkdirs());
			        		}			        		
			        		FileUtils.copyFile(source, new File(fileName+"/"+file.getName()));
			        		fileNum++;
			        		if(!nameFlag.equalsIgnoreCase(file.getName().split("\\.")[0]))
			        		{
			        			batchCheck--;
			        		}
			        	}
			        	else{
			        		FileUtils.copyFile(source, new File(fileName+"/"+file.getName()));
			        		if(!nameFlag.equalsIgnoreCase(file.getName().split("\\.")[0]))
			        		{
			        			batchCheck--;
			        		}
			        	}
			        	if(batchCheck==0)
		        		{
		        			batchCheck=5;
		        		}
			        	nameFlag=file.getName().split("\\.")[0];
			        }
			    }
			}
			
			
		} catch (Exception e) {
			System.out.println("The exception is :"+e);
		}
	}
	
	public void readPdf() throws IOException
	{
		String folderName="D:/work/Daniel/pdf test/actualName";
		String[] name=folderName.split("/");
		File folder = new File(folderName);
		File[] listOfFiles = folder.listFiles();
		String destination="D:/work/Daniel/pdf test/pdfOutput";
		int batch=3;
		int batchCheck=batch;
		int fileNum=1;
		String fileNameFlag="";
		for (File file1 : listOfFiles) {
		    if (file1.isFile()) {
		        if(file1.getName().contains(".PDF"))
		        {			    
		        	 String FILEPATH = folderName+"/"+file1.getName();
			   	     String[] pdfName=file1.getName().split("\\.");
			   	     if(batch==batchCheck)
			   	     {
			   	    	 fileNameFlag=destination+"/"+name[name.length-1]+"-"+fileNum;
			   	    	 File actualFile=new File(fileNameFlag);
			   	    	 if(!actualFile.exists())
			   	    	 {
			   	    		 actualFile.mkdirs();
			   	    	 }
			   	    	 fileNum++;
			   	     }
		   	     
		   	          Document document = new Document();
		   	          try {
		   	             document.setFile(FILEPATH);
		   	          } catch (Exception ex) {
		   	             System.out.println("Error parsing PDF document " + ex);
		   	          } 
		   	          float scale = 1.0f;
		   	          float rotation = 0f;
		   	          for (int i = 0; i < document.getNumberOfPages(); i++) {
		   	        	  int flag=i+1;
		   	             BufferedImage image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.PRINT, Page.BOUNDARY_CROPBOX, rotation, scale);
		   	             RenderedImage rendImage = image;
		   	             try {
		   	            	 File file = null;
		   	            	 if(i>=10 && i<=99)
		   	            	 {
		   	            		  file = new File(fileNameFlag+"/"+pdfName[0]+".0" + flag + ".tif");
		   	            	 }
		   	            	 else if(i<10)
		   	            	 {
		   	            		  file = new File(fileNameFlag+"/"+pdfName[0]+".00" + flag + ".tif");
		   	            	 }	   
		   	            	 else if(i>99)
		   	            	 {
		   	            		file = new File(fileNameFlag+"/"+pdfName[0]+"." + flag + ".tif");
		   	            	 }
		   	                ImageIO.write(rendImage, "tiff", file);
		   	             } catch (IOException e) {
		   	                e.printStackTrace();
		   	             }
		   	             image.flush();
		   	          }
		   	          document.dispose();
		   	          batchCheck--;
		   	       if(batchCheck==0)
	        		{
	        			batchCheck=batch;
	        		}
		        }
		    }
		}
		
		 
		
	    	
	}
}
