package org.example;
import java.util.Random;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class DomProcess {
    public static void test() throws Exception{
        // 读取XML1文档
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document xml1Doc = dBuilder.parse("XML1.xml");
        xml1Doc.getDocumentElement().normalize();

        // 获取根节点
        Element rootElement = xml1Doc.getDocumentElement();

        // 创建新的XML文档
        DocumentBuilderFactory newDbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder newDBuilder = newDbFactory.newDocumentBuilder();
        Document newDoc = newDBuilder.newDocument();
        Element newRoot = newDoc.createElement("StudentReport");
        newDoc.appendChild(newRoot);

        // 将XML1中的学生信息添加到新文档
        NodeList studentsList = rootElement.getElementsByTagName("Student");
        for (int i = 0; i < studentsList.getLength(); i++) {
            Node node = studentsList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Element newElement = (Element) newDoc.importNode(element, true);
                newRoot.appendChild(newElement);
            }
        }

        // 随机生成9名学生信息和成绩
        Random random = new Random();
        for (int i = 0; i < 9; i++) {
            Element studentElement = newDoc.createElement("Student");

            Element studentID = newDoc.createElement("StudentID");
            studentID.appendChild(newDoc.createTextNode("1000" + i));
            studentElement.appendChild(studentID);

            Element firstName = newDoc.createElement("FirstName");
            firstName.appendChild(newDoc.createTextNode("FirstName" + i));
            studentElement.appendChild(firstName);

            Element lastName = newDoc.createElement("LastName");
            lastName.appendChild(newDoc.createTextNode("LastName" + i));
            studentElement.appendChild(lastName);

            Element grades = newDoc.createElement("Grades");
            for (int j = 0; j < 5; j++) {
                Element course = newDoc.createElement("Course");

                Element courseName = newDoc.createElement("CourseName");
                courseName.appendChild(newDoc.createTextNode("Course" + j));
                course.appendChild(courseName);

                Element regularGrade = newDoc.createElement("RegularGrade");
                int regularScore = random.nextInt(101); // 随机生成0-100的整数
                regularGrade.appendChild(newDoc.createTextNode(String.valueOf(regularScore)));
                course.appendChild(regularGrade);

                Element finalGrade = newDoc.createElement("FinalGrade");
                int finalScore = random.nextInt(101);
                finalGrade.appendChild(newDoc.createTextNode(String.valueOf(finalScore)));
                course.appendChild(finalGrade);

                Element totalGrade = newDoc.createElement("TotalGrade");
                int totalScore = (regularScore + finalScore) / 2; // 平均成绩
                totalGrade.appendChild(newDoc.createTextNode(String.valueOf(totalScore)));
                course.appendChild(totalGrade);

                grades.appendChild(course);
            }

            studentElement.appendChild(grades);

            newRoot.appendChild(studentElement);
        }

        // 保存新生成的文档
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(newDoc);
        StreamResult result = new StreamResult("FinalStudentReport.xml");
        transformer.transform(source, result);

        System.out.println("生成成功！");
    }
}
