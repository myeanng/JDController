package com.test.helper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

public class XMLConverter {
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;

	public Marshaller getMarshaller() {
		return marshaller;
	}

	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	public void convertFromObjectToXML(Object object, String filepath) throws IOException {
		System.out.println("Convert Object to XML Begin!");
		FileOutputStream os = null;
		try {
			os = new FileOutputStream(filepath);
			getMarshaller().marshal(object, new StreamResult(os));
		} finally {
			if (os != null) {
				os.close();
			}
			System.out.println("Convert Object to XML End!");
		}
	}

	public Object convertFromXMLToObject(String xmlfile) throws IOException {
		System.out.println("Convert XML to Object Begin!");
		FileInputStream is = null;
		try {
			is = new FileInputStream(xmlfile);
			StreamSource streamSource = new StreamSource(is);
			Object obj = this.unmarshaller.unmarshal(streamSource);
			return obj;
		} finally {
			if (is != null) {
				is.close();
			}
			System.out.println("Convert XML to Object End!");
		}
	}

	public XMLConverter(Marshaller marshaller, Unmarshaller unmarshaller) {
		super();
		this.marshaller = marshaller;
		this.unmarshaller = unmarshaller;
	}

	public XMLConverter() {
		super();
	}

}
