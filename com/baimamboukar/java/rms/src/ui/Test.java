package com.baimamboukar.java.rms.src.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Test {
    final String fileName;
    final String generatedPDF;
    final String desc;
    final String title;

    public Test(String fileName, String generatedPDF, String desc, String title) {
        this.fileName = fileName;
        this.generatedPDF = generatedPDF;
        this.desc = desc;
        this.title = title;

        // created PDF document instance
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(generatedPDF));

            document.open();
            Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 22, BaseColor.GREEN);
            Font textFont = FontFactory.getFont(FontFactory.HELVETICA, 14, BaseColor.DARK_GRAY);
            Chunk chunk = new Chunk(title, font);
            Paragraph p = new Paragraph(chunk);
            Paragraph intro = new Paragraph(desc);
            document.add(p);
            document.add(new Paragraph());
            document.add(intro);

            PdfPTable table = new PdfPTable(3);
            addTableHeader(table);
            addRows(table);
            try {
                addCustomRows(table);
            } catch (URISyntaxException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            document.add(table);
            document.close();

            List<List<String>> records = new ArrayList<>();

            try (Scanner scanner = new Scanner(new File(fileName));) {
                while (scanner.hasNextLine()) {
                    List<String> values = new ArrayList<String>();
                    try (Scanner rowScanner = new Scanner(scanner.nextLine())) {
                        rowScanner.useDelimiter(",");
                        while (rowScanner.hasNext()) {
                            values.add(rowScanner.next());
                        }
                    }
                    records.add(values);
                }
                records.forEach((record) -> {
                    Paragraph paragraph = new Paragraph("", textFont);
                    try {

                        Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 14, BaseColor.DARK_GRAY);

                        record.forEach((String cell) -> {
                            Chunk part = new Chunk(cell);
                            paragraph.add(part);
                        });
                        paragraph.setFont(normalFont);
                        paragraph.setExtraParagraphSpace(12);
                        document.add(paragraph);
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                    // System.out.println(record.toString());
                });

            } catch (Exception e) {
                System.out.println("Not found");
            }
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("column header 1", "column header 2", "column header 3")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table) {
        table.addCell("row 1, col 1");
        table.addCell("row 1, col 2");
        table.addCell("row 1, col 3");
    }

    private void addCustomRows(PdfPTable table)
            throws URISyntaxException, BadElementException, IOException {
        // Path path =
        // Paths.get(ClassLoader.getSystemResource("Java_logo.png").toURI());
        // Image img = Image.getInstance(path.toAbsolutePath().toString());
        // img.scalePercent(10);

        // PdfPCell imageCell = new PdfPCell(img);
        // table.addCell(imageCell);

        PdfPCell horizontalAlignCell = new PdfPCell(new Phrase("row 2, col 2"));
        horizontalAlignCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(horizontalAlignCell);

        PdfPCell verticalAlignCell = new PdfPCell(new Phrase("row 2, col 3"));
        verticalAlignCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        table.addCell(verticalAlignCell);
    }

    public String getFileName() {
        return fileName;
    }

    public String getGeneratedPDF() {
        return generatedPDF;
    }

  
}