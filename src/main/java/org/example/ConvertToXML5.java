package org.example;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ConvertToXML5{
    public static void convertToXML5(){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            MyHandler handler = new MyHandler();
            saxParser.parse(new File("src/main/java/org/example/XML4.xml"), handler);

            // 获取解析结果
            List<CourseGrade> result = handler.getResult();
            // 将结果转换为XML格式并保存到XML5.xml文件
            convertToXML(result, "src/main/java/org/example/XML5.xml");
            System.out.println("XML5生成成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void convertToXML(List<CourseGrade> result, String filename) throws IOException {
        // 创建一个StringBuilder来构建XML字符串
        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
        xmlBuilder.append("<CourseReport xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.example.com/courseReport CourseReport.xsd\" xmlns=\"http://www.example.com/courseReport\">");

        for (CourseGrade courseGrade : result) {
            xmlBuilder.append("<CourseGrade>");
            xmlBuilder.append("<CourseInfo>");
            xmlBuilder.append("<CourseId>").append(courseGrade.getCourseId()).append("</CourseId>");
            xmlBuilder.append("</CourseInfo>");

            for (StuGraInfo stuGraInfo : courseGrade.getStuGraInfoList()) {
                xmlBuilder.append("<StuGraInfo>");
                xmlBuilder.append("<StudentID>").append(stuGraInfo.getStudentId()).append("</StudentID>");
                xmlBuilder.append("<UsualScore>").append(stuGraInfo.getUsualScore()).append("</UsualScore>");
                xmlBuilder.append("<FinalScore>").append(stuGraInfo.getFinalScore()).append("</FinalScore>");
                xmlBuilder.append("<TotalScore>").append(stuGraInfo.getTotalScore()).append("</TotalScore>");
                xmlBuilder.append("</StuGraInfo>");
            }

            xmlBuilder.append("</CourseGrade>");
        }

        xmlBuilder.append("</CourseReport>");

        // 将XML字符串保存到文件
        FileWriter writer = new FileWriter(filename);
        writer.write(xmlBuilder.toString());
        writer.close();
    }

    private static class MyHandler extends DefaultHandler {
        private List<CourseGrade> result;
        private CourseGrade currentCourseGrade;
        private StuGraInfo currentStuGraInfo;
        private boolean hasFailingGrade;
        private boolean isTotalScore;
        private boolean isUsualScore;
        private boolean isFinalScore;
        private boolean isStudentId;
        private boolean isCourseId;




        public MyHandler() {
            result = new ArrayList<>();
            currentCourseGrade = null;
            currentStuGraInfo = null;
            hasFailingGrade = false;
            isTotalScore = false;
            isUsualScore = false;
            isFinalScore = false;
            isStudentId = false;
            isCourseId = false;
        }

        public List<CourseGrade> getResult() {
            return result;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes){
            switch (qName) {
                case "CourseGrade":
                    currentCourseGrade = new CourseGrade();
                    break;
                case "StuGraInfo":
                    currentStuGraInfo = new StuGraInfo();
                    break;
                case "TotalScore":
                    isTotalScore = true;
                    break;
                case "UsualScore":
                    isUsualScore = true;
                    break;
                case "FinalScore":
                    isFinalScore = true;
                    break;
                case "StudentID":
                    isStudentId = true;
                    break;
                case "CourseId":
                    isCourseId = true;
                    break;
            }

        }

        @Override
        public void endElement(String uri, String localName, String qName){
            switch (qName) {
                case "CourseGrade":
                    if (hasFailingGrade) {
                        result.add(currentCourseGrade);
                    }
                    currentCourseGrade = null;
                    hasFailingGrade = false;
                    break;
                case "StuGraInfo":
                    currentCourseGrade.addStuGraInfo(currentStuGraInfo);
                    currentStuGraInfo = null;
                    break;
                case "TotalScore":
                    int score = Integer.parseInt(currentStuGraInfo.getTotalScore());
                    if (score < 60) {
                        hasFailingGrade = true;
                    }
                    isTotalScore = false;
                    break;
                case "UsualScore":
                    isUsualScore = false;
                    break;
                case "FinalScore":
                    isFinalScore = false;
                    break;
                case "StudentID":
                    isStudentId = false;
                    break;
                case "CourseId":
                    isCourseId = false;
                    break;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length){
            String text = new String(ch, start, length);
            if (currentStuGraInfo != null) {
                if(isTotalScore) currentStuGraInfo.setTotalScore(text);
                else if(isUsualScore)currentStuGraInfo.setUsualScore(text);
                else if(isFinalScore)currentStuGraInfo.setFinalScore(text);
                else if(isStudentId)currentStuGraInfo.setStudentId(text);
            }else if(currentCourseGrade != null){
                if(isCourseId) currentCourseGrade.setCourseId(text);
            }
        }
    }

    private static class CourseGrade {
        private String courseId;
        private List<StuGraInfo> stuGraInfoList;

        public CourseGrade() {
            stuGraInfoList = new ArrayList<>();
        }

        public String getCourseId() {
            return courseId;
        }

        public void setCourseId(String courseId) {
            this.courseId = courseId;
        }

        public List<StuGraInfo> getStuGraInfoList() {
            return stuGraInfoList;
        }

        public void addStuGraInfo(StuGraInfo stuGraInfo) {
            stuGraInfoList.add(stuGraInfo);
        }
    }

    private static class StuGraInfo {
        private String studentId;
        private String usualScore;
        private String finalScore;
        private String totalScore;

        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }

        public String getUsualScore() {
            return usualScore;
        }

        public void setUsualScore(String usualScore) { this.usualScore = usualScore; }

        public String getFinalScore() {
            return finalScore;
        }

        public void setFinalScore(String finalScore) {
            this.finalScore = finalScore;
        }

        public String getTotalScore() {
            return totalScore;
        }

        public void setTotalScore(String totalScore) {
            this.totalScore = totalScore;
        }

    }
}