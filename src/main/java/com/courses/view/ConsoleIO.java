package com.example.view;

import com.example.exception.SchoolDAOException;
import com.example.model.Course;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class ConsoleIO {
    private final Scanner scanner;

    public ConsoleIO() {
        scanner = new Scanner(System.in);
    }

    public String getLetterInput() {
        return scanner.next();
    }

    public int getNumberInput() throws SchoolDAOException {
        int number = 0;
        while (number == 0) {
            try {
                number = Integer.parseInt(scanner.next());
            } catch (Exception e) {
                throw new SchoolDAOException("Please enter a number.");
            }
        }
        return number;
    }

    public String getCourseNameInput(List<Course> courses) throws SchoolDAOException {
        List<String> coursesNames = getCourseNameList(courses);
        String result = "";
        while (true) {
            result = scanner.next();
            if (coursesNames.contains(result))
                break;
            throw new SchoolDAOException("Incorrect course.");
        }
        return result;
    }

    public String getStringInput() {
        return scanner.next();
    }

    public void close() {
        scanner.close();
    }

    public int getNumberByMaxSizeInput(int maxSize) throws SchoolDAOException {
        int result = 0;
        while (true) {
            result = getNumberInput();
            if (result <= maxSize && result > 0)
                break;
            throw new SchoolDAOException("Incorrect id.");
        }
        return result;
    }

    private List<String> getCourseNameList(List<Course> course) {
        return course.stream()
                .map(Course::getName)
                .collect(Collectors.toList());
    }
}
