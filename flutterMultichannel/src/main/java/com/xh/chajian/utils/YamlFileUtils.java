package com.xh.chajian.utils;

import com.xh.chajian.bean.FileBean;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class YamlFileUtils {

    public static void writYamlFile(String yamlPath, List<FileBean> list, boolean isClearOldData) {
        if (yamlPath.isEmpty()) {
            throw new NullPointerException("yaml path not found=" + yamlPath);
        }
        File yamlFile = new File(yamlPath);
        if (!yamlFile.exists()) {
            throw new NullPointerException("yaml file not exist=" + yamlPath);
        }
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(yamlFile, StandardCharsets.UTF_8));
            List<String> sourceFileLineList = bufferedReader.lines().collect(Collectors.toList());
            int assetsIndex = -1;
            for (int i = 0; i < sourceFileLineList.size(); i++) {
                if (sourceFileLineList.get(i).replace(" ", "").equals("assets:")) {
                    assetsIndex = i;
                    break;
                }
            }
            if (assetsIndex == -1) {
                throw new IllegalStateException("没有找到 assets");
            }
            //清除之前有的
            if (isClearOldData) {
                clearAssetsOldData(sourceFileLineList, assetsIndex);
            }
            getNewFileData(list, sourceFileLineList, assetsIndex);

            bufferedWriter = new BufferedWriter(new FileWriter(yamlFile, StandardCharsets.UTF_8));
            for (String str : sourceFileLineList) {
                bufferedWriter.write(str);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();

        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }

                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

    }

    private static void clearAssetsOldData(
            List<String> targetFileLineList,
            int assetsIndex
    ) {
        List<String> delList = new ArrayList<>();
        for (int i = assetsIndex + 1; i < targetFileLineList.size(); i++) {
            if (targetFileLineList.get(i).startsWith("    - ")) {
                delList.add(targetFileLineList.get(i));
            } else {
                break;
            }
        }
        targetFileLineList.removeAll(delList);
    }

    //获取新的文件数据
    private static void getNewFileData(
            List<FileBean> sourceData,
            List<String> lineList,
            int assetsIndex
    ) {
        int index = 0;

        for (int i = 0; i < sourceData.size(); i++) {
            FileBean fileBean = sourceData.get(i);
            String insertElement = "    - " +
                    fileBean.dir.replaceFirst("/", "") + "/" +
                    fileBean.name;
            lineList.add(assetsIndex + index + 1, insertElement);
            index++;
        }

    }
}
