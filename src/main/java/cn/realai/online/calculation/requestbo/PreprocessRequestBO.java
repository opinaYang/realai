package cn.realai.online.calculation.requestbo;

/**
 * 预测类请求实体
 * @author lyh
 */
public class PreprocessRequestBO {

	//实验id
	private long id;
	
	//命令
    private String command;
    
    //x表异质数据源
    private String xtableHeterogeneousDataSource;
    
    //x表同质数据源
    private String xtableHomogeneousDataSource;
    
    //y表数据源
    private String ytableDataSource;
    
    //x表含义对照数据源
    private String  xtableMeaningDataSource;
    
    public PreprocessRequestBO(String command) {
    	this.command = command;
    }

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getXtableHeterogeneousDataSource() {
		return xtableHeterogeneousDataSource;
	}

	public void setXtableHeterogeneousDataSource(String xtableHeterogeneousDataSource) {
		this.xtableHeterogeneousDataSource = xtableHeterogeneousDataSource;
	}

	public String getXtableHomogeneousDataSource() {
		return xtableHomogeneousDataSource;
	}

	public void setXtableHomogeneousDataSource(String xtableHomogeneousDataSource) {
		this.xtableHomogeneousDataSource = xtableHomogeneousDataSource;
	}

	public String getYtableDataSource() {
		return ytableDataSource;
	}

	public void setYtableDataSource(String ytableDataSource) {
		this.ytableDataSource = ytableDataSource;
	}

	public String getXtableMeaningDataSource() {
		return xtableMeaningDataSource;
	}

	public void setXtableMeaningDataSource(String xtableMeaningDataSource) {
		this.xtableMeaningDataSource = xtableMeaningDataSource;
	}
	
}
