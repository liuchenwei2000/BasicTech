/**
 * 
 */
package jdom;

import java.io.FileOutputStream;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

/**
 * JDOM 输出 XML 示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年1月26日
 */
public class WriteXMLTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filePath = "files/jdom_test.xml";
		
		Element books = new Element("books");// 定义节点
		
		Element book1 = new Element("book");
		Element book2 = new Element("book");
		
		Element book1Name = new Element("name");
		Attribute id1 = new Attribute("id", "001");// 定义属性，名值对形式
		book1Name.setAttribute(id1);
		book1Name.setText("Thinking in Java");// 设置节点内容
		
		Element book1Author = new Element("author");
		book1Author.setText("Eckl Bruce");
		
		book1.addContent(book1Name);
		book1.addContent(book1Author);
		
		Element book2Name = new Element("name");
		Attribute id2 = new Attribute("id", "002");
		book2Name.setAttribute(id2);
		book2Name.setText("XML Cookbook");
		
		Element book2Author = new Element("author");
		book2Author.setText("Unknown");
		
		book2.addContent(book2Name);
		book2.addContent(book2Author);
		
		books.addContent(book1);
		books.addContent(book2);
		
		Document document = new Document(books);// 使用根节点创建 Document 对象
		
		XMLOutputter outputter = new XMLOutputter();// 输出XML的对象
		outputter.setFormat(outputter.getFormat().setEncoding("GBK"));// 设置编码方式
		try {
			outputter.output(document, new FileOutputStream(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
