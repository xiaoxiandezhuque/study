package com.xh.chajian;

import com.xh.chajian.bean.ConfigBean;
import com.xh.chajian.bean.FileBean;
import com.xh.chajian.bean.WriteAttrBean;
import com.xh.chajian.utils.RFileUtils;
import com.xh.chajian.utils.YamlFileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public abstract class FlutterFileHelper implements ResHelper {
    private ConfigBean configBean;

    public FlutterFileHelper(ConfigBean configBean) {
        this.configBean = configBean;
    }

    abstract boolean needClearYamlOldData();

    abstract String[] ignorePaths();

    public void startWork() {
        File baseFile = new File(configBean.handleDir + File.separator + handleRes());
        if (!baseFile.exists()) {
            return;
        }
        HashMap<String, FileBean> imgMap = new HashMap<>();
        getAllFiles(baseFile, imgMap);

        List<FileBean> imgList = toList(imgMap);
        String yamlPath = (configBean.targetDir + File.separator + "pubspec.yaml").replace("\\", "/");

        YamlFileUtils.writYamlFile(yamlPath, imgList, needClearYamlOldData());

        String dartPath = configBean.targetDir + File.separator + Constants.dart_file_path;

        List<WriteAttrBean> writeAttrList = new ArrayList<>();
        for (FileBean bean : imgList) {
            writeAttrList.add(new WriteAttrBean(bean.name.split("\\.")[0], bean.dir.replaceFirst("/", "") + "/" + bean.name));
        }

        try {
            RFileUtils.createDartClass(dartPath, handleRes() + ".dart", null,
                    "R" + handleRes().substring(0, 1).toUpperCase() + handleRes().substring(1), "String", writeAttrList);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }


    private void getAllFiles(File file, HashMap<String, FileBean> hashMap) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                getAllFiles(f, hashMap);
            }
        } else {
            String dir = file.getParentFile().getAbsolutePath().replace("\\", "/")
                    .replace(configBean.targetDir.replace("\\", "/"), "");

            for (String path : ignorePaths()) {
                dir = dir.replace(path, "");
            }

            boolean isHas = hasFlavorName(dir, "", configBean.flavorName);
            if (isHas) {
                hashMap.put(file.getName(), new FileBean(dir, file.getName()));
            } else {
                if (hashMap.get(file.getName()) == null) {
                    hashMap.put(file.getName(), new FileBean(dir, file.getName()));
                }
            }
        }

    }

    private List<FileBean> toList(HashMap<String, FileBean> hashMap) {
        ArrayList<String> arrayList = new ArrayList(hashMap.keySet());
        Collections.sort(arrayList);
        List<FileBean> resultList = new ArrayList<>(hashMap.size());
        for (String key : arrayList) {
            resultList.add(hashMap.get(key));
        }
        return resultList;
    }

}
