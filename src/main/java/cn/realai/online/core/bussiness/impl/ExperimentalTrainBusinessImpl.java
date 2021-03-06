package cn.realai.online.core.bussiness.impl;

import cn.realai.online.calculation.TrainService;
import cn.realai.online.common.page.PageBO;
import cn.realai.online.core.bo.ExperimentBO;
import cn.realai.online.core.bussiness.ExperimentalTrainBusiness;
import cn.realai.online.core.entity.Experiment;
import cn.realai.online.core.query.ExperimentalTrainQuery;
import cn.realai.online.core.service.ExperimentService;
import cn.realai.online.core.vo.ExperimentalTrainVO;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 实验训练的业务实现
 */
@Service
@Transactional(readOnly = true)
public class ExperimentalTrainBusinessImpl implements ExperimentalTrainBusiness {

    @Autowired
    private ExperimentService experimentService;
    
    @Autowired
    private TrainService trainService;

    /**
     * 根据实验名称和状态等分页查询实验列表
     *
     * @param experimentalTrainQuery 实验列表查询条件
     * @return
     */
    @Override
    public PageBO<ExperimentalTrainVO> pageList(ExperimentalTrainQuery experimentalTrainQuery) {
        //开启分页
        Page page = PageHelper.startPage(experimentalTrainQuery.getPageNum(), experimentalTrainQuery.getPageSize());

        //执行条件查询
        Experiment experiment = new Experiment();
        BeanUtils.copyProperties(experimentalTrainQuery, experiment);
        List<ExperimentBO> list = experimentService.findList(experiment);

        //处理查询结果
        List<ExperimentalTrainVO> result = JSON.parseArray(JSON.toJSONString(list), ExperimentalTrainVO.class);
        PageBO<ExperimentalTrainVO> pageBO = new PageBO<ExperimentalTrainVO>(result, experimentalTrainQuery.getPageSize(), experimentalTrainQuery.getPageNum(), page.getTotal(), page.getPages());
        return pageBO;
    }

    
    
    /*
     * 训练
     * @param trainId 实验id
     */
	@Override
	public int train(long experimentId) {
		//获取训练锁
		
		
		//修改试验状态
		int ret = experimentService.updateExperimentStatus(experimentId, Experiment.STATUS_TRAINING);
		ExperimentBO experimentBO = experimentService.selectExperimentById(experimentId);
		trainService.training(experimentBO);
		return ret;
	}



	@Override
	public void testPreprocess(long experimentId) {
		ExperimentBO experimentBO = experimentService.selectExperimentById(experimentId);
		trainService.preprocess(experimentBO);
	}
}
