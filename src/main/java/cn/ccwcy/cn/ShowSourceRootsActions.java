package cn.ccwcy.cn;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ShowSourceRootsActions extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getProject();
        if (project == null) {
            return;
        }
        String projectName = project.getName();
        StringBuilder sourceRootsList = new StringBuilder();
        VirtualFile[] vFiles = ProjectRootManager.getInstance(project).getContentSourceRoots();
        List<String> vFile1s = ProjectRootManager.getInstance(project).getContentRootUrls();
        String vFile1s1 = ProjectRootManager.getInstance(project).getContentRootUrls().toString();
        String str1 = vFile1s1.substring(0, vFile1s1.indexOf("//"));
        String str2 = (vFile1s1.substring(str1.length()+2, vFile1s1.length()));
        String str3 = str2.substring(0, str2.indexOf("]"));

        List<String> list = new ArrayList<>();
        for (VirtualFile file : vFiles) {
            list.add(file.getUrl());
        }
              Messages.showInfoMessage(
                "Source roots for the " + projectName + " plugin:\n" + sourceRootsList,
                "Project Properties"
        );
    }

    @Override
    public void update(@NotNull final AnActionEvent event) {
        boolean visibility = event.getProject() != null;
        event.getPresentation().setEnabled(visibility);
        event.getPresentation().setVisible(visibility);
    }
}