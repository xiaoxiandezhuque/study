package com.xh.chajian;

import com.xh.chajian.bean.ConfigBean;
import com.xh.chajian.bean.WriteAttrBean;
import com.xh.chajian.utils.RFileUtils;
import com.xh.chajian.utils.XmlUtils;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;


public abstract class FlutterParseXmlHelper implements ResHelper {

    private ConfigBean configBean;

    public FlutterParseXmlHelper(ConfigBean configBean) {
        this.configBean = configBean;
    }

    abstract String getImportPackage();

    abstract String getAttrClassName();

    public void startWork() {
        File baseFile = new File(configBean.handleDir + File.separator + handleRes());
        if (!baseFile.exists()) {
            return;
        }
        HashMap<String, String> stringMap = new HashMap<>();
        getAllStrings(baseFile, stringMap);

        String dartPath = configBean.targetDir + File.separator + Constants.dart_file_path;
        List<WriteAttrBean> writeAttrList = new ArrayList<>();
        List<String> keyList = new ArrayList(stringMap.keySet());
        Collections.sort(keyList);
        for (String key : keyList) {
            writeAttrList.add(new WriteAttrBean(key, stringMap.get(key)));
        }

        try {
            RFileUtils.createDartClass(dartPath,
                    handleRes() + ".dart",
                    getImportPackage(),
                    "R" + handleRes().substring(0, 1).toUpperCase() + handleRes().substring(1),
                    getAttrClassName(),
                    writeAttrList);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void getAllStrings(File file, HashMap<String, String> hashMap) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                getAllStrings(f, hashMap);
            }
        } else {
            try {
                boolean isHas = hasFlavorName(file.getAbsolutePath(), configBean.targetDir, configBean.flavorName);
                XmlUtils.parseFile(hashMap, file.getAbsolutePath(), handleRes(), isHas);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            }
        }
    }

}
