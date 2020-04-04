package PDFBoxPrintDemo;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.printing.PDFPrintable;


public class PDFBoxPrint_demo {

	public static void main(String[] args) throws FileNotFoundException, IOException, PrinterException {

        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
         
        // this step is necessary because I have several printers configured
        PrintService myPrinter = null;
        for (int i = 0; i < services.length; i++){

            String svcName = services[i].toString();  
            System.out.println("service found: "+svcName);
            if (svcName.contains("printer closest to me")){
                myPrinter = services[i];
                System.out.println("my printer found: "+svcName);
                break;
            }
        }
        myPrinter=services[0]; 
        
        byte[] bpdf = Files.readAllBytes(Paths.get("Label_127075.pdf"));
        
        PDDocument PDFfrombytes = PDDocument.load(bpdf);
        if (myPrinter != null) {            
             
            PrinterJob pjob = PrinterJob.getPrinterJob();
            pjob.setPrintService(myPrinter);
            pjob.setPageable(new PDFPageable(PDFfrombytes));
	
            // custom page format
			PageFormat pf = pjob.defaultPage();
			Paper paper = pf.getPaper(); 
			final int DEFAULT_USER_SPACE_UNIT_DPI = 72;
			final float MM_TO_UNITS = 1/(10*2.54f)*DEFAULT_USER_SPACE_UNIT_DPI;
			float x_uu_=49*MM_TO_UNITS;
			float y_uu_=24*MM_TO_UNITS;
			paper.setSize(x_uu_,y_uu_);
			paper.setImageableArea(0, 0,x_uu_,y_uu_);
			pf.setPaper(paper);
			pf.setOrientation(PageFormat.PORTRAIT);
  
			// override the page format
            Book book = new Book();
            // append all pages
	        book.append(new PDFPrintable(PDFfrombytes), pf, PDFfrombytes.getNumberOfPages());
	        pjob.setPageable(book);

            //if (pjob.printDialog())
            {
            	pjob.print();
            }            
            PDFfrombytes.close();
        } else {
            System.out.println("no printer services found");
        }
	}
}
