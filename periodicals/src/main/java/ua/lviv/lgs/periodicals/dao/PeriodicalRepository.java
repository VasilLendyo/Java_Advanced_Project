package ua.lviv.lgs.periodicals.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.lviv.lgs.periodicals.domain.Periodical;

public interface PeriodicalRepository extends JpaRepository<Periodical, Integer>{

}
