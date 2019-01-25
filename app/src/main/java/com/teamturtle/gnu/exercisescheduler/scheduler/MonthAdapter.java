package com.teamturtle.gnu.exercisescheduler.scheduler;

import android.content.Context;
import android.graphics.Color;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.Calendar;

/**
 * 어댑터 객체 정의
 *
 */

public class MonthAdapter extends BaseAdapter {

	public static final String TAG = "MonthAdapter";
	
	Context mContext;
	
	public static int oddColor = Color.rgb(225, 225, 225);
	public static int headColor = Color.rgb(12, 32, 158);
	
	private int selectedPosition = -1;
	
	private MonthItem[] items;
	
	private int countColumn = 7;
	
	int mStartDay;
	int startDay;
	int curYear;
	int curMonth;
	int curTDay; // 오늘 실제 며칠이냐?
	int curTMonth; // 오늘이 실제 몇월이냐?
	
	int firstDay;
	int lastDay;
	
	Calendar mCalendar; // 달력모양 뿌리기용 Calendar 변수
	Calendar tCalendar; // 실제 오늘 날짜 계산용 Calendar 변수
	boolean recreateItems = false;

	public MonthAdapter(Context context) {
		super();

		mContext = context;
		
		init();
	}
	
	public MonthAdapter(Context context, AttributeSet attrs) {
		super();

		mContext = context;
		
		init();
	}

	private void init() {

		items = new MonthItem[7 * 6]; // 42개의 Item 생성

		mCalendar = Calendar.getInstance(); // 캘린더 초기화인듯
		tCalendar = Calendar.getInstance(); // 실제 날짜 계산용
		recalculate();
		resetDayNumbers();
	}
	
	public void recalculate() {

		// set to the first day of the month
        // Field number for get and set indicating the day of the month.
		// DAY_OF_MONTH는 일자를 나타낸다. (DATE와 동일)
		mCalendar.set(Calendar.DAY_OF_MONTH, 1); // 오늘을 1일로 세팅해버리기~
		
		// get week day
        // DAY_OF_WEEK : 해당 날짜의 요일값을 숫자로 출력. 일요일1 ~ 토요일7
		int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);
		// 0(일) ~ 6(토)
		// firstDay : 그 달의 첫 날이 시작되는게 무슨 요일이냐?
		firstDay = getFirstDay(dayOfWeek);
		Log.d(TAG, "firstDay : " + firstDay);

        // getFirstDayOfWeek
        // Returns what the first day of the week is, where 1 = SUNDAY and 7 = SATURDAY.
		mStartDay = mCalendar.getFirstDayOfWeek(); // 한주의 시작이 일요일이냐 월요일이냐 같은...
		curYear = mCalendar.get(Calendar.YEAR);
		curMonth = mCalendar.get(Calendar.MONTH); // 0월 부터 시작하는 듯
		lastDay = getMonthLastDay(curYear, curMonth); // 말 그대로 마지막날이 30일이냐 31일이냐
		
		Log.d(TAG, "curYear : " + curYear + ", curMonth : " + curMonth + ", lastDay : " + lastDay);
		
		int diff = mStartDay - Calendar.SUNDAY - 1;
        startDay = getFirstDayOfWeek();
		Log.d(TAG, "mStartDay : " + mStartDay + ", startDay : " + startDay);

		// DAY_OF_YEAR : 오늘이 1년 중의 몇번째 날이냐? 인 듯?
		// DAY_OF_MONTH : 오늘이 며칠이냐? DATE와 같음.
		// 실제 날짜 계산. mCalendar는 1일로 세팅해버렸기 때문에 tCalendar 사용.
		curTDay = tCalendar.get(Calendar.DAY_OF_MONTH);
		curTMonth = tCalendar.get(Calendar.MONTH);
		Log.d(TAG, "curTDay : " + curTDay);
		Log.d(TAG, "curTMonth : " + curTMonth);
	}
	
	public void setPreviousMonth() {
		mCalendar.add(Calendar.MONTH, -1);
        recalculate();
        
        resetDayNumbers();
        selectedPosition = -1;
	}
	
	public void setNextMonth() {
		mCalendar.add(Calendar.MONTH, 1);
        recalculate();
        
        resetDayNumbers();
        selectedPosition = -1;
	}
	
	public void resetDayNumbers() {
		for (int i = 0; i < 42; i++) {
			// calculate day number
			int dayNumber = (i+1) - firstDay;
			if (dayNumber < 1 || dayNumber > lastDay) {
				dayNumber = 0;
			}
			
	        // save as a data item
	        items[i] = new MonthItem(dayNumber);
		}
	}
	
	private int getFirstDay(int dayOfWeek) {
        // 요일마다 서로 다른 숫자를 반환한다.
		int result = 0;
		if (dayOfWeek == Calendar.SUNDAY) {
			result = 0;
		} else if (dayOfWeek == Calendar.MONDAY) {
			result = 1;
		} else if (dayOfWeek == Calendar.TUESDAY) {
			result = 2;
		} else if (dayOfWeek == Calendar.WEDNESDAY) {
			result = 3;
		} else if (dayOfWeek == Calendar.THURSDAY) {
			result = 4;
		} else if (dayOfWeek == Calendar.FRIDAY) {
			result = 5;
		} else if (dayOfWeek == Calendar.SATURDAY) {
			result = 6;
		}
		
		return result;
	}

	public int getTCurDay() {
		return curTDay;
	} // tCalendar(실제시간)로 계산

	public int getTCurMonth() {
		return curTMonth;
	} // tCalendar(실제시간)로 계산

	public int getCurYear() {
		return curYear;
	}
	
	public int getCurMonth() {
		return curMonth;
	}

	public int getNumColumns() { return 7; }

	public int getCount() {
		return 7 * 6;
	}

	public Object getItem(int position) {
		return items[position];
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		Log.d(TAG, "getView(" + position + ") called.");

		MonthItemView itemView;
		if (convertView == null) {
			itemView = new MonthItemView(mContext);
		} else {
			itemView = (MonthItemView) convertView;
		}
		
		// create a params
		GridView.LayoutParams params = new GridView.LayoutParams(
				GridView.LayoutParams.MATCH_PARENT,
				120);
		
		// calculate row and column
		// countColumn은 7
		int rowIndex = position / countColumn; // 달력의 행(0 ~ 6)
		int columnIndex = position % countColumn; // 열(0 ~ 6)
		
		Log.d(TAG, "Index : " + rowIndex + ", " + columnIndex);

		// set item data and properties
		itemView.setItem(items[position]);
		itemView.setLayoutParams(params);
		itemView.setPadding(2, 2, 2, 2);
		
		// set properties
		itemView.setGravity(Gravity.CENTER);

		if (columnIndex == 0) { // 일요일은 빨강색으로
			itemView.setTextColor(Color.RED);

		}

		else if(columnIndex==6) { // 토요일은 파란색으로
			itemView.setTextColor(Color.BLUE);

		}
		else { // 평일은 검정색으로
			itemView.setTextColor(Color.BLACK);
		}

		// set background color
		if (position == getSelectedPosition()) {
		Log.d("getSelected", "positon == getSelectedPositon 진입");

		itemView.setBackgroundColor(Color.YELLOW);
		} else {
		itemView.setBackgroundColor(Color.WHITE);
		}

		// 오늘 날짜는 다르게 색칠하기
		if (items[position].getDay() == getTCurDay()) {
			if (getCurMonth() == getTCurMonth()) { // 스케줄러의 Month와 실제 Month의 비교
				Log.d(TAG, "getCurMonth() == Calendar.MONTH 진입");
				itemView.setBackgroundColor(Color.GREEN);

				if (position == getSelectedPosition()) {
					Log.d("getSelected", "positon == getSelectedPositon 진입");

					itemView.setBackgroundColor(Color.LTGRAY);
				}
			}
		}

		return itemView;
	}

    /**
     * Get first day of week as android.text.format.Time constant.
     * @return the first day of week in android.text.format.Time
     */
    public static int getFirstDayOfWeek() {
        int startDay = Calendar.getInstance().getFirstDayOfWeek();
        if (startDay == Calendar.SATURDAY) {
            return Time.SATURDAY;
        } else if (startDay == Calendar.MONDAY) {
            return Time.MONDAY;
        } else {
            return Time.SUNDAY;
        }
    }
 
	
    /**
     * get day count for each month
     * 
     * @param year
     * @param month
     * @return
     */
    // 30일짜리 31일짜리 달 구분
    private int getMonthLastDay(int year, int month) {

    	switch (month) {
 	   		case 0:
      		case 2:
      		case 4:
      		case 6:
      		case 7:
      		case 9:
      		case 11:
      			return (31);

      		case 3:
      		case 5:
      		case 8:
      		case 10:
      			return (30);

      		default:
      			if(((year%4==0)&&(year%100!=0)) || (year%400==0) ) {
      				return (29);   // 2월 윤년계산
      			} else { 
      				return (28);
      			}
 	   	}
 	}

	/**
	 * set selected row
	 */
	public void setSelectedPosition(int selectedPosition) {
		Log.d("setSelectedPosition", "selectedPositon: " + selectedPosition);

		this.selectedPosition = selectedPosition;
	}

	/**
	 * get selected row
	 * 
	 * @return
	 */
	public int getSelectedPosition() {
		return selectedPosition;
	}



}
