/**
 * 
 */
package jdom;

import java.io.File;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

/**
 * JDOM 读取 XML 示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年1月26日
 */
public class ReadXMLTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filePath = "files/jdom_test.xml";
		
		SAXBuilder builder = new SAXBuilder();// 建立SAX解析
		try {
			Document document = builder.build(new File(filePath));
			Element books = document.getRootElement();// 读取根元素
			
			/* 
			 * 在JDOM中，所有节点都是以集合的形式返回的，集合中的每一个对象都是Element实例，
			 * 通过Element实例可以取得全部XML文件中的元素内容及属性内容。
			 */
			List<Element> bookList = books.getChildren("book");// 得到所有 book 子元素
			
			for (Element book : bookList) {
				Element name = book.getChild("name"); 
				System.out.println("id = " + name.getAttributeValue("id"));
				System.out.println("name = " + name.getText());
				
				Element author = book.getChild("author"); 
				System.out.println("author = " + author.getText());
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
