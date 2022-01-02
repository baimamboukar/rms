package com.baimamboukar.java.rms.src.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFBox {
    final String fileName;
    final String generatedPDF;
    final String desc;
    final String title;

    public PDFBox(String fileName, String generatedPDF, String desc, String title) {
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

    public String getFileName() {
        return fileName;
    }

    public String getGeneratedPDF() {
        return generatedPDF;
    }
}