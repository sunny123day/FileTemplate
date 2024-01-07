package cn.ccwcy;

import com.intellij.psi.PsiFile;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class SavePsiToFile {
    public static void savePsiToFile(PsiFile psiFile, String filePath) {
        try {
            String str1 = filePath.substring(0, filePath.indexOf(":"));
            String str2 = (filePath.substring(str1.length()+1, filePath.length())) + "\\" + psiFile.getName();
            File file = new File(str2);
            if (!file.exists()) {
                file.createNewFile();
            }else {
                if(showInputDialog() != 0){
                    return;
                };
            }
            String fileText = psiFile.getText();
            // FileOutputStream outputStream = new FileOutputStream(file);

            OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "utf-8");


            oStreamWriter.write(fileText);
            oStreamWriter.close();
            // outputStream.write(fileText.getBytes());
            // outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void savePsiToFile(List<PsiFile> psiFile, String filePath) {
        try {
            String str = filePath.substring(0, filePath.indexOf(":"));

            // FileOutputStream outputStream = null;

            for (PsiFile psiFiles1:psiFile) {
                String str1 = (filePath.substring(str.length()+1)) + "\\" + psiFiles1.getName();
                File file = new File(str1);
                if (file.exists()) {
                    if(showInputDialog() != 0){
                        return;
                    };
                }
            }

            for (PsiFile psiFiles1:psiFile) {
                String str1 = (filePath.substring(str.length()+1)) + "\\" + psiFiles1.getName();
                File file = new File(str1);
                if (!file.exists()) {
                    file.createNewFile();
                }
                String fileText = psiFiles1.getText();
                // outputStream = new FileOutputStream(file);


                OutputStreamWriter oStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "utf-8");

                oStreamWriter.write(fileText);

                oStreamWriter.close();
                // outputStream.write(fileText.getBytes());
            }

            // outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int showInputDialog() {
        int result = JOptionPane.showConfirmDialog(null, "文件已存在是否替换？", null, JOptionPane.OK_CANCEL_OPTION,0);
        return result;
    }

    public static String showInputDialogInfoAll() {
        JTextField field = new JTextField();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("请输入包名，使用.分割。如:cn.baidu"), BorderLayout.NORTH);
        panel.add(field, BorderLayout.SOUTH);
        int result = JOptionPane.showConfirmDialog(null, panel, "信息输入框", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            // System.out.println("User entered: " + field.getText());
            return field.getText();
        }
        return null;
    }

    public static void createFolders(String path){ //项目路径，到src的上一级
        String inputPath = showInputDialogInfoAll();        //cn.ccwcy
        String str1 = inputPath.substring(0, inputPath.indexOf("."));   //cn
        String str2 = (inputPath.substring(str1.length()+1, inputPath.length()));   //ccwcy

        String str11 = path.substring(0, path.indexOf(":"));
        String str22 = (path.substring(str11.length()+1, path.length()));


        //新建包cn.ccwcy
        String completePath = str22 + "\\" + str1 + "\\" + str2;
        File targetFile = new File(completePath);
        if (!targetFile.exists()){
            targetFile.mkdirs();
        }

        //新建controller
        String controllerPath = completePath + "\\controller";
        File controllertargetFile = new File(controllerPath);
        if (!controllertargetFile.exists()){
            controllertargetFile.mkdirs();
        }

        //新建mapper
        String mapperPath = completePath + "\\mapper";
        File mappertargetFile = new File(mapperPath);
        if (!mappertargetFile.exists()){
            mappertargetFile.mkdirs();
        }

        //新建pojo
        String pojoPath = completePath + "\\pojo";
        File pojotargetFile = new File(pojoPath);
        if (!pojotargetFile.exists()){
            pojotargetFile.mkdirs();
        }

        //新建service
        String servicePath = completePath + "\\service";
        File servicetargetFile = new File(servicePath);
        if (!servicetargetFile.exists()){
            servicetargetFile.mkdirs();
        }

        //新建utils
        String utilsPath = completePath + "\\utils";
        File utilstargetFile = new File(utilsPath);
        if (!utilstargetFile.exists()){
            utilstargetFile.mkdirs();
        }
    }
    public static void createStiticFolders(String path) { //右键位置的路径

        String str11 = path.substring(0, path.indexOf(":"));
        String str22 = (path.substring(str11.length() + 1));


        //在右键位置新建包resources
        String completePath = str22 + "\\resources";
        File targetFile = new File(completePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
    }
}