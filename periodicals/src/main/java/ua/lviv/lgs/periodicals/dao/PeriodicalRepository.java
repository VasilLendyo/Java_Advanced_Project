package ua.lviv.lgs.periodicals.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ua.lviv.lgs.periodicals.domain.Periodical;

@Repository
public interface PeriodicalRepository extends JpaRepository<Periodical, Integer>{

}
