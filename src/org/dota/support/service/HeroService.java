package org.dota.support.service;

import java.util.HashMap;
import java.util.List;

import org.dota.support.dao.IDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {

	@Autowired
	private IDataDao dataDao;

	public List<HashMap<String,String>> getHeros() {
		return dataDao.selectHeros(); 
	}
}
