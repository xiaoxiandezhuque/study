package com.xh.chajian


import com.android.build.gradle.api.BaseVariant
import org.gradle.api.Plugin
import org.gradle.api.Project

class MultiChannelPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        applyExtension(project)
        applyTask(project)
    }

    void applyExtension(Project project) {
        project.extensions.create("flutterMultiChannel", Extension, project)
    }

    void applyTask(Project project) {
        project.afterEvaluate {
            project.android.applicationVariants.all { BaseVariant variant ->
                def flavorName = variant.flavorName
                def variantName = variant.name.capitalize();
                ChannelHandle channelHandle = project.tasks.create("flutterChannel${variantName}Handle", ChannelHandle)
                channelHandle.targetProject = project
                channelHandle.variant = variant


                project.tasks.getByName("pre${variantName}Build").dependsOn(channelHandle)

//                project.tasks.getByName("compileFlutterBuild$variantName").dependsOn(channelHandle)

            }
        }
    }

}