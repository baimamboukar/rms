
/*
 📌📌📌 THE ICT UNIVERSITY, FALL 20201 📌📌📌

 🚩 ♨♨♨♨♨ JAVA PROGRAMMING I ♨♨♨♨♨ 🚩

 🚩 INSTRUCTOR: ENGR. Daniel Moune

 📌﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋📌

 🎯 NAMES: BAIMAM BOUKAR JEAN JACQUES
 🎯 EMAIL: baimam.jeanjacque@ictuniversity.edu.cm
 🎯 WHATSAPP: (+237) 690535759
 🎯 TWITTER: @baimamjj
 🎯 LINKEDIN: linkedin.com/in/baimamboukar
 🎯 WEBSITE: www.baimamboukar.me
 */

package com.baimamboukar.java.rms.src.ui;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.baimamboukar.java.rms.src.database.Database;
import com.baimamboukar.java.rms.src.models.Course;
import com.baimamboukar.java.rms.src.models.PDFBox;
import com.baimamboukar.java.rms.src.models.Result;
import com.baimamboukar.java.rms.src.models.Student;
import com.baimamboukar.java.rms.src.models.Twilio;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.TextBox.Style;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.FileDialogBuilder;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.gui2.table.Table;

public class ResultGUI {
        static File input;

        public static Panel publishResult(Panel prev, Window window, WindowBasedTextGUI gui) {
                window.setHints(Arrays.asList(Window.Hint.CENTERED));
                Panel contentPanel = new Panel(new GridLayout(2));

                GridLayout gridLayout = (GridLayout) contentPanel.getLayoutManager();
                gridLayout.setHorizontalSpacing(2);
                gridLayout.setVerticalSpacing(1);
                contentPanel.addComponent(
                                new Separator(Direction.HORIZONTAL)
                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                2)));
                contentPanel.addComponent(new Label("≜ RESULT'S COURSE ≜").setForegroundColor(TextColor.ANSI.GREEN)
                                .addStyle(SGR.BOLD));

                List<Course> coursesList = Database.getCourses("SELECT * FROM courses");
                ComboBox<String> courses = new ComboBox<String>();

                TextBox descBox = new TextBox(new TerminalSize(25, 6), Style.MULTI_LINE).setCaretWarp(false);
                courses.withBorder(Borders.singleLineBevel("Course"));

                coursesList.forEach((course) -> {
                        courses.addItem(course.getCourseName());
                });

                contentPanel.addComponent(courses);
                contentPanel.addComponent(new Label("≛ RESULT INFOS ≛").setForegroundColor(TextColor.ANSI.MAGENTA)
                                .addStyle(SGR.BOLD));
                contentPanel.addComponent(descBox)
                                .withBorder(Borders.singleLine("Brief description"));
                contentPanel.addComponent(
                                new Label("⚝ UPLOAD FILE ⚝").setForegroundColor(TextColor.ANSI.CYAN)
                                                .addStyle(SGR.BOLD));
                contentPanel.addComponent(new Button(" Upload ⇲ ", new Runnable() {
                        @Override
                        public void run() {
                                input = new FileDialogBuilder()
                                                .setTitle("Chose File")
                                                .setDescription("CHOSE CSV FILE THAT CONTAINS THE RESULT")
                                                .setActionLabel("select 🎯")
                                                .build()
                                                .showDialog(gui);
                                if (input == null) {
                                        MessageDialog.showMessageDialog(gui, "ERROR",
                                                        "No file selected!\n Make sure you choose a file",
                                                        MessageDialogButton.OK);
                                } else {
                                        // Copy file to ressourse folder
                                        Path copied = Paths
                                                        .get("/home/baimamboukar/school_codes/java/rms/com/baimamboukar/java/rms/ressources/"
                                                                        + input.getName());

                                        Path originalPath = input.toPath();
                                        try {
                                                Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
                                                // WaitingDialog.showDialog(gui, "Copying", "Waiting");
                                        } catch (Exception e) {
                                                e.printStackTrace();
                                        }
                                }

                        }
                }).withBorder(Borders.singleLine("CSV")));
                contentPanel.addComponent(
                                new EmptySpace(TextColor.ANSI.BLUE)

                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                2)));
                contentPanel.addComponent(new Button(" Publish ↗↗↗ ", new Runnable() {
                        @Override
                        public void run() {
                                List<Student> students = Database.getStudents("SELECT * FROM students");
                                List<String> studentsEmail = new ArrayList<String>();
                                students.forEach((Student student) -> {
                                        studentsEmail.add(student.getEmail());
                                });
                                LocalDateTime now = LocalDateTime.now();
                                String fileName = input.getAbsolutePath();
                                String title = "FALL 2021 RESULTS | THE ICT UNIVERSITY \n " + courses.getSelectedItem();
                                String pdfFile = "rms_" + courses.getSelectedItem() + "_results.pdf";
                                PDFBox pdf = new PDFBox(fileName, pdfFile, descBox.getText(), title);
                                String generated = pdf.getGeneratedPDF();
                                Result result = new Result(now.toString(), courses.getSelectedItem(), "2",
                                                descBox.getText(),
                                                generated != null ? generated : "No file");
                                Database.resultInsert(result.getpublicationDate(), result.getCourseId(),
                                                result.getPublisherId(), result.getDescription(),
                                                result.getResultsFile());
                                String message = "Emails successfully delivered to students.....";
                                if (result.getResultsFile() != "No file") {

                                        String subject = "RMS ⚝ FALL 2021 ⚝ " + courses.getSelectedItem();
                                        new Twilio(subject, descBox.getText(),

                                                        studentsEmail, new File(generated));

                                        MessageDialog.showMessageDialog(gui, "Success",
                                                        message, MessageDialogButton.OK);
                                }
                        }
                }).withBorder(Borders.singleLine("done")).setLayoutData(
                                GridLayout.createHorizontallyEndAlignedLayoutData(
                                                2)));

                contentPanel.addComponent(new Button("BACK", () -> {
                        window.setComponent(prev);
                }).setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(
                                                2)));
                return contentPanel;
        }

        public static Panel displayResults(Panel back, Window window, WindowBasedTextGUI gui) {
                Panel contentPanel = new Panel(new GridLayout(4));

                GridLayout gridLayout = (GridLayout) contentPanel.getLayoutManager();
                gridLayout.setHorizontalSpacing(2);
                contentPanel.addComponent(
                                new Separator(Direction.HORIZONTAL)
                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                4)));

                String mean = Database.getTotalAVG();
                String mode = Database.getMode();
                String stdev = Database.getStandardDev();
                /**
                 * STATISTICS BUTTONS
                 */
                contentPanel.addComponent(
                                new EmptySpace()

                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                4)));
                contentPanel.addComponent(new Label("❴ RESULTS STATISTICAL ANALYSIS ❵⤵")
                                .addStyle(SGR.BOLD)
                                .setForegroundColor(TextColor.ANSI.CYAN).setLayoutData(
                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                4)));
                contentPanel.addComponent(
                                new EmptySpace()

                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                4)));

                contentPanel.addComponent(new Button(" MODE: " + mode + " ")
                                .setEnabled(false)
                                .withBorder(Borders.singleLine("⤴⤴⤴⤴⤴")));
                contentPanel.addComponent(new Button(" MEAN: " + mean + " ").setEnabled(false)
                                .withBorder(Borders.singleLine("♨⥄⥄⥄♨")));
                contentPanel.addComponent(new Button(" MEDIAN: D ")
                                .setEnabled(false)
                                .withBorder(Borders.singleLine("⅀≞≞≞⅀")));
                contentPanel.addComponent(new Button(" STDEV: " + stdev + " ")
                                .setEnabled(false)
                                .withBorder(Borders
                                                .singleLine("∂∂≜≜≜∂∂")));
                Table<String> table = new Table<String>("Date", "Course", "Description", "File", "Status");
                table.withBorder(Borders.doubleLine("."));

                List<Result> courses = Database.getResults("SELECT * FROM results");
                table.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(4));

                if (courses != null) {
                        for (Result result : courses) {
                                table.getTableModel().addRow(result.getpublicationDate().substring(0, 10) + " ⤻ ",
                                                result.getCourseId().substring(0, 8) + "   ",
                                                result.getDescription().substring(0, 8) + "... ⤻ ",
                                                result.getResultsFile().substring(0, 8), "✅");
                        }
                }
                table.setSelectAction(() -> {
                        int index = table.getSelectedRow();
                        MessageDialog.showMessageDialog(gui, "Results description",
                                        courses.get(index).toString(),
                                        MessageDialogButton.Close);
                });

                table.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(
                                4));
                contentPanel.addComponent(
                                new EmptySpace()

                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                4)));
                contentPanel.addComponent(new Label("✅ Published Results ⤵")
                                .addStyle(SGR.BOLD)
                                .setForegroundColor(TextColor.ANSI.CYAN).setLayoutData(
                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                4)));
                contentPanel.addComponent(
                                new EmptySpace()

                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                4)));
                contentPanel.addComponent(table);
                contentPanel.addComponent(
                                new Separator(Direction.HORIZONTAL)
                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                4)));
                contentPanel.addComponent(
                                new Button("Back", () -> {
                                        window.setComponent(back);
                                })
                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                4)));

                return contentPanel;
        }

        public static Panel deleteResult(Panel back, Window window, WindowBasedTextGUI gui) {
                Panel contentPanel = new Panel(new GridLayout(4));

                GridLayout gridLayout = (GridLayout) contentPanel.getLayoutManager();
                gridLayout.setHorizontalSpacing(2);
                contentPanel.addComponent(
                                new Separator(Direction.HORIZONTAL)
                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                4)));

                /**
                 * STATISTICS BUTTONS
                 */
                String mean = Database.getTotalAVG();
                String mode = Database.getMode();
                String stdev = Database.getStandardDev();
                contentPanel.addComponent(
                                new EmptySpace()

                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                4)));
                contentPanel.addComponent(new Label("Click on the result you want to delete ⤵")
                                .addStyle(SGR.BOLD)
                                .setForegroundColor(TextColor.ANSI.CYAN).setLayoutData(
                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                4)));
                contentPanel.addComponent(
                                new EmptySpace()

                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                4)));
                contentPanel.addComponent(
                                new EmptySpace()

                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                4)));

                contentPanel.addComponent(new Button(" MODE: " + mode + " ")
                                .setEnabled(false)
                                .withBorder(Borders.singleLine("⤴⤴⤴⤴⤴")));
                contentPanel.addComponent(new Button(" MEAN: " + mean + " ").setEnabled(false)
                                .withBorder(Borders.singleLine("♨⥄⥄⥄♨")));
                contentPanel.addComponent(new Button(" MEDIAN: D ")
                                .setEnabled(false)
                                .withBorder(Borders.singleLine("⅀≞≞≞⅀")));
                contentPanel.addComponent(new Button(" STDEV: " + stdev + " ")
                                .setEnabled(false)
                                .withBorder(Borders
                                                .singleLine("∂∂≜≜≜∂∂")));

                ;

                Table<String> table = new Table<String>("Date", "Course", "Description", "File");
                table.withBorder(Borders.doubleLine("."));

                List<Result> courses = Database.getResults("SELECT * FROM results");
                table.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(4));
                if (courses != null) {
                        for (Result result : courses) {
                                table.getTableModel().addRow(result.getpublicationDate().substring(0, 10) + " ⤻ ",
                                                result.getCourseId().substring(0, 8) + "   ",
                                                result.getDescription().substring(0, 8) + "... ⤻ ",
                                                result.getResultsFile().substring(0, 8));
                        }
                }
                contentPanel.addComponent(
                                new EmptySpace(TextColor.ANSI.CYAN)

                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                4)));

                table.setSelectAction(() -> {
                        Database.deleteResult(courses.get(table.getSelectedRow()).getpublicationDate());

                        MessageDialog.showMessageDialog(gui, "Result Deletion",
                                        "Deleted successfuly",
                                        MessageDialogButton.Close);
                        window.setComponent(back);
                });

                table.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(
                                4));
                contentPanel.addComponent(
                                new EmptySpace()

                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                4)));
                contentPanel.addComponent(table);
                contentPanel.addComponent(
                                new EmptySpace()

                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                4)));
                contentPanel.addComponent(
                                new EmptySpace(TextColor.ANSI.CYAN)

                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                4)));
                contentPanel.addComponent(
                                new EmptySpace()

                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                4)));
                contentPanel.addComponent(
                                new Button("Back", () -> {
                                        window.setComponent(back);
                                })
                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                4)));

                return contentPanel;
        }
}
