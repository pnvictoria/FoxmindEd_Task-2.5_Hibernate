package com.example.dao.impl;

import com.example.dao.CourseDAO;
import com.example.exception.SchoolDAOException;
import com.example.model.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.constant.QueryConstants.*;

@Repository
public class CourseDAOImpl implements CourseDAO  {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Course> saveAll(List<Course> entities) throws SchoolDAOException {
        return entities.stream().map(this::save).toList();
    }

    @Override
    public List<Course> getCoursesByStudentId(int id) throws SchoolDAOException {
        return em.createQuery(COURSE_GET_BY_STUDENT_ID, Course.class)
                .setParameter("student_id", id)
                .getResultList();
    }

    @Override
    public Optional<Course> findById(Integer id) throws SchoolDAOException {
        Course course = em.createQuery(COURSE_GET_OBJECT_BY_ID, Course.class)
                .setParameter("course_id", id)
                .getSingleResult();
        return Optional.ofNullable(course);

    }

    @Override
    public List<Course> findAll() throws SchoolDAOException {
        return em.createQuery(COURSE_GET_ALL_OBJECTS, Course.class)
                .getResultList();
    }

    @Override
    public void deleteById(Integer id) throws SchoolDAOException {
        em.createQuery(COURSE_REMOVE_OBJECT, Course.class)
                .setParameter("course_id", id);
    }

    @Override
    public Course create(Course entity) throws SchoolDAOException {
        return em.createQuery(COURSE_ADD_OBJECT, Course.class)
                .setParameter("course_name", entity.getName())
                .setParameter("course_description", entity.getDescription())
                .getSingleResult();
    }

    @Override
    public Course update(Course entity) throws SchoolDAOException {
        return em.createQuery(COURSE_UPDATE_OBJECT, Course.class)
                .setParameter("course_name", entity.getName())
                .setParameter("course_description", entity.getDescription())
                .setParameter("course_id", entity.getId())
                .getSingleResult();
        }
}
