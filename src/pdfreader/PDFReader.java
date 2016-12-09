package pdfreader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PDFReader {
	
	PDFTextStripper pdfStripper = null;
	PDDocument pdDoc = null;
	int pageCount = 0;
	
	public PDFReader(String path){
		COSDocument cosDoc = null;
		File file = new File(path);
		try {
			PDFParser parser = new PDFParser(new FileInputStream(file));
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);
			pageCount = pdDoc.getNumberOfPages();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String parsePage(int page){
		pdfStripper.setStartPage(page);
		pdfStripper.setEndPage(page);
		try {
			return pdfStripper.getText(pdDoc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getPageCount(){
		return pageCount;
	}
	
	public static void main(String[] args) {
		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		File file;
//		file = new File("/mnt/8ACC1B44CC1B2A49/School/Research/BookReader/Joint Paper/J2 test images/ABBYY/1/CutRGBImJ2SHM001.pdf");
		file = new File("/mnt/8ACC1B44CC1B2A49/School/Research/BookReader/Joint Paper/book/The_memoirs_of_sherlock_holmes.pdf");
		try {
			PDFParser parser = new PDFParser(new FileInputStream(file));
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);
			pdfStripper.setStartPage(2);
			pdfStripper.setEndPage(3);
			String parsedText = pdfStripper.getText(pdDoc);
			System.out.println(parsedText);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static String loadPDF(String path, int startPage, int endPage){
		PDFTextStripper pdfStripper = null;
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		File file = new File(path);
		try {
			PDFParser parser = new PDFParser(new FileInputStream(file));
			parser.parse();
			cosDoc = parser.getDocument();
			pdfStripper = new PDFTextStripper();
			pdDoc = new PDDocument(cosDoc);
			if(endPage<0) endPage = pdDoc.getNumberOfPages();
			pdfStripper.setStartPage(startPage);
			pdfStripper.setEndPage(endPage);
			String parsedText = pdfStripper.getText(pdDoc);
			return parsedText;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int getPDFpageCount(String path){
		PDDocument pdDoc = null;
		COSDocument cosDoc = null;
		File file = new File(path);
		try {
			PDFParser parser = new PDFParser(new FileInputStream(file));
			parser.parse();
			cosDoc = parser.getDocument();
			pdDoc = new PDDocument(cosDoc);
			int n_pages = pdDoc.getNumberOfPages();
			pdDoc.close();
			return n_pages;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
