package com.mj.sbo.ui;

import com.mj.sbo.utils.Resource;
import com.mj.sbo.utils.log.LogType;
import com.mj.sbo.utils.log.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

public class CodeScreen extends JFrame {


    private final JFXPanel jfxPanel = new JFXPanel();
    private WebView webView;
    private WebEngine webEngine;
    private Group group = new Group();

    private Scene scene = new Scene(group);

    public CodeScreen() {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        setResizable(false);
        setSize(1400, 760);
        setTitle("SBO");
//        setLayout(new BoxLayout(1));//new GridLayout()
        Platform.runLater(() -> {

            webView = new WebView();
            group = new Group();

            scene = new Scene(group);
            jfxPanel.setScene(scene);
            group.getChildren().add(webView);
            webView.setMinSize(760, 740);
            webView.setMaxSize(760, 740);
            webEngine = webView.getEngine();
            String text = String.format(Resource.getText(Resource.getResource("/index.html")), Resource.getJavaCode().replace("\\", "\\\\").replace("\n", "\\n"));
            webEngine.loadContent(text);
        });
    }

    public static void sleep(long millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getJavaCode() {
        return (String) webEngine.executeScript("getText()");

    }

    public void view() {
        jfxPanel.setBounds(625, 0, 760, 740);
        add(jfxPanel);
        this.getRootPane().registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Platform.runLater(() -> {
                    String text = getJavaCode();
                    Resource.writeJavaCode(text);
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            OutputStream outputStream = Resource.readJavaCode();
                            if (outputStream.toString().equals(""))
                                Logger.log(LogType.INFO, "코드 저장완료");
                            else
                                Logger.log(LogType.ERROR, outputStream.toString());
                            timer.cancel();
                        }
                    }, 500);
                });
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);


        this.getRootPane().registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Platform.runLater(() -> {
                    String text = (String) webEngine.executeScript("window.editor.getModel().getValueInRange(window.editor.getSelection())");
                    if (text.equals(""))
                        return;
                    StringSelection stringSelection = new StringSelection(text);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(stringSelection, null);
                });
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);

        this.getRootPane().registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Platform.runLater(() -> {
                    String text = (String) webEngine.executeScript("window.editor.getModel().getValueInRange(window.editor.getSelection())");
                    if (text.equals(""))
                        return;
                    StringSelection stringSelection = new StringSelection(text);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(stringSelection, null);
                });
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);

        setVisible(true);
    }

}