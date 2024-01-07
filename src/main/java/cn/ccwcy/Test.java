package cn.ccwcy;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;

public class Test extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent event) {
        //创建并显示对话框
        Project currentProject = event.getProject();
        StringBuilder message =
                new StringBuilder(event.getPresentation().getText() + " Selected!");
        //如果选中了一个元素，追加该元素的信息
        Navigatable selectedElement = event.getData(CommonDataKeys.NAVIGATABLE);
        if (selectedElement != null) {
            message.append("\nSelected Element: ").append(selectedElement);
        }
        Messages.showMessageDialog(
                currentProject,
                message.toString(),
                "title",
                Messages.getInformationIcon());

        /*//获取根目录
        String vFile1s1 = ProjectRootManager.getInstance(project).getContentRootUrls().toString();
        String str1 = vFile1s1.substring(0, vFile1s1.indexOf("//"));
        String str2 = (vFile1s1.substring(str1.length()+2, vFile1s1.length()));
        String str3 = str2.substring(0, str2.indexOf("]"));*/

    }
}
