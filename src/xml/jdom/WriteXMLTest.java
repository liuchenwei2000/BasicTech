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
 * JDOM ��� XML ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��1��26��
 */
public class WriteXMLTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filePath = "files/jdom_test.xml";
		
		Element books = new Element("books");// ����ڵ�
		
		Element book1 = new Element("book");
		Element book2 = new Element("book");
		
		Element book1Name = new Element("name");
		Attribute id1 = new Attribute("id", "001");// �������ԣ���ֵ����ʽ
		book1Name.setAttribute(id1);
		book1Name.setText("Thinking in Java");// ���ýڵ�����
		
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
		
		Document document = new Document(books);// ʹ�ø��ڵ㴴�� Document ����
		
		XMLOutputter outputter = new XMLOutputter();// ���XML�Ķ���
		outputter.setFormat(outputter.getFormat().setEncoding("GBK"));// ���ñ��뷽ʽ
		try {
			outputter.output(document, new FileOutputStream(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
