package com.mj.sbo.utils;

import com.mj.sbo.Main;
import com.mj.sbo.event.handler.Handler;
import com.mj.sbo.event.handler.Listener;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Resource {


    public static File getTempFile(InputStream inputStream, String prefix, String suffix) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        try {
            File temp = File.createTempFile(prefix, suffix);
            OutputStream outputStream = new FileOutputStream(temp);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            char[] c = new char[1024];
            int count = 0;
            while (true) {
                if ((count = Objects.requireNonNull(inputStreamReader).read(c)) == -1) break;
                outputStreamWriter.write(c, 0, count);
            }
            inputStream.close();
            inputStreamReader.close();
            outputStream.close();
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static File getFile(InputStream inputStream, String path) {
        File file = new File(path);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        try {
            OutputStream outputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
            char[] c = new char[1024];
            int count = 0;
            while (true) {
                if ((count = Objects.requireNonNull(inputStreamReader).read(c)) == -1) break;
                outputStreamWriter.write(c, 0, count);
            }
            inputStream.close();
            inputStreamReader.close();
            outputStream.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
    public static void paste(InputStream inputStream, File file) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file,StandardCharsets.UTF_8));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line + "\n");
            }
            bufferedReader.close();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String,String> readProperties(File file) {
        Map<String, String> properties = new HashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file, StandardCharsets.UTF_8));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if(!line.contains(": "))continue;
                String[] text = line.split(": ");
                properties.put(text[0], text[1]);
                
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }


    public static InputStream getResource(String path){
        return Main.class.getResourceAsStream(path);
    }
    public static void createFile(){
        createFile("Listeners.java");
        createFile("properties.txt");
//        createIndexFile();
    }

    private static void createFile(String name){
        File file = new File(Main.PROJECT_PATH + "\\" + name);
        InputStream inputStream = getResource("/" + name);
        if(!file.exists())
            Resource.paste(inputStream, file);
    }
    public static String getText(InputStream inputStream){
        String text = "";
        try {
            char[] c = new char[1024];
            int count = 0;
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                text += str + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    public static String getJavaCode() {
        String str = "";
        try {
            File file = new File(Main.PROJECT_PATH + "\\Listeners.java");
            InputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null){
                str += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void writeJavaCode(String javaCode){
        try {
            File file = new File(Main.PROJECT_PATH + "\\Listeners.java");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8));
            InputStream inputStream = new ByteArrayInputStream(javaCode.getBytes(StandardCharsets.UTF_8));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line + "\n");
            }
            bufferedReader.close();
            bufferedWriter.close();
        }catch (Exception e){e.printStackTrace();}
    }
    public static OutputStream error;
    public static URLClassLoader urlClassLoader;
    public static OutputStream readJavaCode(){
        try {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            File file = new File(Main.PROJECT_PATH + "\\Listeners.java");
            Path path = Paths.get(file.toURI());
            error = new ByteArrayOutputStream();
            compiler.run(null, null, error, path.toString());
            URL classPath = path.toAbsolutePath().getParent().toUri().toURL();
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{ classPath });
            urlClassLoader = classLoader;
            Class<?> clazz = Class.forName("Listeners", true, classLoader);
            Listener listener = (Listener) clazz.newInstance();
            Handler.removeLastListener();
            Main.registerEvents(listener);
            return error;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return error;
    }
    public static Class<?> getJavaCodeClass(){
        try {
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            File file = new File(Main.PROJECT_PATH + "\\Listeners.java");
            Path path = Paths.get(file.toURI());
            error = new ByteArrayOutputStream();
            compiler.run(null, null, error, path.toString());
            URL classPath = path.toAbsolutePath().getParent().toUri().toURL();
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{ classPath });
            return Class.forName("Listeners", true, classLoader);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
