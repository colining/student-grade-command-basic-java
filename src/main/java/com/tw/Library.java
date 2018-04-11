package com.tw;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Library {
    private Map<Integer, Student> studentMap;
    private List<Double> sumList;

    Library() {
        sumList = new ArrayList<>();
        studentMap = new HashMap<>();
    }

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        library.printHelp();
        label:
        while (scanner.hasNext()) {
            String cmd = scanner.nextLine();
            switch (cmd) {
                case "1":
                    library.addStudentCMD();
                    String student = "";
                    while (scanner.hasNext()) {

                        if (library.checkAddStudentFormat(student = scanner.nextLine())) {
                            break;
                        }
                        System.out.println("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）: ");
                    }
                    library.addStudent(student);
                    break;
                case "2":
                    library.generateScoreCMD();
                    String studentIds = "";
                    while (scanner.hasNext()) {
                        //正确就生成成绩单
                        if (library.checkIdsFormat(studentIds = scanner.nextLine())) {
                            break;
                        }
                        System.out.println("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
                    }
                    library.generateScore(studentIds);
                    break;
                case "3":
                    break label;
            }
            library.printHelp();
        }
    }

    private boolean checkAddStudentFormat(String s) {
        String[] strings = s.split(",");
        return strings.length == 6;
    }

    void generateScore(String studentIds) {

        if (!checkIdsFormat(studentIds)) {
            System.out.println("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交：");
            return;
        }
        String stu[] = studentIds.split(",");
        double sum = getSum();
        double average = sum / sumList.size();
        double mid = getMid();
        String score = printScore(stu);
        if (score.length() == 0) {
            return;
        }
        System.out.println("成绩单\n" +
                "姓名|数学|语文|英语|编程|平均分|总分\n" +
                "========================");
        System.out.print(score);
        System.out.println("========================\n" +
                "全班总分平均数：" + average + "\n" +
                "全班总分中位数：" + mid);
    }

    private String printScore(String[] stu) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : stu) {
            int id = Integer.parseInt(s);
            if (!studentMap.containsKey(id)) {
                continue;
            }
            Student student = studentMap.get(id);
            stringBuilder.append(student.getName()).append("|").append(student.getMath()).append("|").append(student.getLanguage()).append("|").append(student.getEnglish()).append("|").append(student.getProgramming()).append("|").append(student.getAverage()).append("|").append(student.getSum()).append("\n");
        }
        return stringBuilder.toString();
    }

    private double getSum() {
        double sum = 0;
        for (Double aDouble : sumList) {
            sum += aDouble;
        }
        return sum;
    }

    private double getMid() {
        Collections.sort(sumList);
        double mid;
        if (sumList.size() % 2 == 0) {
            mid = sumList.get(sumList.size() / 2);
        } else if (sumList.size() == 1) {
            mid = sumList.get(0);
        } else {
            mid = (sumList.get(sumList.size() / 2) + sumList.get(sumList.size() / 2) + 1) / 2;
        }
        return mid;
    }


    private void generateScoreCMD() {
        System.out.println("请按正确的格式输入要打印的学生的学号（格式： 学号, 学号,...），按回车提交:");
    }

    void addStudent(String studentString) {
        if (!checkAddStudentFormat(studentString)) {
            System.out.println("请按正确的格式输入（格式：姓名, 学号, 学科: 成绩, ...）：");
            return;
        }
        Student student;
        String[] strings = studentString.split(",");
        String name = strings[0];
        int id = Integer.parseInt(strings[1]);
        double math = Double.parseDouble(strings[2].split(":")[1]);
        double language = Double.parseDouble(strings[3].split(":")[1]);
        double english = Double.parseDouble(strings[4].split(":")[1]);
        double programming = Double.parseDouble(strings[5].split(":")[1]);
        student = new Student(name, id, math, language, english, programming);
        studentMap.put(id, student);
        sumList.add(student.getSum());
        System.out.println("学生" + student.getName() + "的成绩被添加");


    }

    private boolean checkIdsFormat(String studentIds) {
        String regEx = "\\d+(,\\d+)*";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(studentIds);
        return mat.find();
    }

    boolean someLibraryMethod() {
        return true;
    }

    void printHelp() {
        System.out.println("1. 添加学生\n" +
                "2. 生成成绩单\n" +
                "3. 退出\n" +
                "请输入你的选择（1～3）：");
    }

    void addStudentCMD() {
        System.out.println("请输入学生信息（格式：姓名, 学号, 学科: 成绩, ...），按回车提交：");
    }
}
