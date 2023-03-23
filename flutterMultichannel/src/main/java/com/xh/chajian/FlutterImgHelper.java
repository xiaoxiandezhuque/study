package com.xh.chajian;

import com.xh.chajian.bean.ConfigBean;
import com.xh.chajian.bean.FileBean;
import com.xh.chajian.bean.WriteAttrBean;
import com.xh.chajian.utils.RFileUtils;
import com.xh.chajian.utils.StringUtils;
import com.xh.chajian.utils.YamlFileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FlutterImgHelper extends FlutterFileHelper {

    public FlutterImgHelper(ConfigBean configBean) {
        super(configBean);
    }

    @Override
    boolean needClearYamlOldData() {
        return true;
    }

    @Override
    String[] ignorePaths() {
        return new String[]{"/1.0x","/2.0x","/3.0x","/4.0x"};
    }

    @Override
    public String handleRes() {
        return "img";
    }
}