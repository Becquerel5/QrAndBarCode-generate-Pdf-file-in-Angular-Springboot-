package net.becquerel.springboot.Exportdoc;



import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import net.becquerel.springboot.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class PdfDoc {

    private final static  Logger logger = (Logger)LoggerFactory.getLogger(PdfDoc.class);
    public static ByteArrayInputStream employeePDFreport(List<Employee> employees) throws IOException{


        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document,out);
            document.open();

            //Add text to pdf file
            Font font = FontFactory.getFont(FontFactory.COURIER_BOLD,14, BaseColor.BLUE);
            Paragraph para = new Paragraph("Employee Record",font);
            para.setAlignment(Element.ALIGN_CENTER);
            document.add(para);
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(6);
            //Add Pdf Table Header
            Stream.of("id","firstName","emailId","url1","url2","lastname")
                    .forEach(headerTitle ->{
                        PdfPCell header = new PdfPCell();
                        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                        header.setBackgroundColor(BaseColor.ORANGE);
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setBorder(4);
                        header.setPhrase(new Phrase(headerTitle,headFont));
                        table.addCell(header);
                    });

            for (Employee emp:employees){

                String id = String.valueOf(emp.getId());
                PdfPCell idcell = new PdfPCell(new Phrase(id));
                idcell.setPadding(4);
                idcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                idcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(idcell);

                //String firstname = String.valueOf(emp.getFirstName());
                PdfPCell firstnamecell = new PdfPCell(new Phrase(emp.getFirstName()));
                firstnamecell.setPaddingLeft(4);
                firstnamecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                firstnamecell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(firstnamecell);

               /* PdfPCell lastnamecell = new PdfPCell(new Phrase(emp.getLastName()));
                lastnamecell.setPaddingLeft(2);
                lastnamecell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                lastnamecell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(lastnamecell);
                table.addCell(lastnamecell);*/

                PdfPCell emailcell = new PdfPCell(new Phrase(emp.getEmailId()));
                emailcell.setPaddingLeft(4);
                emailcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                emailcell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(emailcell);

                PdfPCell url1cell = new PdfPCell(new Phrase(emp.getUrl()));
                url1cell.setPaddingLeft(4);
                url1cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                url1cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(url1cell);

                PdfPCell url2cell = new PdfPCell(new Phrase(emp.getUrl2()));
                url2cell.setPaddingLeft(4);
                url2cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                url2cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(url2cell);

                PdfPCell lastnamec = new PdfPCell(new Phrase(emp.getLastName()));
                lastnamec.setPaddingLeft(4);
                lastnamec.setVerticalAlignment(Element.ALIGN_MIDDLE);
                lastnamec.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(lastnamec);

            }
            document.add(table);
            document.close();
        }catch (DocumentException e){
            logger.error(e.toString());
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

}









































