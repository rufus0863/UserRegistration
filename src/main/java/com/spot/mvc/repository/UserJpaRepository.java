package com.spot.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spot.mvc.dto.UserDTO;

@Repository
public interface UserJpaRepository extends JpaRepository<UserDTO, Long> {

    UserDTO findById(Long id);
    UserDTO findByName(String name);
    
    public UserDTO searchUserByEmail(String name);    
}
