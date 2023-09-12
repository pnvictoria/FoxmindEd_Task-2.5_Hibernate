package com.example.dao.impl;

import com.example.dao.GroupDAO;
import com.example.exception.SchoolDAOException;
import com.example.model.Group;
import com.example.model.dto.GroupStudentCount;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.constant.QueryConstants.*;

@Repository
public class GroupDAOImpl implements GroupDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Group> findById(Integer id) throws SchoolDAOException {
        Group group = em.createQuery(GROUP_GET_OBJECT_BY_ID, Group.class)
                .setParameter("group_id", id)
                .getSingleResult();
        return Optional.ofNullable(group);
    }

    @Override
    public List<Group> findAll() throws SchoolDAOException {
        return em.createQuery(GROUP_GET_ALL_OBJECTS, Group.class)
                .getResultList();
    }

    @Override
    public void deleteById(Integer id) throws SchoolDAOException {
        em.createQuery(GROUP_REMOVE_OBJECT, Group.class)
                .setParameter("group_id", id);
    }

    @Override
    public List<Group> saveAll(List<Group> entities) throws SchoolDAOException {
        return entities.stream().map(this::save).toList();
    }

    @Override
    public List<GroupStudentCount> getGroupsByStudentCount(int count) throws SchoolDAOException {
        return em.createQuery(GROUP_GET_BY_STUDENT_COUNT, GroupStudentCount.class)
                .setParameter("count", count)
                .getResultList();
    }

    @Override
    public Group create(Group entity) throws SchoolDAOException {
        return em.createQuery(GROUP_ADD_OBJECT, Group.class)
                .setParameter("group_name", entity.getName())
                .getSingleResult();
    }

    @Override
    public Group update(Group entity) throws SchoolDAOException {
        return em.createQuery(GROUP_UPDATE_OBJECT, Group.class)
                .setParameter("group_name", entity.getName())
                .setParameter("group_id", entity.getId())
                .getSingleResult();
    }
}
