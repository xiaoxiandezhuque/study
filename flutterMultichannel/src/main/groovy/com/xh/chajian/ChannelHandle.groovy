package com.xh.chajian

import com.android.build.gradle.api.BaseVariant
import com.xh.chajian.bean.ConfigBean
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

class ChannelHandle extends DefaultTask {


    @Input
    public BaseVariant variant
    @Input
    public Project targetProject

//    private String flavorName
//
//    @Inject
//    ChannelHandle(String flavorName) {
//        this.flavorName = flavorName
//    }


    @TaskAction
    public void handle() {
        Extension extension = Extension.getConfig(targetProject)
        String flavorName = variant.flavorName

        AAA aaa = new AAA()
        aaa.print(project.getProjectDir().getAbsolutePath())

        ConfigBean configBean = new ConfigBean()
        configBean.projectDir =project.getProjectDir().getAbsolutePath()
        configBean.flavorName = flavorName
        configBean.targetDir = extension.flutterPath

        FlutterResHelper flutterResHelper = new FlutterResHelper(configBean)
        flutterResHelper.startWork()

    }

}
