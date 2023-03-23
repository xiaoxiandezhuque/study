package com.xh.chajian;

import com.xh.chajian.bean.ConfigBean;


public class FlutterStringHelper extends FlutterParseXmlHelper {

    public FlutterStringHelper(ConfigBean configBean) {
        super(configBean);
    }

    @Override
    String getImportPackage() {
        return null;
    }

    @Override
    String getAttrClassName() {
        return "String";
    }

    @Override
    public String handleRes() {
        return "string";
    }

}
