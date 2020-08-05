package com.example.ATMProject;

import io.swagger.models.auth.In;

public class ReportEntry {
		public Integer field_1;
		public Integer field_5;
		public Integer field_10;
		public Integer field_50;
		public Integer field_100;
		public ReportEntry(Integer field_1, Integer field_5, Integer field_10, Integer field_50, Integer field_100) {
			this.field_1 = field_1;
			this.field_5 = field_5;
			this.field_10 = field_10;
			this.field_50 = field_50;
			this.field_100 = field_100;
		}
	
	public Integer getfield_1() {
		return field_1;
	}
	
	public void setfield_1(Integer field_1) {
		field_1 = field_1;
	}
	
	public Integer getfield_5() {
		return field_5;
	}
	
	public void setfield_5(Integer field_5) {
		field_5 = field_5;
	}
	
	public Integer getfield_10() {
		return field_10;
	}
	
	public void setfield_10(Integer field_10) {
		field_10 = field_10;
	}
	
	public Integer getfield_50() {
		return field_50;
	}
	
	public void setfield_50(Integer field_50) {
		field_50 = field_50;
	}
	
	public Integer getfield_100() {
		return field_100;
	}
	
	public void setfield_100(Integer field_100) {
		field_100 = field_100;
	}
}
