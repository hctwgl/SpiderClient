package org.spider.ui.eclipse.spidermanager.helper;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.spider.client.SpiderDefine;
import org.spider.client.SpiderDefine.UploadConfig;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ReadUploadConfigFile {

	public UploadConfig read(String filePath) {
		UploadConfig uploadCfg = null;
		try {

			File fXmlFile = new File(filePath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("config");
			System.out.println("----------------------------");

			Node nNode = nList.item(0);
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String vTitle = eElement.getElementsByTagName("vTitle").item(0).getTextContent();
				String vDesc = eElement.getElementsByTagName("vDesc").item(0).getTextContent();
				String vTags = eElement.getElementsByTagName("vTags").item(0).getTextContent();
				int enableTitle = Integer.parseInt(eElement.getElementsByTagName("enableTitle").item(0).getTextContent());
				int enableDesc = Integer.parseInt(eElement.getElementsByTagName("enableDesc").item(0).getTextContent());
				int enableTags = Integer.parseInt(eElement.getElementsByTagName("enableTags").item(0).getTextContent());	
				uploadCfg = new SpiderDefine().new UploadConfig(vTitle, vDesc, vTags, 
						enableTitle == 1, enableDesc == 1, enableTags == 1); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uploadCfg;
	}
}

