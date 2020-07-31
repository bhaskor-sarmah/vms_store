package com.bohniman.vmsmaintenance.utilities;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.bohniman.vmsmaintenance.model.TransJobCardItemOrder;
import com.bohniman.vmsmaintenance.model.TransVehicleJobCardItems;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class OrderPdf {

    public ByteArrayInputStream generateOrderPdf(TransJobCardItemOrder order, List<TransVehicleJobCardItems> itemList) {
        Document document = new Document(PageSize.A4, 30, 30, 60, 10);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Resource resource = new ClassPathResource("static/images/emblem.png");
        try {

            PdfWriter.getInstance(document, out);
            document.open();

            PdfPCell cell;
            Font fbs = new Font(Font.HELVETICA, 8, Font.BOLD, Color.BLACK);
            Font f = new Font(Font.HELVETICA, 10, Font.NORMAL, Color.BLACK);
            Font fu = new Font(Font.HELVETICA, 10, Font.UNDERLINE, Color.BLACK);
            Font fb = new Font(Font.HELVETICA, 10, Font.BOLD, Color.BLACK);
            Font fbl = new Font(Font.HELVETICA, 16, Font.BOLD, Color.BLACK);

            float[] columnWidth = { 40F };
            PdfPTable table = new PdfPTable(columnWidth);
            cell = new PdfPCell(new Paragraph("Govt. Of Assam", fbl));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            cell.setPaddingTop(10);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Office of the Commissioner of Police", fbl));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("GUWAHATI :: ASSAM", fbl));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            table.addCell(cell);

            Image image = Image.getInstance(resource.getFile().getAbsolutePath());
            cell = new PdfPCell(image);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            cell.setPaddingLeft(5);
            cell.setPaddingTop(10);
            cell.setPaddingBottom(20);
            cell.setColspan(2);
            table.addCell(cell);

            document.add(table);

            float[] columnWidth1 = { 15F, 85F };
            PdfPTable tableVendorDetails = new PdfPTable(columnWidth1);
            tableVendorDetails.setWidthPercentage(95);

            cell = new PdfPCell(new Paragraph("Subject", fb));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(0);
            cell.setPaddingBottom(10);
            tableVendorDetails.addCell(cell);

            cell = new PdfPCell(new Paragraph(" : Order against the items mentioned below", fb));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(0);
            cell.setPaddingBottom(10);
            tableVendorDetails.addCell(cell);

            cell = new PdfPCell(new Paragraph("To,", fb));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(0);
            tableVendorDetails.addCell(cell);

            cell = new PdfPCell(new Paragraph("", fb));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(0);
            tableVendorDetails.addCell(cell);

            cell = new PdfPCell(new Paragraph("Name", fb));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(0);
            tableVendorDetails.addCell(cell);

            cell = new PdfPCell(new Paragraph(" : " + order.getVendor().getVendorName(), fb));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(0);
            tableVendorDetails.addCell(cell);

            cell = new PdfPCell(new Paragraph("Email", fb));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(0);
            tableVendorDetails.addCell(cell);

            cell = new PdfPCell(new Paragraph(" : " + order.getVendor().getVendorEmail(), fb));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(0);
            tableVendorDetails.addCell(cell);

            cell = new PdfPCell(new Paragraph("Mobile No", fb));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(0);
            tableVendorDetails.addCell(cell);

            cell = new PdfPCell(new Paragraph(" : " + order.getVendor().getVendorMobile(), fb));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(0);
            tableVendorDetails.addCell(cell);

            cell = new PdfPCell(new Paragraph("GST No", fb));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(0);
            tableVendorDetails.addCell(cell);

            cell = new PdfPCell(new Paragraph(" : " + order.getVendor().getGstNo(), fb));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(0);
            tableVendorDetails.addCell(cell);

            cell = new PdfPCell(new Paragraph("PAN No", fb));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(0);
            tableVendorDetails.addCell(cell);

            cell = new PdfPCell(new Paragraph(" : " + order.getVendor().getPanNo(), fb));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorder(0);
            cell.setPaddingBottom(30);
            tableVendorDetails.addCell(cell);

            document.add(tableVendorDetails);

            float[] columnWidth2 = { 10F, 20F, 10F, 15F, 10F, 20F, 15F };
            PdfPTable tableItemDetails = new PdfPTable(columnWidth2);
            tableItemDetails.setWidthPercentage(95);

            cell = new PdfPCell(new Paragraph("Sl. No.", fb));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(1);
            cell.setPaddingTop(10);
            cell.setPaddingBottom(10);
            tableItemDetails.addCell(cell);

            cell = new PdfPCell(new Paragraph("Item Name", fb));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(1);
            cell.setPaddingTop(10);
            cell.setPaddingBottom(10);
            tableItemDetails.addCell(cell);

            cell = new PdfPCell(new Paragraph("Unit", fb));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(1);
            cell.setPaddingTop(10);
            cell.setPaddingBottom(10);
            tableItemDetails.addCell(cell);

            cell = new PdfPCell(new Paragraph("Price Per Unit", fb));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(1);
            cell.setPaddingTop(10);
            cell.setPaddingBottom(10);
            tableItemDetails.addCell(cell);

            cell = new PdfPCell(new Paragraph("MOQ", fb));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(1);
            cell.setPaddingTop(10);
            cell.setPaddingBottom(10);
            tableItemDetails.addCell(cell);

            cell = new PdfPCell(new Paragraph("Order Quantity", fb));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(1);
            cell.setPaddingTop(10);
            cell.setPaddingBottom(10);
            tableItemDetails.addCell(cell);

            cell = new PdfPCell(new Paragraph("Amount", fb));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(1);
            cell.setPaddingTop(10);
            cell.setPaddingBottom(10);
            tableItemDetails.addCell(cell);

            int i = 0;
            Double totalAmount = 0D;
            for (TransVehicleJobCardItems item : itemList) {
                i++;
                Double pricePerUnit = item.getTransVendorItem().getPricePerUnit();
                Double orderQuantity = item.getQuantity();
                Double amount = pricePerUnit * orderQuantity;
                totalAmount += amount;
                cell = new PdfPCell(new Paragraph(String.valueOf(i), f));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(1);
                cell.setPaddingBottom(6);
                tableItemDetails.addCell(cell);

                cell = new PdfPCell(
                        new Paragraph(item.getTransVendorItem().getMasterItemBrand().getItem().getItemName(), f));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(1);
                cell.setPaddingBottom(6);
                tableItemDetails.addCell(cell);

                cell = new PdfPCell(
                        new Paragraph(item.getTransVendorItem().getMasterItemBrand().getItem().getItemUnit(), f));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(1);
                cell.setPaddingBottom(6);
                tableItemDetails.addCell(cell);

                cell = new PdfPCell(new Paragraph(pricePerUnit.toString(), f));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(1);
                cell.setPaddingBottom(6);
                tableItemDetails.addCell(cell);

                cell = new PdfPCell(new Paragraph(item.getTransVendorItem().getMasterItemBrand().getMoq(), f));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(1);
                cell.setPaddingBottom(6);
                tableItemDetails.addCell(cell);

                cell = new PdfPCell(new Paragraph(orderQuantity.toString().replace(".0", ""), f));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(1);
                cell.setPaddingBottom(6);
                tableItemDetails.addCell(cell);

                cell = new PdfPCell(new Paragraph(amount.toString(), f));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBorder(1);
                cell.setPaddingBottom(6);
                tableItemDetails.addCell(cell);
            }

            cell = new PdfPCell(new Paragraph("Total Amount", fb));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(1);
            cell.setColspan(6);
            tableItemDetails.addCell(cell);

            cell = new PdfPCell(new Paragraph(totalAmount.toString(), fb));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(1);
            cell.setPaddingBottom(100);
            tableItemDetails.addCell(cell);

            document.add(tableItemDetails);

            float[] columnWidth3 = { 80F, 20F };
            PdfPTable tableSignature = new PdfPTable(columnWidth3);
            tableSignature.setWidthPercentage(95);

            cell = new PdfPCell(new Paragraph("", fb));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            cell.setPaddingTop(100);
            tableSignature.addCell(cell);

            cell = new PdfPCell(new Paragraph("Official Signature", fbs));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBorder(0);
            tableSignature.addCell(cell);

            document.add(tableSignature);

            document.close();

        } catch (DocumentException | IOException ex) {
            System.out.println("Exception : " + ex.getMessage());
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