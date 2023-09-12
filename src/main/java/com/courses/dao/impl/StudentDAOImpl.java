package com.example.dao.impl;

import com.example.dao.StudentDAO;
import com.example.exception.SchoolDAOException;
import com.example.model.Course;
import com.example.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.constant.QueryConstants.*;

@Repository
public class StudentDAOImpl implements StudentDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Student> findById(Integer id) throws SchoolDAOException {
        Student student = em.createQuery(STUDENT_GET_OBJECT_BY_ID, Student.class)
                .setParameter("student_id", id)
                .getSingleResult();
        return Optional.ofNullable(student);
    }

    @Override
    public List<Student> findAll() throws SchoolDAOException {
        return em.createQuery(STUDENT_GET_ALL_OBJECTS, Student.class)
                .getResultList();
    }

    @Override
    public void deleteById(Integer id) throws SchoolDAOException {
        em.createQuery(STUDENT_REMOVE_OBJECT, Course.class)
                .setParameter("student_id", id);
    }

    @Override
    public List<Student> saveAll(List<Student> entities) throws SchoolDAOException {
        return entities.stream().map(this::save).toList();
    }

    @Override
    public List<Student> getStudentsByCourseName(String courseName) throws SchoolDAOException {
        return em.createQuery(STUDENT_BY_COURSE_NAME, Student.class)
                .setParameter("course_name", courseName)
                .getResultList();
    }

    @Override
    public void assignStudentsToCourse(int studentId, int courseId) throws SchoolDAOException {
        em.createQuery(STUDENT_ASSIGN_TO_COURSE, Student.class)
                .setParameter("student_id", studentId)
                .setParameter("course_id", courseId)
                .getResultList();
    }

    @Override
    public void deleteStudentFromCourse(int studentId, int courseId) throws SchoolDAOException {
        em.createQuery(STUDENT_DELETE_FROM_COURSE, Student.class)
                .setParameter("student_id", studentId)
                .setParameter("course_id", courseId)
                .getResultList();
    }

    @Override
    public void assignStudentsToCourse(List<Student> students) throws SchoolDAOException {
        for (Student student : students) {
            for (Course course : student.getCourses()) {
                em.createQuery(STUDENT_ASSIGN_TO_COURSE, Student.class)
                        .setParameter("student_id", student.getId())
                        .setParameter("course_id", course.getId())
                        .getResultList();
            }
        }
    }

    @Override
    public Student create(Student entity) throws SchoolDAOException {
        return em.createQuery(STUDENT_ADD_OBJECT, Student.class)
                .setParameter("group_id", entity.getId())
                .setParameter("first_name", entity.getFirstName())
                .setParameter("last_name", entity.getLastName())
                .getSingleResult();
    }

    @Override
    public Student update(Student entity) throws SchoolDAOException {
        return em.createQuery(STUDENT_UPDATE_OBJECT, Student.class)
                .setParameter("group_id", entity.getId())
                .setParameter("first_name", entity.getFirstName())
                .setParameter("last_name", entity.getLastName())
                .setParameter("student_id", entity.getId())
                .getSingleResult();
    }
}
