package com.bohniman.vmsmaintenance.utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.bohniman.vmsmaintenance.model.TransJobCardItemOrder;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import org.springframework.stereotype.Component;

@Component
public class OrderPdf {

    public ByteArrayInputStream generateOrderPdf(TransJobCardItemOrder order) {

        Document document = new Document(PageSize.A4, 30, 30, 60, 10);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfPCell cell;
            Font f = new Font(Font.HELVETICA, 10, Font.NORMAL);

            // First Part
            float[] pointColumnWidths1 = { 100F };
            PdfPTable table1 = new PdfPTable(pointColumnWidths1);

            cell = new PdfPCell(new Paragraph("Sample Order PDF", new Font(Font.HELVETICA, 10, Font.BOLD)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            cell.setPaddingBottom(10);
            table1.addCell(cell);

            PdfWriter.getInstance(document, out);
            document.open();

            document.add(table1);

            document.close();

        } catch (DocumentException ex) {
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    public ByteArrayInputStream generateErrorPdf() {

        Document document = new Document(PageSize.A4, 30, 30, 60, 10);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfPCell cell;
            Font f = new Font(Font.HELVETICA, 10, Font.NORMAL);

            // First Part
            float[] pointColumnWidths1 = { 100F };
            PdfPTable table1 = new PdfPTable(pointColumnWidths1);

            cell = new PdfPCell(new Paragraph("Some Error has ocurred", new Font(Font.HELVETICA, 10, Font.BOLD)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            cell.setPaddingBottom(10);
            table1.addCell(cell);

            PdfWriter.getInstance(document, out);
            document.open();

            document.add(table1);

            document.close();

        } catch (DocumentException ex) {
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}