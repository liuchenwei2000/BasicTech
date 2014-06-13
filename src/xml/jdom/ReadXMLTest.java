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
 * JDOM ��ȡ XML ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��1��26��
 */
public class ReadXMLTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filePath = "files/jdom_test.xml";
		
		SAXBuilder builder = new SAXBuilder();// ����SAX����
		try {
			Document document = builder.build(new File(filePath));
			Element books = document.getRootElement();// ��ȡ��Ԫ��
			
			/* 
			 * ��JDOM�У����нڵ㶼���Լ��ϵ���ʽ���صģ������е�ÿһ��������Elementʵ����
			 * ͨ��Elementʵ������ȡ��ȫ��XML�ļ��е�Ԫ�����ݼ��������ݡ�
			 */
			List<Element> bookList = books.getChildren("book");// �õ����� book ��Ԫ��
			
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
