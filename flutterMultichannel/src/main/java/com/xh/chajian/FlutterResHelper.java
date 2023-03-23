package com.xh.chajian;

import com.xh.chajian.bean.ConfigBean;
import com.xh.chajian.utils.StringUtils;

import java.io.File;

public class FlutterResHelper implements ResHelper {

    private ConfigBean configBean;
    private ResHelper[] resHelpers;

    public FlutterResHelper(ConfigBean configBean) {
        this.configBean = configBean;
        resHelpers = new ResHelper[]{
                new FlutterImgHelper(configBean),
                new FlutterOtherHelper(configBean),
                new FlutterStringHelper(configBean),
                new FlutterColorHelper(configBean),
        };
    }

    @Override
    public String handleRes() {
        return "res";
    }

    public void startWork() {
        File projectFile = new File(configBean.projectDir);
        configBean.targetDir = configBean.targetDir.replace("\\", "/");
        File targetFile = handleSpotPath(configBean.targetDir, projectFile);
        File resFile = new File(targetFile.getAbsolutePath() + File.separator + handleRes());


        configBean.targetDir = targetFile.getAbsolutePath();
        configBean.handleDir = resFile.getAbsolutePath();
        System.out.println(configBean);
        for (ResHelper resHelper : resHelpers) {
            resHelper.startWork();
        }

    }

    private File handleSpotPath(String path, File baseFile) {
        if (!StringUtils.isEmpty(path) && path.startsWith("../")) {
            return handleSpotPath(path.replaceFirst("../", ""), baseFile.getParentFile());
        }
        return new File(baseFile.getAbsolutePath() + File.separator + path.replace("../", ""));
    }
}
