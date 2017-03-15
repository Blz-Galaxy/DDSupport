package org.dota.support.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
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
			error.put("error", "null value");
			return error;
		}
		return JSONObject.fromObject(hero);
	}

	@RequestMapping(value = "/recommend", method = RequestMethod.GET)
	@ResponseBody
	public JSONArray FetchData(HttpServletRequest request) {
		logger.info("Getting recommend...");
		JSONArray jArray = null;
		try {
			String Teammate = request.getParameter("Teammate");
			String Enemy = request.getParameter("Enemy");
			List<String> Teammate_L = null, Enemy_L = null;

			if (Teammate != null)
				Teammate_L = Arrays.asList(Teammate.split(","));
			if (Enemy != null)
				Enemy_L = Arrays.asList(Enemy.split(","));

			List<Recommend> recommends = getRecommends(Teammate_L, Enemy_L);
			if (recommends == null) {
				jArray = new JSONArray();
				jArray.add(0, "null value");
				return jArray;
			} else
				jArray = JSONArray.fromObject(recommends);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		logger.info("Geted recommend!");
		return jArray;
	}

	public List<Recommend> getRecommends(List<String> Teammate_L, List<String> Enemy_L) {
		List<Recommend> Teammate_R = null, Enemy_R = null;

		if (Teammate_L != null)
			Teammate_R = heroService.getAssist(Teammate_L);
		if (Enemy_L != null)
			Enemy_R = heroService.getAdvantage(Enemy_L);

		List<Recommend> recommends = new ArrayList<Recommend>();

		if (Enemy_R == null && Teammate_R == null)
			return null;

		if (Teammate_R == null)
			recommends = Enemy_R;

		if (Enemy_R == null)
			recommends = Teammate_R;

		if (Enemy_R != null && Teammate_R != null)
			for (Recommend team : Teammate_R) {
				for (Recommend enemy : Enemy_R) {
					String e_hero = enemy.getHero();
					String t_hero = team.getHero();

					double e_value = enemy.getIdxValue();
					double t_value = team.getIdxValue();

					if (e_hero.equals(t_hero)) {

						Recommend sum = new Recommend();
						sum.setHero(e_hero);
						sum.setIdxValue(e_value + t_value);
						recommends.add(sum);
						break;
					}
				}
			}

		Collections.sort(recommends);
		return recommends;
	}

}
