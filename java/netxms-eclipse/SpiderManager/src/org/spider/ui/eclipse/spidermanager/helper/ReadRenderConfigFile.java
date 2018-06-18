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
				String vIntro = eElement.getElementsByTagName("vIntro").item(0).getTextContent();
				String vOutro = eElement.getElementsByTagName("vOutro").item(0).getTextContent();
				String vLogo = eElement.getElementsByTagName("vLogo").item(0).getTextContent();
				int enableIntro = Integer.parseInt(eElement.getElementsByTagName("enableIntro").item(0).getTextContent());
				int enableOutro = Integer.parseInt(eElement.getElementsByTagName("enableOutro").item(0).getTextContent());
				int enableLogo = Integer.parseInt(eElement.getElementsByTagName("enableLogo").item(0).getTextContent());	
				renderCfg = new SpiderDefine().new RenderConfig(vIntro, vOutro, vLogo, 
						enableIntro == 1, enableOutro == 1, enableLogo == 1); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return renderCfg;
	}
}

