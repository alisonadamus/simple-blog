package com.alisonadamus.simple_blog.repositories;

import com.alisonadamus.simple_blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
