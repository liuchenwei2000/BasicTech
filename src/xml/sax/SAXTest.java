/**
 * 
 */
package sax;

import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX解析XML示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年1月24日
 */
public class SAXTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String xmlFilePath = "files/demo.xml";
		try {
			// 1，建立 SAXParserFactory
			SAXParserFactory factory = SAXParserFactory.newInstance();
			// 2，构造解析器 SAXParser
			SAXParser parser = factory.newSAXParser();
			// 3，使用 DefaultHandler 解析XML
			parser.parse(new File(xmlFilePath), new MySAX());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static class MySAX extends DefaultHandler {

		@Override
		public void startDocument() throws SAXException {
			System.out.println("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		}

		@Override
		public void endDocument() throws SAXException {
			System.out.println("\n 文档读取结束");
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			System.out.print("<" + qName);
			if (attributes != null) {
				for (int i = 0; i < attributes.getLength(); i++) {
					System.out.print(" " + attributes.getQName(i) + "=\""
							+ attributes.getValue(i) + "\"");
				}
			}
			System.out.print(">");
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			System.out.print("<" + qName + ">");
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			System.out.print(new String(ch, start, length));
		}
	}
}
