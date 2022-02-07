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

import java.io.IOException;
import java.util.Arrays;

import com.baimamboukar.java.rms.src.database.Database;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

public class Dashboard {
        /*
         * 🎯🎯🎯 DASHBOARD GUI CLASS 🎯🎯🎯
         * This class contains lanterna code that show how the data related all the
         * classes are
         * represented in
         * the application
         * 
         * @author: BAIMAM BOUKAR JEAN JACQUES
         * 
         * @version: 1.0
         * 
         * @since: First Release
         * 
         * @apinote: Lanterna Library used
         * 
         */
        static Panel contentPanel;
        static int studentsCount = Database.getCount("SELECT COUNT(*) as count FROM students");

        static int teachersCount = Database.getCount("SELECT COUNT(*) as count FROM teachers");
        static int coursesCount = Database.getCount("SELECT COUNT(*) as count FROM courses");
        static int resultsCount = Database.getCount("SELECT COUNT(*) as count FROM results");

        public static void main(String[] args) {

                DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();

                Screen screen = null;

                try {

                        screen = terminalFactory.createScreen();
                        screen.startScreen();
                        screen.doResizeIfNecessary();

                        final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen,
                                        new DefaultWindowManager(),
                                        new EmptySpace(TextColor.ANSI.DEFAULT));

                        final Window window = new BasicWindow("Admin Dashboard");
                        // window.setFixedSize(new TerminalSize(100, 80));
                        window.setDecoratedSize(new TerminalSize(4, 8));
                        window.setHints(Arrays.asList(Window.Hint.CENTERED));
                        contentPanel = new Panel(new GridLayout(4));
                        Panel mainPanel = new Panel(new GridLayout(4));
                        mainPanel.addComponent(new Label("Hello World"));

                        final Panel activPanel = contentPanel;
                        GridLayout gridLayout = (GridLayout) contentPanel.getLayoutManager();
                        gridLayout.setHorizontalSpacing(2);
                        contentPanel.addComponent(
                                        new Label("༻☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄༺")
                                                        .setForegroundColor(TextColor.ANSI.CYAN)
                                                        .setLayoutData(
                                                                        GridLayout.createHorizontallyFilledLayoutData(
                                                                                        4)));
                        contentPanel.addComponent(
                                        new EmptySpace()

                                                        .setLayoutData(
                                                                        GridLayout.createHorizontallyFilledLayoutData(
                                                                                        4)));
                        contentPanel.addComponent(
                                        new Label("፤፤፤፤ RMS DATABASE STATISTICS ፤፤፤፤")
                                                        .setLabelWidth(100)
                                                        .setForegroundColor(TextColor.ANSI.BLACK)
                                                        .setLayoutData(
                                                                        GridLayout.createHorizontallyFilledLayoutData(
                                                                                        4)));
                        contentPanel.addComponent(
                                        new EmptySpace()

                                                        .setLayoutData(
                                                                        GridLayout.createHorizontallyFilledLayoutData(
                                                                                        4)));

                        contentPanel.addComponent(
                                        new Label("｟" + studentsCount + "K Students｠")
                                                        .setBackgroundColor(new TextColor.RGB(14, 244, 125)));
                        contentPanel.addComponent(
                                        new Label("｟" + teachersCount + "K Teachers｠")
                                                        .setBackgroundColor(TextColor.ANSI.BLUE_BRIGHT));
                        contentPanel.addComponent(new Label("｟" + coursesCount + "K Courses｠")
                                        .setBackgroundColor(TextColor.ANSI.RED));
                        contentPanel.addComponent(new Label("｟" + resultsCount + "K Results｠")
                                        .setBackgroundColor(new TextColor.RGB(225, 49, 125)));
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
                                        new Label("፤፤፤፤ CRUD OPERATIONS ON RESULT ፤፤፤፤")
                                                        .addStyle(SGR.BOLD)
                                                        .setLabelWidth(100)
                                                        .setForegroundColor(TextColor.ANSI.CYAN)
                                                        .setLayoutData(
                                                                        GridLayout.createHorizontallyFilledLayoutData(
                                                                                        4)));
                        contentPanel.addComponent(
                                        new EmptySpace()

                                                        .setLayoutData(
                                                                        GridLayout.createHorizontallyFilledLayoutData(
                                                                                        4)));
                        contentPanel.addComponent(new Button(" Result ",
                                        new Runnable() {
                                                public void run() {
                                                        window.setComponent(ResultGUI.publishResult(contentPanel,
                                                                        window, textGUI));
                                                };
                                        })
                                                        .withBorder(Borders.singleLine("◂◂create▸▸"))
                                                        .setLayoutData(GridLayout.createLayoutData(
                                                                        GridLayout.Alignment.CENTER,
                                                                        GridLayout.Alignment.CENTER)));
                        contentPanel.addComponent(new Button(" result ",
                                        () -> window.setComponent(
                                                        ResultGUI.displayResults(contentPanel, window, textGUI)))

                                                                        .withBorder(Borders.singleLine("◂◂read▸▸"))
                                                                        .setLayoutData(GridLayout.createLayoutData(
                                                                                        GridLayout.Alignment.END,
                                                                                        GridLayout.Alignment.BEGINNING)));
                        contentPanel.addComponent(new Button(" result ",
                                        () -> MessageDialog.showMessageDialog(textGUI, "MessageBox",
                                                        "Result can not be updated after it has been sent! ☄☄☄☄☄",
                                                        MessageDialogButton.OK))
                                                                        .withBorder(Borders.singleLine("◂◂update▸▸"))
                                                                        .setLayoutData(GridLayout.createLayoutData(
                                                                                        GridLayout.Alignment.CENTER,
                                                                                        GridLayout.Alignment.CENTER)));
                        contentPanel.addComponent(new Button(" result ",
                                        () -> window.setComponent(
                                                        ResultGUI.deleteResult(contentPanel, window, textGUI)))
                                                                        .withBorder(Borders.singleLine("◂◂delete▸▸"))
                                                                        .setSize(new TerminalSize(8, 4))
                                                                        .setLayoutData(GridLayout.createLayoutData(
                                                                                        GridLayout.Alignment.CENTER,
                                                                                        GridLayout.Alignment.CENTER)));
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
                        contentPanel.addComponent(
                                        new EmptySpace(TextColor.ANSI.RED)

                                                        .setLayoutData(
                                                                        GridLayout.createHorizontallyFilledLayoutData(
                                                                                        4)));
                        contentPanel.addComponent(
                                        new EmptySpace()

                                                        .setLayoutData(
                                                                        GridLayout.createHorizontallyFilledLayoutData(
                                                                                        4)));
                        contentPanel.addComponent(
                                        new Label("፤፤፤፤ STATISTICAL ANALYSIS ፤፤፤፤").addStyle(SGR.BOLD)
                                                        .setLabelWidth(100)
                                                        .setForegroundColor(TextColor.ANSI.RED)
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
                        contentPanel.addComponent(new Button(" ∂: " + stdev + " ")
                                        .setEnabled(false)
                                        .withBorder(Borders
                                                        .singleLine("∂∂≜≜≜∂∂")));
                        contentPanel.addComponent(
                                        new EmptySpace()

                                                        .setLayoutData(
                                                                        GridLayout.createHorizontallyFilledLayoutData(
                                                                                        4)));

                        /** ACTION BUTTONS FOR DISPLAY */
                        contentPanel.addComponent(
                                        new EmptySpace(TextColor.ANSI.MAGENTA)

                                                        .setLayoutData(
                                                                        GridLayout.createHorizontallyFilledLayoutData(
                                                                                        4)));
                        contentPanel.addComponent(
                                        new EmptySpace()

                                                        .setLayoutData(
                                                                        GridLayout.createHorizontallyFilledLayoutData(
                                                                                        4)));

                        contentPanel.addComponent(new Button(" Students ☄ ",
                                        () -> window.setComponent(
                                                        StudentGUI.displayStudents(contentPanel, window, textGUI)))
                                                                        .withBorder(Borders.singleLine("display")));
                        contentPanel.addComponent(new Button(" Teacher ☄ ",
                                        () -> window.setComponent(
                                                        TeacherGUI.displayteachers(contentPanel, window, textGUI)))
                                                                        .withBorder(Borders.singleLine("display")));
                        contentPanel.addComponent(new Button(" Results ☄ ",
                                        () -> window.setComponent(
                                                        ResultGUI.displayResults(contentPanel, window, textGUI)))
                                                                        .withBorder(Borders.singleLine("display")));
                        contentPanel.addComponent(new Button(" Course ☄ ",
                                        () -> window.setComponent(
                                                        CourseGUI.displayCourses(contentPanel, window, textGUI)))
                                                                        .setLayoutData(GridLayout.createLayoutData(
                                                                                        GridLayout.Alignment.CENTER,
                                                                                        GridLayout.Alignment.CENTER))
                                                                        .withBorder(Borders.singleLine("display")));
                        contentPanel.addComponent(
                                        new Button("Reload", new Runnable() {

                                                @Override
                                                public void run() {
                                                        window.setComponent(contentPanel);

                                                }

                                        }).setLayoutData(
                                                        GridLayout.createHorizontallyEndAlignedLayoutData(2)));
                        contentPanel.addComponent(
                                        new Button("Close", window::close).setLayoutData(
                                                        GridLayout.createHorizontallyEndAlignedLayoutData(2)));

                        window.setComponent(activPanel);

                        textGUI.addWindowAndWait(window);

                } catch (IOException e) {
                        e.printStackTrace();
                } finally {
                        if (screen != null) {
                                try {
                                        screen.stopScreen();
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                        }
                }
        }

        public static Panel getPanel() {
                return contentPanel;
        }
}