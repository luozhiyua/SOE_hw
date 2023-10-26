package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.*;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

public class ConvertToXML4 {
    public static void convertToXML4() throws Exception{
        try {
            // Step 1: Parse XML3
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc3 = builder.parse(new File("src/main/java/org/example/XML3.xml"));

            // Step 2: Create XML4 and sort courses by TotalScore
            Document doc4 = builder.newDocument();
            Element courseReportElement = doc4.createElementNS("http://www.example.com/courseReport", "CourseReport");
            courseReportElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            courseReportElement.setAttribute("xsi:schemaLocation", "http://www.example.com/courseReport CourseReport.xsd");
            doc4.appendChild(courseReportElement);

            NodeList studentGrades = doc3.getElementsByTagName("StudentGrade");
            Map<Integer, List<Element>> coursesByCourseId = new HashMap<>();

            for (int i = 0; i < studentGrades.getLength(); i++) {
                Element studentGrade = (Element) studentGrades.item(i);
                NodeList courses = studentGrade.getElementsByTagName("Course");
                Element studentID = (Element) studentGrade.getElementsByTagName("StudentID").item(0);

                for (int j = 0; j < courses.getLength(); j++) {
                    Element course = (Element) courses.item(j);

                    // TODO : think about it
                    //  course.appendChild(studentID);  并且是 doc3 而非 doc4
                    Element studentIdElement = doc3.createElement("StudentID");
                    studentIdElement.setTextContent(studentID.getTextContent());
                    course.appendChild(studentIdElement);

                    int courseId = Integer.parseInt(course.getElementsByTagName("CourseId").item(0).getTextContent());
                    coursesByCourseId.computeIfAbsent(courseId, k -> new ArrayList<>()).add(course);
                }
            }

            // Sort courses within each CourseId group by TotalScore
            for (List<Element> courseList : coursesByCourseId.values()) {
                courseList.sort((course1, course2) -> {
                    int score1 = Integer.parseInt(course1.getElementsByTagName("TotalScore").item(0).getTextContent());
                    int score2 = Integer.parseInt(course2.getElementsByTagName("TotalScore").item(0).getTextContent());
                    return Integer.compare(score2, score1);
                });
            }

            // Create the sorted XML4
            for (List<Element> courseList : coursesByCourseId.values()) {
                Element courseGradeElement = doc4.createElement("CourseGrade");
                boolean courseInfoflag = true;
                for (Element course : courseList) {
                    if(courseInfoflag){
                        Element courseInfoElement = doc4.createElement("CourseInfo");
                        courseInfoElement.appendChild(createElement(doc4,"CourseId",course));
                        courseGradeElement.appendChild(courseInfoElement);
                        courseInfoflag = false;
                    }

                    Element stuGraInfoElement = doc4.createElement("StuGraInfo");
                    stuGraInfoElement.appendChild(createElement(doc4,"StudentID",course));
                    stuGraInfoElement.appendChild(createElement(doc4,"UsualScore",course));
                    stuGraInfoElement.appendChild(createElement(doc4,"FinalScore",course));
                    stuGraInfoElement.appendChild(createElement(doc4,"TotalScore",course));

                    courseGradeElement.appendChild(stuGraInfoElement);
                }
                courseReportElement.appendChild(courseGradeElement);
            }

            // Step 3: Save XML4 to a file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // 启用自动换行功能
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2"); // 设置缩进的空格数
            transformer.transform(new DOMSource(doc4), new StreamResult(new File("src/main/java/org/example/XML4.xml")));
            System.out.println("XML4生成成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Element createElement(Document doc,String tagName,Element course){
        Element element = doc.createElement(tagName);
        element.setTextContent(course.getElementsByTagName(tagName).item(0).getTextContent());
        return element;
    }
}
