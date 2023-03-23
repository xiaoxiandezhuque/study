package com.xh.chajian.utils;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XmlUtils {


    public static Element parse(String path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory xmlDomFactory = DocumentBuilderFactory.newInstance();
        Document document = xmlDomFactory.newDocumentBuilder().parse(path);
        document.getDocumentElement().normalize();
        return document.getDocumentElement();
    }

    public static void parseFile(HashMap<String, String> hashMap, String path, String keyName, boolean isHasAndAdd) throws ParserConfigurationException, IOException, SAXException {
        Element element = parse(path);
        NodeList nodeList = element.getElementsByTagName(keyName);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element keyElement = (Element) nodeList.item(i);
            String key = keyElement.getAttribute("name");
            String value = keyElement.getFirstChild().getNodeValue();
            if (isHasAndAdd) {
                hashMap.put(key, value);
            } else {
                if (hashMap.get(key) == null) {
                    hashMap.put(key, value);
                }
            }
        }
    }
}
