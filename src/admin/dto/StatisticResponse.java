package admin.dto;

import java.util.List;

import admin.model.Statistic;

public class StatisticResponse {
	private Statistic statistic;
	private List list;
	public StatisticResponse() {
		super();
	}
	public Statistic getStatistic() {
		return statistic;
	}
	public void setStatistic(Statistic statistic) {
		this.statistic = statistic;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "StatisticResponse [statistic=" + statistic + ", list=" + list + "]";
	}
	

}
