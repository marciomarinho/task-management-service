package au.com.riosoftware.task.repository;

import au.com.riosoftware.task.model.User;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepository extends Repository<User, Long> {
    User save(User user);
    User findById(long id);
    List<User> findAll();
}