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

public class WriteUploadConfigFile {

	public void write(String filePath, SpiderDefine.UploadConfig uploadCfg) {

	  try {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();

		// config elements
		Element config = doc.createElement("config");
		doc.appendChild(config);

		Element title = doc.createElement("vTitle");
		title.appendChild(doc.createTextNode(uploadCfg.titleTemp));
		config.appendChild(title);

		Element desc = doc.createElement("vDesc");
		desc.appendChild(doc.createTextNode(uploadCfg.descTemp));
		config.appendChild(desc);

		Element tags = doc.createElement("vTags");
		tags.appendChild(doc.createTextNode(uploadCfg.tagTemp));
		config.appendChild(tags);

		Element enableTitle = doc.createElement("enableTitle");
		if(uploadCfg.enableTitle)
		{
			enableTitle.appendChild(doc.createTextNode("" + 1));	
		}else{
			enableTitle.appendChild(doc.createTextNode("" + 0));
		}
		config.appendChild(enableTitle);
		
		Element enableDesc = doc.createElement("enableDesc");
		if(uploadCfg.enableDesc)
		{
			enableDesc.appendChild(doc.createTextNode("" + 1));	
		}else{
			enableDesc.appendChild(doc.createTextNode("" + 0));
		}
		config.appendChild(enableDesc);
		
		Element enableTags = doc.createElement("enableTags");
		if(uploadCfg.enableTag)
		{
			enableTags.appendChild(doc.createTextNode("" + 1));	
		}else{
			enableTags.appendChild(doc.createTextNode("" + 0));
		}
		config.appendChild(enableTags);

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