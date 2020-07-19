package com.example.SecuritySpringDataSpecifications.Services;

import com.example.SecuritySpringDataSpecifications.models.Role;
import com.example.SecuritySpringDataSpecifications.models.User;
import com.example.SecuritySpringDataSpecifications.repositories.RoleRepository;
import com.example.SecuritySpringDataSpecifications.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DAOUserServiceImlementation implements DAOUSerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> findAllUsers() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> rootFrom = query.from(User.class);

        query.select(rootFrom);

        TypedQuery<User> resultList = entityManager.createQuery(query);
        List<User> responseList = resultList.getResultList();
        log.info("{} users was extracted successful", resultList.getResultList().size());
        return responseList;
    }

    @Override
    public void registerUser(User user) {
        Role roleUser = roleRepository.findRoleByName("ROLE_USER").get();
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);

        userRepository.save(user);

        log.info("user was saved successful");
    }

    @Override
    public User findUserByUsername(String username) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> rootFrom = query.from(User.class);

        query.select(rootFrom).where(criteriaBuilder.equal(rootFrom.get("username"), username));
        TypedQuery<User> result = entityManager.createQuery(query);

        if (result == null) {
            log.warn("user with username: {} was NOT found - returned new user instance", username);
            return new User();
        } else {
            User response = result.getSingleResult();
            log.info("User with name - {} was recieved", username);
            return response;
        }
    }

    @Override
    public void deleteUser(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaDelete<User> query = criteriaBuilder.createCriteriaDelete(User.class);
        Root<User> rootFrom = query.from(User.class);

        query.where(criteriaBuilder.equal(rootFrom.get("id"), id));
        entityManager.createQuery(query).executeUpdate();
        log.info("user with id {} was deleted successful", id);
    }

    @Override
    public User findUserById(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> rootFrom = query.from(User.class);

        query.where(criteriaBuilder.equal(rootFrom.get("id"), id));
        TypedQuery<User> result = entityManager.createQuery(query);
        if (result == null) {
            log.warn("user with id : {} was NOT found - returned new user instance", id);
            return new User();
        } else {
            User response = result.getSingleResult();

            log.info("user with id {} was found", id);
            return response;
        }
    }
}
