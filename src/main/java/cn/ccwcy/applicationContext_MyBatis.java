package cn.ccwcy;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.lang.xml.XMLLanguage;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiFileFactory;

import java.io.IOException;
import java.util.Properties;

public class applicationContext_MyBatis extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getProject();


        //获取右键菜单选取的路径
        Navigatable selectedElement = event.getData(CommonDataKeys.NAVIGATABLE);

        //读取模板
        FileTemplate template = FileTemplateManager.getInstance(project)
                .getInternalTemplate("applicationContext-mybatis");
        String renderedText = "";

        String path = SavePsiToFile.showInputDialogInfoAll();
        if (path == null){
            return;
        }
        //替换模板里的参数
        Properties properties = new Properties();
        properties.setProperty("PACKAGE_NAME", path);
        try {
            renderedText += template.getText(properties);
            // renderedText += template.getText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        PsiFile psiFile = PsiFileFactory.getInstance(project).createFileFromText("applicationContext-mybatis.xml", XMLLanguage.INSTANCE, renderedText);

        if (selectedElement != null){
            SavePsiToFile.savePsiToFile(psiFile,selectedElement.toString());
        }

    }
}
