package org.dota.support.dao;

import java.util.HashMap;
import java.util.List;

import org.dota.support.pojo.Anti;
import org.dota.support.pojo.Assist;
import org.dota.support.pojo.Hero;
import org.dota.support.pojo.Recommend;

public interface IHeroDao {

	public List<Hero> selectHeros();

	public Hero selectHeroByID(String ID);

	public List<Hero> recommendHeros();

	public List<Recommend> sumAssist(List<String> Teammate);

	public List<Recommend> sumAdvantage(List<String> Enemy);

	public List<Recommend> sumIdxValue(HashMap<String, Object> allMap);

	public Assist getAssist(HashMap<String, String> heros);

	public Anti getAnti(HashMap<String, String> heros);
}
