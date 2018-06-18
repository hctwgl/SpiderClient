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

		Element intro = doc.createElement("vIntro");
		intro.appendChild(doc.createTextNode(renderCfg.vIntro));
		config.appendChild(intro);

		Element outro = doc.createElement("vOutro");
		outro.appendChild(doc.createTextNode(renderCfg.vOutro));
		config.appendChild(outro);

		Element logo = doc.createElement("vLogo");
		logo.appendChild(doc.createTextNode(renderCfg.vLogo));
		config.appendChild(logo);

		Element enableIntro = doc.createElement("enableIntro");
		enableIntro.appendChild(doc.createTextNode("" + renderCfg.enableIntro));
		config.appendChild(enableIntro);
		
		Element enableOutro = doc.createElement("enableOutro");
		enableOutro.appendChild(doc.createTextNode("" + renderCfg.enableOutro));
		config.appendChild(enableOutro);
		
		Element enableLogo = doc.createElement("enableLogo");
		enableLogo.appendChild(doc.createTextNode("" + renderCfg.enableLogo));
		config.appendChild(enableLogo);

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