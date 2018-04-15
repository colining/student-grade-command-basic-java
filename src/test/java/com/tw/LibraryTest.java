package com.tw;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static junit.framework.TestCase.assertTrue;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class LibraryTest {
    private Library library = null;
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        library = new Library();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testSomeLibraryMethod() {
        Library classUnderTest = new Library();
        assertTrue("someLibraryMethod should return 'true'", classUnderTest.someLibraryMethod());
    }

    @Test
    public void testMockClass() {
        // you can mock concrete classes, not only interfaces
        LinkedList mockedList = mock(LinkedList.class);

        // stubbing appears before the actual execution
        String value = "first";
        when(mockedList.get(0)).thenReturn(value);

        assertEquals(mockedList.get(0), value);

    }

    @Test
    public void testPrintHelp() {
        library.printHelp();
        assertThat(systemOut()).isEqualTo("1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3）：\n");

    }

    @Test
    public void testAddStudentCMD() {
        library.addStudentCMD();
        assertThat(systemOut()).isEqualTo("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：\n");

    }


    private String systemOut() {
        return outContent.toString();
    }

    @Test
    public void testGenerateScore() {
        library.addStudent("张三,1,数学:75,语文:95,英语:80,编程:80");
        library.generateScore("1");
        assertThat(systemOut()).isEqualTo("学生张三的成绩被添加\n" +
                "成绩单\n" +
                "姓名|数学|语文|英语|编程|平均分|总分\n" +
                "========================\n" +
                "张三|75.0|95.0|80.0|80.0|82.5|330.0\n" +
                "========================\n" +
                "全班总分平均数：330.0\n" +
                "全班总分中位数：330.0\n");
    }

    @Test
    public void testGenerateScore_should_return_error_info() {
        library.generateScore("test");
        assertThat(systemOut()).isEqualTo("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：\n");
    }

    @Test
    public void testAddStudent() {
        library.addStudent("张三,1,数学:75,语文:95,英语:80,编程:80");
        assertThat(systemOut()).isEqualTo("学生张三的成绩被添加\n");
    }

    @Test
    public void testAddStudent_should_return_error_info() {
        library.addStudent("test");
        assertThat(systemOut()).isEqualTo("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：\n");
    }

    @Test
    public void testGenerateScore_should_return_two_student() {
        library.addStudent("张三,1,数学:75,语文:95,英语:80,编程:80");
        library.addStudent("李四,2,数学:85,语文:80,英语:70,编程:90");
        library.generateScore("1,2");
        assertThat(systemOut()).isEqualTo("学生张三的成绩被添加\n" +
                "学生李四的成绩被添加\n" +
                "成绩单\n" +
                "姓名|数学|语文|英语|编程|平均分|总分\n" +
                "========================\n" +
                "张三|75.0|95.0|80.0|80.0|82.5|330.0\n" +
                "李四|85.0|80.0|70.0|90.0|81.25|325.0\n" +
                "========================\n" +
                "全班总分平均数：327.5\n" +
                "全班总分中位数：330.0\n");
    }

    @Test
    public void testGenerateScore_should_return_just_two_student() {
        library.addStudent("张三,1,数学:75,语文:95,英语:80,编程:80");
        library.addStudent("李四,2,数学:85,语文:80,英语:70,编程:90");
        library.generateScore("1,2,3");
        assertThat(systemOut()).isEqualTo("学生张三的成绩被添加\n" +
                "学生李四的成绩被添加\n" +
                "成绩单\n" +
                "姓名|数学|语文|英语|编程|平均分|总分\n" +
                "========================\n" +
                "张三|75.0|95.0|80.0|80.0|82.5|330.0\n" +
                "李四|85.0|80.0|70.0|90.0|81.25|325.0\n" +
                "========================\n" +
                "全班总分平均数：327.5\n" +
                "全班总分中位数：330.0\n");
    }
}
