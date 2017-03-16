package org.dota.support.controller;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dota.support.pojo.Anti;
import org.dota.support.pojo.Assist;
import org.dota.support.pojo.Hero;
import org.dota.support.pojo.Recommend;
import org.dota.support.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/data")
public class HeroController {
	private Logger logger = Logger.getLogger(HeroController.class);

	@Autowired
	private HeroService heroService;

	@RequestMapping(value = "/hero", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject FetchHero(HttpServletRequest request) {
		logger.info("Getting hero...");
		Hero hero = null;
		try {
			String ID = request.getParameter("ID");
			System.out.println(ID);
			hero = heroService.getHerosByID(ID);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		logger.info("Geted hero!");
		if (hero == null) {
			JSONObject error = new JSONObject();
			error.put("null", "null");
			return error;
		}
		return JSONObject.fromObject(hero);
	}

	@RequestMapping(value = "/recommend", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray FetchRecommend(HttpServletRequest request) {
		logger.info("Getting recommend...");
		JSONArray jArray = null;
		try {
			String Teammate = request.getParameter("Teammate");
			String Enemy = request.getParameter("Enemy");
			List<String> Teammate_L = null, Enemy_L = null;

			if (Teammate == null)
				Teammate = "";
			if (Enemy == null)
				Enemy = "";

			Teammate_L = Arrays.asList(Teammate.split(","));			
			Enemy_L = Arrays.asList(Enemy.split(","));			

			//List<Recommend> recommends = getRecommends(Teammate_L, Enemy_L);
			List<Recommend> recommends = heroService.getSummary(Teammate_L, Enemy_L);
			
			if (recommends == null) {
				jArray = new JSONArray();
				jArray.add(0, "null");
				return jArray;
			} else {
				jArray = JSONArray.fromObject(recommends);
				Iterator<JSONObject> it = jArray.iterator();	           
	            while (it.hasNext()) {
	            	JSONObject jhero = it.next();
	            	String hero = jhero.getString("hero");
	            	
	            	// 查询场上的辅助英雄
	            	JSONArray assistArray = new JSONArray();
	            	for (String teammate : Teammate_L) {
	            		Assist assist = heroService.getAssist(hero, teammate);
	            		if(assist == null) continue;
	            		if(assist.getAssist() > 0)
	            			assistArray.add(teammate);
					}
	            	jhero.put("Assist", assistArray);
	            	
	            	// 查询场上的克制英雄
	            	JSONArray antiArray = new JSONArray();
	            	for (String enemy : Enemy_L) {
	            		Anti anti = heroService.getAnti(hero, enemy);
	            		if(anti == null) continue;
	            		if(anti.getAdvantage() > 0)
	            			antiArray.add(enemy);
					}
	            	jhero.put("Anti", antiArray);
	            }
			}			
		} catch (Exception e) {
			logger.error(e.toString());
		}
		logger.info("Got recommend!");
		return jArray;
	}
	
	
	@RequestMapping(value = "/grid", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray FetchGrid(HttpServletRequest request) {
		logger.info("Getting grid...");
		JSONArray competition = null;
		try {
			String Teammate = request.getParameter("Teammate");
			String Enemy = request.getParameter("Enemy");
			List<String> Teammate_L = null, Enemy_L = null;

			if (Teammate == null)
				Teammate = "";
			if (Enemy == null)
				Enemy = "";

			Teammate_L = Arrays.asList(Teammate.split(","));			
			Enemy_L = Arrays.asList(Enemy.split(","));
			
			competition = new JSONArray();
			for (String teammate : Teammate_L) {
	        	for (String enemy : Enemy_L) {
	        		Anti anti = heroService.getAnti(teammate, enemy);
	        		if(anti == null) continue;
	        		
	        		JSONObject group = new JSONObject();
	        		group.put("Teammate", teammate);
        			group.put("Enemy", enemy);
	        		if(anti.getAdvantage() > 0)
	        			group.put("TeamWin", 1);
	        		else
	        			group.put("TeamWin", 0);
	        		competition.add(group);
				}
			}			
		} catch (Exception e) {
			logger.error(e.toString());
		}
		logger.info("Got recommend!");
		return competition;
	}
}
