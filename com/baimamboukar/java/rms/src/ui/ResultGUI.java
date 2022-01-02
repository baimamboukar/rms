package com.baimamboukar.java.rms.src.ui;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.baimamboukar.java.rms.src.database.Database;
import com.baimamboukar.java.rms.src.models.Course;
import com.baimamboukar.java.rms.src.models.Result;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Direction;
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
                contentPanel.addComponent(new Label("Result's Course"));

                List<Course> coursesList = Database.getCourses("SELECT * FROM courses");
                ComboBox<String> courses = new ComboBox<String>();

                TextBox descBox = new TextBox(new TerminalSize(25, 6), Style.MULTI_LINE).setCaretWarp(false);
                courses.withBorder(Borders.singleLineBevel("Course"));

                coursesList.forEach((course) -> {
                        courses.addItem(course.getCourseName());
                });

                contentPanel.addComponent(courses);
                contentPanel.addComponent(new Label("Result Description"));
                contentPanel.addComponent(descBox)
                                .withBorder(Borders.singleLine("Brief description"));
                contentPanel.addComponent(new Label("clikc to Upload the file"));
                contentPanel.addComponent(new Button("Upload file", new Runnable() {
                        @Override
                        public void run() {
                                // Twilio.sendMail();
                                input = new FileDialogBuilder()
                                                .setTitle("Open File")
                                                .setDescription("Choose a file")
                                                .setActionLabel("Open")
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

                                // List<List<String>> records = new ArrayList<>();

                                // try (Scanner scanner = new Scanner(new File(input.getAbsolutePath()));) {
                                // while (scanner.hasNextLine()) {
                                // List<String> values = new ArrayList<String>();
                                // try (Scanner rowScanner = new Scanner(scanner.nextLine())) {
                                // rowScanner.useDelimiter(",");
                                // while (rowScanner.hasNext()) {
                                // values.add(rowScanner.next());
                                // }
                                // }
                                // records.add(values);
                                // }
                                // records.forEach((record) -> {
                                // System.out.println(record.toString());
                                // });

                                // } catch (Exception e) {
                                // System.out.println("Not found");
                                // }

                        }
                }).withBorder(Borders.doubleLine("--csv only--")));
                contentPanel.addComponent(
                                new Separator(Direction.HORIZONTAL)
                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                2)));
                contentPanel.addComponent(new Button("Publish result", new Runnable() {
                        @Override
                        public void run() {
                                LocalDateTime now = LocalDateTime.now();

                                Result result = new Result(now.toString(), courses.getSelectedItem(), "2",
                                                descBox.getText(),
                                                input != null ? input.getAbsolutePath() : "No file");
                                Database.insertResult(
                                                "INSERT INTO results VALUES(" + result.getpublicationDate() + "," +
                                                                result.getCourseId()
                                                                + "," + result.getPublisherId() + ","
                                                                + result.getDescription() + ","
                                                                + result.getResultsFile()
                                                                + ")");
                                String message = "The result will be published and each concerned student will be notified \n [WE ARE STILL COOKING THIS FEAUTRE]";
                                if (result.getResultsFile() != "No file") {
                                        MessageDialog.showMessageDialog(gui, "Success",
                                                        message, MessageDialogButton.OK);
                                }
                        }
                }).withBorder(Borders.doubleLine("done")).setLayoutData(
                                GridLayout.createHorizontallyEndAlignedLayoutData(
                                                2)));

                contentPanel.addComponent(new Button("BACK", () -> {
                        window.setComponent(prev);
                }).setLayoutData(
                                GridLayout.createHorizontallyFilledLayoutData(
                                                2)));
                return contentPanel;
        }

        public static Panel displayStudents(Panel back, Window window, WindowBasedTextGUI gui) {
                Panel contentPanel = new Panel(new GridLayout(4));
                GridLayout gridLayout = (GridLayout) contentPanel.getLayoutManager();
                gridLayout.setHorizontalSpacing(2);
                contentPanel.addComponent(
                                new Separator(Direction.HORIZONTAL)
                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                4)));
                Table<String> table = new Table<>("CourseID", "TeacherID", "CourseName");

                List<Course> courses = Database.getCourses("SELECT * FROM courses");
                table.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(4));
                if (courses != null) {
                        for (Course course : courses) {
                                table.getTableModel().addRow(course.getCourseID(), course.getTeacherID(),
                                                course.getCourseName());
                        }
                }
                table.setSelectAction(() -> {

                        MessageDialog.showMessageDialog(gui, "Course Information",
                                        "CourseID: ICT 1112 \n" +
                                                        "TeacherID: 3\n" +
                                                        "Course: Research Methods\n",
                                        MessageDialogButton.Close);
                });

                table.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(
                                4));
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
}
