package com.example.securityrole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UzenetRepo extends CrudRepository<Uzenet, Integer> {
    @Query("SELECT u FROM Uzenet u ORDER BY u.created_at DESC")
    List<Uzenet> findAllOrderByCreatedAtDesc();
}
