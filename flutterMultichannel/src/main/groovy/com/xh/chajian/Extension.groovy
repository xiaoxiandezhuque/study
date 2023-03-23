package com.xh.chajian

import org.gradle.api.Project

class Extension {

//  flutter 工程的目录
    String flutterPath

    Extension(Project project) {

    }

    public static Extension getConfig(Project project) {
        Extension config =
                project.getExtensions().findByType(Extension.class);
        if (config == null) {
            config = new Extension();
        }
        return config;
    }
}
