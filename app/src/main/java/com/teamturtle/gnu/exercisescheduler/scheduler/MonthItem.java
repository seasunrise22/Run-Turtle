package com.teamturtle.gnu.exercisescheduler.scheduler;

/**
 * 일자 정보를 담기 위한 클래스 정의
 *
 */
public class MonthItem {

	private int dayValue;

	private int monthValue;

	public MonthItem() {}
	
	public MonthItem(int day) {
		dayValue = day;
	}
	
	public int getDay() {
		return dayValue;
	}

	public int getMonth() {  return monthValue;}

	public void setDay(int day) {

		this.dayValue = day;
	}

}
