package org.example;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class ConvertToXML3 {
    public static void convertToXML3() throws Exception {
        try {
            // 读取XML1文档
            File file = new File("src/main/java/org/example/XML1.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document xml1Doc = dBuilder.parse(file);
            Element studentElement = (Element) xml1Doc.getElementsByTagName("StudentInfo").item(0);

            // 创建新的XML3
            Document xml3Doc = dBuilder.newDocument();
            Element studentReportElement = xml3Doc.createElement("StudentReport");
            studentReportElement.setAttribute("xmlns", "http://www.example.com/studentReport");
            studentReportElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            studentReportElement.setAttribute("xsi:schemaLocation", "http://www.example.com/studentReport StudentReport.xsd");
            studentReportElement.setAttribute("xmlns:sc","http://www.example.com/schoolArchives");
            xml3Doc.appendChild(studentReportElement);


            int[] courseIds = new int[5];
            // 生成10名学生信息和成绩
            for (int i = 0; i < 9; i++) {
                Element studentGradeElement = xml3Doc.createElement("StudentGrade");
                Element studentElementTmp;
                if(i > 0)studentElementTmp = generateRandomStudent(xml3Doc);
                else {
                    studentElementTmp = xml3Doc.createElement("StudentInfo");
                    Element personalInfoElement = (Element) studentElement.getElementsByTagName("PersonalInfo").item(0);
                    Element academicInfoElement = (Element) studentElement.getElementsByTagName("AcademicInfo").item(0);
                    personalInfoElement.setAttribute("xmlns","http://www.example.com/schoolArchives");
                    academicInfoElement.setAttribute("xmlns","http://www.example.com/schoolArchives");

                    studentElementTmp.appendChild(xml3Doc.importNode(personalInfoElement, true));
                    studentElementTmp.appendChild(xml3Doc.importNode(academicInfoElement, true));
                }

                // 随机生成成绩数据
                Element gradesElement = xml3Doc.createElement("Grades");
                Element[] courseElements = new Element[5];
                for (int j = 0; j < 5; j++) {
                    courseElements[j] = xml3Doc.createElement("Course");
                    int courseId;
                    if(i == 0){
                        courseId = getRandomScore();
                        courseIds[j] = courseId;
                    }else courseId = courseIds[j];
                    int usualScore = getRandomScore();
                    int finalScore = getRandomScore();
                    int totalScore = (int) (usualScore * 0.3 + finalScore * 0.7);
                    Element courseIdElement = xml3Doc.createElement("CourseId");
                    courseIdElement.setTextContent(Integer.toString(courseId));

                    Element usualScoreElement = xml3Doc.createElement("UsualScore");
                    usualScoreElement.setTextContent(Integer.toString(usualScore));

                    Element finalScoreElement = xml3Doc.createElement("FinalScore");
                    finalScoreElement.setTextContent(Integer.toString(finalScore));

                    Element totalScoreElement = xml3Doc.createElement("TotalScore");
                    totalScoreElement.setTextContent(Integer.toString(totalScore));

                    courseElements[j].appendChild(courseIdElement);
                    courseElements[j].appendChild(usualScoreElement);
                    courseElements[j].appendChild(finalScoreElement);
                    courseElements[j].appendChild(totalScoreElement);

                    gradesElement.appendChild(courseElements[j]);
                }
                studentGradeElement.appendChild(studentElementTmp);
                studentGradeElement.appendChild(gradesElement);
                studentReportElement.appendChild(studentGradeElement);
            }

            // 保存新生成的文档
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // 启用自动换行功能
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2"); // 设置缩进的空格数
            DOMSource source = new DOMSource(xml3Doc);
            StreamResult result = new StreamResult(new File("src/main/java/org/example/XML3.xml"));
            transformer.transform(source, result);
            System.out.println("XML3生成成功！");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getRandomScore() {
        Random random = new Random();
        return random.nextInt(101);
    }

    private static Element generateRandomStudent(Document document) {
        Element studentElement = document.createElement("StudentInfo");

        // 随机生成个人信息
        Element personalInfoElement = document.createElement("PersonalInfo");
        personalInfoElement.setAttribute("xmlns","http://www.example.com/schoolArchives");
        personalInfoElement.appendChild(createElementWithTextContent(document, "FirstName", "FirstName" + getRandomName()));
        personalInfoElement.appendChild(createElementWithTextContent(document, "LastName", "LastName" + getRandomName()));
        personalInfoElement.appendChild(createElementWithTextContent(document, "DateOfBirth", getRandomDateOfBirth()));
        personalInfoElement.appendChild(createElementWithTextContent(document, "Address", "Address" + getRandomLocation()));
        studentElement.appendChild(personalInfoElement);

        // 随机生成学术信息
        Element academicInfoElement = document.createElement("AcademicInfo");
        academicInfoElement.setAttribute("xmlns","http://www.example.com/schoolArchives");
        academicInfoElement.appendChild(createElementWithTextContent(document, "StudentID", getRandomStudentID()));
        academicInfoElement.appendChild(createElementWithTextContent(document, "Department", "Department" + getRandomDepartment()));
        academicInfoElement.appendChild(createElementWithTextContent(document, "Major", "Major" + getRandomMajor()));
        academicInfoElement.appendChild(createElementWithTextContent(document, "GPA", getRandomGPA()));
        studentElement.appendChild(academicInfoElement);

        return studentElement;
    }

    private static Element createElementWithTextContent(Document document, String tagName, String textContent) {
        Element element = document.createElement(tagName);
        element.setTextContent(textContent);
        return element;
    }

    private static String getRandomName() {
        return "Name" + (new Random().nextInt(1000));
    }

private static String getRandomDateOfBirth() {
    // 创建一个 Calendar 对象
    Calendar calendar = Calendar.getInstance();

    // 设置随机年份，假设范围为 1950 年至 2003 年
    int year = 1950 + new Random().nextInt(54); // 1950 + 0 到 53

    // 设置随机月份，假设范围为 1 到 12 月
    int month = 1 + new Random().nextInt(12); // 1 到 12

    // 设置随机日期，根据月份确定最大日期
    int maxDay = 31; // 默认为 31 天
    if (month == 4 || month == 6 || month == 9 || month == 11) {
        maxDay = 30;
    } else if (month == 2) {
        // 对 2 月进行特殊处理，通常为 28 天
        maxDay = 28;
        if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
            // 如果是闰年，2 月为 29 天
            maxDay = 29;
        }
    }
    int day = 1 + new Random().nextInt(maxDay); // 1 到 maxDay

    // 设置 Calendar 对象的日期
    calendar.set(year, month - 1, day);

    // 将 Calendar 对象转换为日期字符串
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = calendar.getTime();
    return dateFormat.format(date);
}

    private static String getRandomLocation() {
        return "Location" + (new Random().nextInt(100));
    }

    private static String getRandomStudentID() {
        return String.valueOf(200000000 + new Random().nextInt(9999999));
    }

    private static String getRandomDepartment() {
        return "Department" + (new Random().nextInt(10));
    }

    private static String getRandomMajor() {
        return "Major" + (new Random().nextInt(10));
    }

    private static String getRandomGPA() {
        return String.format("%.1f", 2.0 + new Random().nextDouble() * 2.0);
    }
}
