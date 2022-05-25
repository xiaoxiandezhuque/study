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

public class FlutterOtherHelper extends FlutterFileHelper {


    public FlutterOtherHelper(ConfigBean configBean) {
        super(configBean);
    }

    @Override
    boolean needClearYamlOldData() {
        return false;
    }

    @Override
    String[] ignorePaths() {
        return new String[0];
    }


    @Override
    public String handleRes() {
        return "other";
    }
}
