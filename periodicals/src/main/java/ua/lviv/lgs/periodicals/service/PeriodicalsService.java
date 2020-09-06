package ua.lviv.lgs.periodicals.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.periodicals.dao.PeriodicalRepository;
import ua.lviv.lgs.periodicals.domain.Periodical;

@Service
public class PeriodicalsService {

	private Logger logger = LoggerFactory.getLogger(PeriodicalsService.class);
	
	@Autowired
	private PeriodicalRepository periodicalRepository;
	
	public Periodical save(Periodical periodical) {
		logger.info("Create periodical item {}: " + periodical);
		return periodicalRepository.save(periodical);
	}
	
	public List<Periodical> getAllPeriodicals(){
		logger.info("Get all periodicals items");
		return periodicalRepository.findAll();
	}
	
	public Periodical findById(Integer id) {
		logger.info("Get periodical item by id: " + id);
		return periodicalRepository.findById(id).get();
	}
}
