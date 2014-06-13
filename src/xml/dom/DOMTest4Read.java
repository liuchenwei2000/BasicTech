/**
 * 
 */
package dom;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * DOM解析XML示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年1月24日
 */
public class DOMTest4Read {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String xmlFilePath = "files/demo.xml";
		try {
			// 1，建立 DocumentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// 2，建立 DocumentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 3，建立 Document
			Document document = builder.parse(new File(xmlFilePath));
			// 4，建立 NodeList
			NodeList books = document.getElementsByTagName("book");
			// 5，进行XML信息读取
			for (int i = 0; i < books.getLength(); i++) {
				Element book = (Element)books.item(i);
				NodeList name = book.getElementsByTagName("name");
				NodeList author = book.getElementsByTagName("author");
				// 在DOM解析中，每一个节点中的内容实际上都是一个单独的文本节点
				// 取得 name 节点下的第一个子节点的第一个文本节点，并通过getNodeValue取得了节点内容
				System.out.println("name:" + name.item(0).getFirstChild().getNodeValue());
				System.out.println("author:" + author.item(0).getFirstChild().getNodeValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}