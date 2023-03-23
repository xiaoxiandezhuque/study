package com.xh.chajian;

import com.xh.chajian.bean.FileBean;
import com.xh.chajian.utils.StringUtils;

public interface ResHelper {

    String handleRes();

    void startWork();

    default boolean hasFlavorName(String path, String ignorePath, String flavorName) {
        String dir = path.replace("\\", "/")
                .replace(ignorePath.replace("\\", "/"), "");

        String[] dirNames = dir.split("/");

        if (dirNames.length > 3 && !StringUtils.isEmpty(flavorName)) {
            if (flavorName.toLowerCase().contains(dirNames[3])) {
                return true;
            }
        }
        return false;
    }

}
