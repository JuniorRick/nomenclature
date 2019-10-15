package crdm.nomenclature.component;

import java.util.Calendar;

import org.springframework.stereotype.Component;


@Component
public class YearComponent {

	private int year = Calendar.getInstance().get(Calendar.YEAR);

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
}
