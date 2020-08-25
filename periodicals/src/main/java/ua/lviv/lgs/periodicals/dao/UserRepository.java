package ua.lviv.lgs.periodicals.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.lgs.periodicals.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
