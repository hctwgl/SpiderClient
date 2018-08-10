package org.spider.ui.eclipse.spidermanager.helper;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.spider.client.SpiderDefine;
import org.spider.client.SpiderDefine.RenderConfig;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ReadRenderConfigFile {

	public RenderConfig read(String filePath) {
		RenderConfig renderCfg = null;
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
				String renderCmd = eElement.getElementsByTagName("RenderCmd").item(0).getTextContent();
				renderCfg = new SpiderDefine().new RenderConfig(renderCmd); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return renderCfg;
	}
}

