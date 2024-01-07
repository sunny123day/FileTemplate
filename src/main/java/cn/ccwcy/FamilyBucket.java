package cn.ccwcy;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.lang.xml.XMLLanguage;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FamilyBucket extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getProject();

        String inputPath = SavePsiToFile.showInputDialogInfoAll();
        if (inputPath == null){
            return;
        }

        //获取右键菜单选取的路径
        Navigatable selectedElement = event.getData(CommonDataKeys.NAVIGATABLE);

        List<PsiFile> psiFile = new ArrayList<>();
        String[] configAll = new String[]{
                "applicationContext-mybatis",
                "database",
                "log4j",
                "mybatis-config",
                "springmvc-servlet"
        };
        String[] configAllInfo = new String[]{
                "applicationContext-mybatis.xml",
                "database.properties",
                "log4j.properties",
                "mybatis-config.xml",
                "springmvc-servlet.xml"
        };

        for (int i = 0; i < configAll.length; i++) {
            //读取模板
            FileTemplate template = FileTemplateManager.getInstance(project)
                    .getInternalTemplate(configAll[i]);
            String renderedText = "";

            //替换模板里的参数
            Properties properties = new Properties();
            properties.setProperty("PACKAGE_NAME", inputPath);
            try {
                renderedText += template.getText(properties);
                // renderedText += template.getText();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            PsiFile file = PsiFileFactory.getInstance(project).createFileFromText(configAllInfo[i], XMLLanguage.INSTANCE, renderedText);
            psiFile.add(file);
        }
        if (selectedElement != null){
            SavePsiToFile.savePsiToFile(psiFile,selectedElement.toString());
        }

    }
}
