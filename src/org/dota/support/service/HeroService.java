package org.dota.support.service;

import java.util.HashMap;
import java.util.List;

import org.dota.support.dao.IHeroDao;
import org.dota.support.pojo.Hero;
import org.dota.support.pojo.Recommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeroService {

	@Autowired
	private IHeroDao heroDao;

	public List<Hero> getHeros() {
		return heroDao.selectHeros(); 
	}
	
	public Hero getHerosByID(String ID) {
		return heroDao.selectHeroByID(ID); 
	}
	
	public List<Recommend> getAssist(List<String> Teammate) {
		return heroDao.sumAssist(Teammate); 
	}
	
	public List<Recommend> getAdvantage(List<String> Enemy) {
		return heroDao.sumAdvantage(Enemy); 
	}
}
