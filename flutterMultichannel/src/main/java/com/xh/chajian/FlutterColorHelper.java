package com.xh.chajian;

import com.xh.chajian.bean.ConfigBean;


public class FlutterColorHelper extends FlutterParseXmlHelper {

    public FlutterColorHelper(ConfigBean configBean) {
        super(configBean);
    }

    @Override
    String getImportPackage() {
        return "import 'dart:ui';";
    }

    @Override
    String getAttrClassName() {
        return "Color";
    }

    @Override
    public String handleRes() {
        return "color";
    }

}
