package com.mj.sbo.ui;

import com.mj.sbo.objects.Bot;
import com.mj.sbo.Main;
import com.mj.sbo.objects.login.AppleLogin;
import com.mj.sbo.objects.login.EmailLogin;
import com.mj.sbo.objects.login.FacebookLogin;
import com.mj.sbo.objects.login.PhoneLogin;
import com.mj.sbo.storage.DatabaseType;
import com.mj.sbo.storage.Databases;
import com.mj.sbo.utils.Resource;
import com.mj.sbo.utils.log.LogType;
import com.mj.sbo.utils.log.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.*;
import java.util.Timer;

import javax.swing.*;

public class MainUI extends JFrame {


    private JButton urlButton = new JButton("이동");
    private JLabel serialNumberLabel = new JLabel("");
    private JLabel serialNumberDescription = new JLabel("");
    private JLabel idLabel = new JLabel("ID : ");
    private JLabel passwordLabel = new JLabel("Password : ");
    public JTextField url = new JTextField(25);
    public JTextField serialNumber = new JTextField(1);
    public JTextField id = new JTextField(10);
    public JTextArea chatLog = new JTextArea();
    private JTextField chat = new JTextField("", 44);
    private JButton chatButton = new JButton("send");
    private JButton debugButton = new JButton("Debug");
    public JPasswordField password = new JPasswordField(10);
    public JComboBox<String> comboBox = new JComboBox<>(new String[]{"Phone", "Facebook", "Email", "Apple"});
    public JCheckBox checkBox = new JCheckBox("저장");
    private JButton codeEditor = new JButton("Code");
    private JButton start = new JButton("실행");
    private JButton stop = new JButton("종료");
    private JFXPanel jfxPanel = new JFXPanel();
    private JLabel developer = new JLabel();
    private JLabel description = new JLabel();
    public JCheckBox webViewCheck = new JCheckBox("웹 보기 (실행 전에 체크해야 보입니다. 필수 X)");
    private WebView webView;
    private WebEngine webEngine;
    private Group group = new Group();

    private Scene scene = new Scene(group);

    public MainUI() {
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        setResizable(false);
        setSize(1400, 760);
        setTitle("SBO - v" + Main.VERSION);
        setLayout(null);//new GridLayout()
        Platform.runLater(()-> {

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
//            webEngine.getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
//                if (newState == Worker.State.SUCCEEDED) {
//                }
//            });
        });
    }
    public String getJavaCode(){
        return (String) webEngine.executeScript("getText()");

    }
    public void view() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                try {
                    Bot.stop();
                }catch (Exception e){}
                System.exit(0);
            }
        });
        url.setToolTipText("URL을 입력해주세요");
        url.setText("https://www.spooncast.net/kr/");
        url.setBounds(0, 0, 555, 30);
        urlButton.setBounds(550, 0, 75, 29);

        serialNumberLabel.setBounds(5, 50, 75, 30);
        serialNumberLabel.setSize(100, 20);
//        serialNumber.setBounds(85, 50, 75, 20);
        serialNumberDescription.setBounds(175, 50, 300, 100);
        serialNumberDescription.setSize(300, 20);
        idLabel.setBounds(5, 75, 100, 20);
        id.setBounds(30, 75, 150, 20);
        passwordLabel.setBounds(190, 75, 150, 20);
        password.setBounds(263, 75, 150, 20);
        comboBox.setBounds(425, 75, 100, 20);
        checkBox.setBounds(535, 75, 50, 20);

        chatLog.setAutoscrolls(true);
        chatLog.setEditable(false);
        chatLog.setText("로그");
        JScrollPane scrollPane = new JScrollPane(chatLog);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBounds(0, 110, 625, 400);
        chat.setBounds(0, 508, 562, 30);
        chatButton.setBounds(560, 508, 65, 29);

        start.setBounds(230, 600, 100, 30);
        stop.setBounds(330, 600, 100, 30);
        debugButton.setBounds(525, 695, 100, 30);

        jfxPanel.setBounds(625, 0, 760, 740);
        developer.setBounds(0, 680, 300, 50);
        developer.setText("디스코드 문의 : _R#8668");
        webViewCheck.setBounds(0, 670, 300, 30);
        add(jfxPanel);
        add(developer);
        add(webViewCheck);
        add(urlButton);
        add(url);
        add(serialNumberLabel);
        add(serialNumber);
        add(serialNumberDescription);
        add(idLabel);
        add(id);
        add(passwordLabel);
        add(password);
        add(comboBox);
        add(checkBox);
        add(chatButton);
        add(chat);
        add(scrollPane);
        add(start);
        add(debugButton);
        add(stop);
        add(codeEditor);

        chat.addActionListener((event)-> {
            String message = chat.getText();
            Bot.sendMessage(message);
            chat.setText("");
        });

        this.getRootPane().registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Platform.runLater(()->{
                    String text = getJavaCode();
                    Resource.writeJavaCode(text);
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            OutputStream outputStream = Resource.readJavaCode();
                            if(outputStream.toString().equals(""))
                                Logger.log(LogType.INFO, "코드 저장완료");
                            else
                                Logger.log(LogType.ERROR, outputStream.toString());
                            timer.cancel();
                        }
                    }, 500);
                });
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK),JComponent.WHEN_IN_FOCUSED_WINDOW );


        this.getRootPane().registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Platform.runLater(()->{
//                    String text2 =  webEngine.executeScript("window.editor.getModel().getLineCount()").toString();
                    String text = (String) webEngine.executeScript("window.editor.getModel().getValueInRange(window.editor.getSelection())");
                    if(text.equals(""))
                        return;
                    StringSelection stringSelection = new StringSelection(text);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(stringSelection, null);
                });
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK),JComponent.WHEN_IN_FOCUSED_WINDOW );

        this.getRootPane().registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Platform.runLater(()->{
                    String text = (String) webEngine.executeScript("window.editor.getModel().getValueInRange(window.editor.getSelection())");
                    if(text.equals(""))
                        return;
                    StringSelection stringSelection = new StringSelection(text);
                    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    clipboard.setContents(stringSelection, null);
                });
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_D,KeyEvent.CTRL_DOWN_MASK),JComponent.WHEN_IN_FOCUSED_WINDOW );

        debugButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class<?> clazz = Class.forName("Listeners", true, Resource.urlClassLoader);
                    Method method = clazz.getDeclaredMethod("debug");
                    method.invoke(clazz.newInstance());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
        urlButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Bot.driver.get(url.getText());
                addLog(url.getText() + "로 이동되었습니다.");
            }
        });
        stop.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Bot.stop();
                addLog("종료되었습니다.");
            }
        });
        start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addLog("로딩 중");
                String loginType = (String) comboBox.getSelectedItem();
                switch (Objects.requireNonNull(loginType)) {
                    case "Phone":
                        Main.login = new PhoneLogin();
                        break;
                    case "Facebook":
                        Main.login = new FacebookLogin();
                        break;
                    case "Email":
                        Main.login = new EmailLogin();
                        break;
                    case "Apple":
                        Main.login = new AppleLogin();
                        break;
                }
                if (checkBox.isSelected()) {
                    Databases.getInstance().write(DatabaseType.INFO);
                }
                Bot.start();
                addLog("로딩 완료");
            }
        });
        codeEditor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Platform.runLater(()-> {

                });

            }
        });
        chatButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String message = chat.getText();
                Bot.sendMessage(message);
                chat.setText("");
            }
        });
        setVisible(true);
    }

    public void addLog(String log) {
        chatLog.append("\n" + log);
        chatLog.setCaretPosition(chatLog.getDocument().getLength());
    }

    public static void sleep(long millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public boolean isWebView(){
        return webViewCheck.isSelected();
    }
    public void getHTML() {

    }
}