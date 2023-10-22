package org.example;

import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlVlidate {
    //输入XDS和XML文件。来验证XML文件的有效性
    public static boolean validate(String xsdPath,String xmlPath){
        boolean flag = false;
        try {
            //1.创建模式工厂
            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            //2.通过XSD文件创建模式Scheme
            File file = new File(xsdPath);
            Schema schema = schemaFactory.newSchema(file);
            //3.由模式创建验证器Validator
            Validator validator = schema.newValidator();
            //4.使用验证器验证xml文件
            Source source = new StreamSource(xmlPath);
            validator.validate(source);
            flag = true;
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("[Debug]xsd文件在验证xml文件时出错");
            e.printStackTrace();
        }
        return flag;
    }
}
