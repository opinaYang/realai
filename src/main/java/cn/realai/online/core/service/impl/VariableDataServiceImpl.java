package cn.realai.online.core.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import cn.realai.online.core.controller.RealTimeController;
import cn.realai.online.core.dao.VariableDataDao;
import cn.realai.online.core.entity.VariableData;
import cn.realai.online.core.service.VariableDataService;

@Service
public class VariableDataServiceImpl implements VariableDataService {

	private static Logger logger = LoggerFactory.getLogger(RealTimeController.class);
	
	@Autowired
	private VariableDataDao variableDataDao;
	
	@Override
	@Transactional
	public int insertVariableDataList(List<VariableData> vdList) {
		if (vdList == null || vdList.size() == 0) {
			return 0;
		}
		
		for (VariableData vd : vdList) {
			vd.setDelete(VariableData.DELETE_NO);
			vd.setCreateTime(System.currentTimeMillis());
		}
		
		int ret = variableDataDao.insertVariableDataList(vdList);
		if (ret != vdList.size()) {
			logger.error("VariableDataServiceImpl insertVariableDataList, 预处理失败, vdList{}" + JSON.toJSONString(vdList));
			throw new RuntimeException("预处理失败");
		}
		logger.info("VariableDataServiceImpl insertVariableDataList, 预处理成功");
		return ret;
	}

}
