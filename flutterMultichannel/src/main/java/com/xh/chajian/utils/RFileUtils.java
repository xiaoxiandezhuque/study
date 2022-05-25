package com.xh.chajian.utils;

import com.xh.chajian.bean.WriteAttrBean;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class RFileUtils {

//    public static void createDartClass(String path, String fileName, String className, List<WriteAttrBean> list) throws IOException {
//        File targetFileDir = new File(path);
//
//        if (!targetFileDir.exists()) {
//            Files.createDirectories(targetFileDir.toPath());
//        }
//
//        File targetFile = new File(targetFileDir, fileName);
//
//        if (targetFile.exists()) {
//            targetFile.delete();
//        }
//
//        targetFile.createNewFile();
//
//        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), StandardCharsets.UTF_8));
//
//        out.write("class " + className + " {");
//
//        for (WriteAttrBean bean : list) {
//            out.write("\n\tstatic const String " + bean.key + " = \"" + bean.value + "\";");
//        }
//        out.write("\n}");
//        out.flush();
//        out.close();
//    }


    public static void createDartClass(String path, String fileName, String importPackage,
                                       String className,
                                       String attrClassName,
                                       List<WriteAttrBean> list) throws IOException {
        File targetFileDir = new File(path);

        if (!targetFileDir.exists()) {
            Files.createDirectories(targetFileDir.toPath());
        }

        File targetFile = new File(targetFileDir, fileName);

        if (targetFile.exists()) {
            targetFile.delete();
        }

        targetFile.createNewFile();

        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), StandardCharsets.UTF_8));
        if (!StringUtils.isEmpty(importPackage)) {
            out.write(importPackage + "\n");
        }
        out.write("class " + className + " {");

        if ("String".equals(attrClassName)) {
            for (WriteAttrBean bean : list) {
                out.write("\n\tstatic const String " + bean.key + " = \"" + bean.value + "\";");
            }
        } else {
            for (WriteAttrBean bean : list) {
                out.write("\n\tstatic const " + attrClassName + " " + bean.key + " = " + attrClassName + "(" + bean.value + ");");
            }
        }

        out.write("\n}");
        out.flush();
        out.close();
    }
}
