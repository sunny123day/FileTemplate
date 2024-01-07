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

import java.util.ArrayList;
import java.util.List;

public class web_xml extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getProject();


        //获取右键菜单选取的路径
        Navigatable selectedElement = event.getData(CommonDataKeys.NAVIGATABLE);

        //读取模板
        FileTemplate template = FileTemplateManager.getInstance(project)
                .getInternalTemplate("web");
        String renderedText = "";

        //替换模板里的参数
        /*Properties properties = new Properties();
        properties.setProperty("NAMESPACE", "1231231312");
        renderedText += template.getText(properties);*/

        renderedText += template.getText();

        PsiFile psiFile = PsiFileFactory.getInstance(project).createFileFromText("web.xml", XMLLanguage.INSTANCE, renderedText);

        if (selectedElement != null){
            SavePsiToFile.savePsiToFile(psiFile,selectedElement.toString());

        }

    }
}
