package com.xh.chajian.bean;

public class ConfigBean {

    //  D:\work\project\study\aop
    public String projectDir;
    public String targetDir;
    public String handleDir;
    public String flavorName;
    public String outDir;

    @Override
    public String toString() {
        return "ConfigBean{" +
                "projectDir='" + projectDir + '\'' +
                ", targetDir='" + targetDir + '\'' +
                ", handleDir='" + handleDir + '\'' +
                ", flavorName='" + flavorName + '\'' +
                ", outDir='" + outDir + '\'' +
                '}';
    }
}
