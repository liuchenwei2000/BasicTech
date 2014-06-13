/**
 * 
 */
package dom;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * DOM输出XML示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年1月24日
 */
public class DOMTest4Write {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String xmlFilePath = "files/output.xml";
		try {
			// 1，建立 DocumentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// 2，建立 DocumentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 3，建立空的 Document
			Document document = builder.newDocument();
			// 4，建立各个操作节点
			Element books = document.createElement("books");
			Element book = document.createElement("book");
			Element name = document.createElement("name");
			Element author = document.createElement("author");
			// 5，设置节点的文本内容
			name.appendChild(document.createTextNode("Thinking in Java"));
			author.appendChild(document.createTextNode("Eckl Bruce"));
			// 6，组织各个节点的关系
			book.appendChild(name);
			book.appendChild(author);
			books.appendChild(book);
			// 7，将根节点绑定到 Document 对象上
			document.appendChild(books);
			// 8，输出到硬盘
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "GB2312");// 设置编码方式
			
			transformer.transform(new DOMSource(document), new StreamResult(new File(xmlFilePath)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}