package cn.realai.online.core.entity;

import cn.realai.online.common.vo.Result;
import cn.realai.online.core.vo.IdVO;
import cn.realai.online.core.vo.PersonalInformationDetailVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 跑批记录
 * @author lyh
 */
public class BatchRecord {

	private long id;
	
	//y表数据源
	private String ytableDataSource;
	
	//x表同质数据源
	private String xtableHomogeneousDataSource;
	
	//x表异质数据源
	private String xtableHeterogeneousDataSource;
	
	//实验id
	private long experimentId;
	
	//服务id
	private long serviceId;
	
	//创建时间
	private long createTime;
	
	//结果下载路径
	private String downUrl;
	
	//批次类型
	private int batchType;
	
	public static final int BATCH_TYPE_OFFLINE = 1; // 离线跑批
	
	public static final int BATCH_TYPE_DAILY = 2; //每日跑批

	public static final int BATCH_TYPE_TRAIN = 3; //训练批次

	//批次名称
	private String batchName;

	public long getServiceId() {
		return serviceId;
	}

	public void setServiceId(long serviceId) {
		this.serviceId = serviceId;
	}

	public int getBatchType() {
		return batchType;
	}

	public void setBatchType(int batchType) {
		this.batchType = batchType;
	}

	public String getDownUrl() {
		return downUrl;
	}

	public void setDownUrl(String downUrl) {
		this.downUrl = downUrl;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getYtableDataSource() {
		return ytableDataSource;
	}

	public void setYtableDataSource(String ytableDataSource) {
		this.ytableDataSource = ytableDataSource;
	}

	public String getXtableHomogeneousDataSource() {
		return xtableHomogeneousDataSource;
	}

	public void setXtableHomogeneousDataSource(String xtableHomogeneousDataSource) {
		this.xtableHomogeneousDataSource = xtableHomogeneousDataSource;
	}

	public String getXtableHeterogeneousDataSource() {
		return xtableHeterogeneousDataSource;
	}

	public void setXtableHeterogeneousDataSource(String xtableHeterogeneousDataSource) {
		this.xtableHeterogeneousDataSource = xtableHeterogeneousDataSource;
	}

	public long getExperimentId() {
		return experimentId;
	}

	public void setExperimentId(long experimentId) {
		this.experimentId = experimentId;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
}
