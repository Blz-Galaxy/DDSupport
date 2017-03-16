package org.dota.support.service;

import java.util.HashMap;
import java.util.List;

import org.dota.support.dao.IHeroDao;
import org.dota.support.pojo.Anti;
import org.dota.support.pojo.Assist;
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
	
	public List<Recommend> getAssists(List<String> Teammate) {
		return heroDao.sumAssist(Teammate); 
	}
	
	public List<Recommend> getAdvantages(List<String> Enemy) {
		return heroDao.sumAdvantage(Enemy); 
	}
	
	public List<Recommend> getSummary(List<String> Teammate, List<String> Enemy) {
		HashMap<String, Object> allMap = new HashMap<String, Object>(2);
		allMap.put("Teammate", Teammate);
		allMap.put("Enemy", Enemy);
		return heroDao.sumIdxValue(allMap); 
	}

	public Assist getAssist(String hero, String teammate) {
		HashMap<String, String> heros = new HashMap<String, String>(2);
		heros.put("Hero", hero);
		heros.put("Teammate", teammate);
		return heroDao.getAssist(heros); 		
	}
	
	public Anti getAnti(String hero, String enemy) {
		HashMap<String, String> heros = new HashMap<String, String>(2);
		heros.put("Hero", hero);
		heros.put("Enemy", enemy);
		return heroDao.getAnti(heros); 		
	}
}
