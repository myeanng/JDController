package com.test.main;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.bean.impl.Cust;
import com.test.helper.ObjectToString;
import com.test.helper.XMLConverter;

public class OXM_Main {
	private static final String XML_FILE_NAME = "customer.xml";

	public static void main(String[] args) throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		XMLConverter converter = (XMLConverter) context.getBean("xMLConverter");
		Cust Cust = new Cust();
		Cust.setName("Cust");
		Cust.setAge(27);
		Cust.setFlag(true);
		Cust.setAdd("he na");
		// from object to XML file
		converter.convertFromObjectToXML(Cust, XML_FILE_NAME);
		System.out.println("Done");
		ObjectToString os=new ObjectToString();
		// from XML to object
		Cust customer2 = (Cust) converter.convertFromXMLToObject(XML_FILE_NAME);
		System.out.println(os.getFiledsInfo(customer2));
		System.out.println("Done");
	}

}
