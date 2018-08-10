package org.spider.ui.eclipse.spidermanager.helper;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.spider.client.SpiderDefine;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteRenderConfigFile {

	public void write(String filePath, SpiderDefine.RenderConfig renderCfg) {

	  try {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();

		// config elements
		Element config = doc.createElement("config");
		doc.appendChild(config);

		Element logo = doc.createElement("RenderCmd");
		logo.appendChild(doc.createTextNode(renderCfg.renderCmd));
		config.appendChild(logo);

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(filePath));

		transformer.transform(source, result);

		System.out.println("File saved!");

	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}
}