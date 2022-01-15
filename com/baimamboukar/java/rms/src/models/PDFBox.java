package com.baimamboukar.java.rms.src.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
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
            document.addTitle("ALPHA RMS");
            document.addAuthor("ALPHA RMS");
            document.addCreator("Baimam Boukar");
            document.addSubject("Exam results");
            document.left(20);
            document.right(20);
            document.top(20);
            document.bottom(20);
            Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 22, BaseColor.GREEN);
            Font textFont = FontFactory.getFont(FontFactory.HELVETICA, 14, BaseColor.DARK_GRAY);
            Chunk chunk = new Chunk(title, font);
            Paragraph p = new Paragraph(chunk);
            Paragraph intro = new Paragraph(desc);
            document.add(p);
            document.add(new Paragraph());
            document.add(intro);
            document.add(new Paragraph("\n \n "));

            PdfPTable table = new PdfPTable(2);
            addTableHeader(table);

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
                    Paragraph gparagraph = new Paragraph("", textFont);
                    Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 14, BaseColor.DARK_GRAY);

                    record.forEach((String cell) -> {
                        paragraph.setFont(normalFont);
                        paragraph.setExtraParagraphSpace(12);
                        Chunk mat = new Chunk(record.get(0));
                        Chunk grad = new Chunk(record.get(1));
                        paragraph.add(mat);
                        gparagraph.add(grad);

                        addRows(table, paragraph, gparagraph);
                    });
                });

            } catch (Exception e) {
                System.out.println("Not found");
            }
            document.add(table);
            document.close();

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("📌 MATRICULE", "📊 GRADE")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.GREEN);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, Paragraph mat, Paragraph grad) {
        table.addCell(mat);
        table.addCell(grad);
    }

    public String getFileName() {
        return fileName;
    }

    public String getGeneratedPDF() {
        return generatedPDF;
    }
}