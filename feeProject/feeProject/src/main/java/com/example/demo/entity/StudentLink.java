package com.example.demo.entity;

import javax.persistence.Embeddable;

@Embeddable
public class StudentLink {
	
	
	private Integer perm;//Type of Account

	private Integer perm_lv;//Account Permission ID

	public Integer getPerm() {
		return perm;
	}

	public void setPerm(Integer perm) {
		this.perm = perm;
	}

	public Integer getPerm_lv() {
		return perm_lv;
	}

	public void setPerm_lv(Integer perm_lv) {
		this.perm_lv = perm_lv;
	}
	
	
}
