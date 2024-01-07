package cn.ccwcy;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.pom.Navigatable;
import org.jetbrains.annotations.NotNull;

public class CreateResourcesPack extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) {
            return;
        }

        Navigatable selectedElement = e.getData(CommonDataKeys.NAVIGATABLE);
        if (selectedElement != null){
            SavePsiToFile.createStiticFolders(selectedElement.toString());
        }
    }
}
