package com.company.learn.generate;

import freemarker.template.TemplateException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成service、dao、model 代码
 */
public class Generate {

    public static void main(String[] args) throws IOException, TemplateException, ParserConfigurationException, SAXException {
        String ftlPath = "";
        String ModelftlName = "";
        String fileName = "";
        String ModelfilePath = "";
        String ModelpackgeName = "";

        String DaoftlName = "";
        String DaofilePath = "";
        String DaopackgeName = "";

        String ServiceftlName = "";
        String ServicefilePath = "";
        String ServicepackgeName = "";

        List<Object> modellist = new ArrayList<Object>();

        File xmlFile = new File(System.getProperty("user.dir"), "\\src\\main\\java\\com\\company\\learn\\generate\\GenerateConf.xml");
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        Element rootElement = doc.getDocumentElement(); //获取根元素
        Node ftlnode = rootElement.getElementsByTagName("ftl").item(0);
        ftlPath = ((Element) ftlnode).getAttribute("path");
        NodeList params = ftlnode.getChildNodes();
        for (int i = 0; i < params.getLength(); i++) {
            Node node = params.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE) continue;
            Element e = (Element) node;
            if (e.getAttribute("name").trim().equals("model")) ModelftlName = node.getFirstChild().getNodeValue();
            if (e.getAttribute("name").trim().equals("dao")) DaoftlName = node.getFirstChild().getNodeValue();
            if (e.getAttribute("name").trim().equals("service")) ServiceftlName = node.getFirstChild().getNodeValue();
        }

        Node servicenode = rootElement.getElementsByTagName("service").item(0);
        ServicefilePath = ((Element) servicenode).getAttribute("path");
        ServicepackgeName = servicenode.getChildNodes().item(1).getFirstChild().getNodeValue();

        Node daonode = rootElement.getElementsByTagName("dao").item(0);
        DaofilePath = ((Element) daonode).getAttribute("path");
        DaopackgeName = daonode.getChildNodes().item(1).getFirstChild().getNodeValue();

        Node modelnode = rootElement.getElementsByTagName("model").item(0);
        ModelfilePath = ((Element) modelnode).getAttribute("path");
        params = modelnode.getChildNodes();
        for (int i = 0; i < params.getLength(); i++) {
            Node node = params.item(i);
            if (node.getNodeType() != Node.ELEMENT_NODE) continue;
            Element e = (Element) node;
            if (e.getNodeName().trim().equals("packageName")) ModelpackgeName = node.getFirstChild().getNodeValue();
            if (e.getNodeName().trim().equals("class")) fileName = node.getFirstChild().getNodeValue();
            if (e.getNodeName().trim().equals("field")) {
                NodeList attrnode = node.getChildNodes();
                Attr attr = new Attr();
                for (int j = 0; j < attrnode.getLength(); j++) {
                    Node anode = attrnode.item(j);
                    if (anode.getNodeType() != Node.ELEMENT_NODE) continue;
                    Element ae = (Element) anode;
                    if (ae.getTagName().trim().equals("fieldName")) attr.setField(anode.getFirstChild().getNodeValue());
                    if (ae.getTagName().trim().equals("fieldType")) attr.setType(anode.getFirstChild().getNodeValue());
                    if (ae.getTagName().trim().equals("fieldComment"))
                        attr.setComment(anode.getFirstChild().getNodeValue());
                }
                modellist.add(attr);
            }
        }

        generateModel(ftlPath, ModelftlName, fileName, ModelfilePath, ModelpackgeName, modellist);
        generateDao(ftlPath, DaoftlName, fileName, DaofilePath, DaopackgeName, ModelpackgeName);
        generateService(ftlPath, ServiceftlName, fileName, ServicefilePath, ServicepackgeName, DaopackgeName, ModelpackgeName);
    }

    private static void generateService(String ftlPath, String serviceftlName, String fileName, String servicefilePath, String servicepackgeName, String daopackgeName, String modelpackgeName) throws IOException, TemplateException {

        GenerateService.Generate(ftlPath, serviceftlName, fileName, servicefilePath, servicepackgeName, daopackgeName, modelpackgeName);
    }

    private static void generateDao(String ftlPath, String daoftlName, String fileName, String daofilePath, String daopackgeName, String modelpackgeName) throws IOException, TemplateException {

        GenerateDao.Generate(ftlPath, daoftlName, fileName, daofilePath, daopackgeName, modelpackgeName);
    }

    private static void generateModel(String ftlPath, String modelftlName, String fileName, String modelfilePath, String modelpackgeName, List<Object> modellist) throws IOException, TemplateException {

        GenerateModel.Generate(ftlPath, modelftlName, fileName, modelfilePath, modelpackgeName, modellist);
    }
}
