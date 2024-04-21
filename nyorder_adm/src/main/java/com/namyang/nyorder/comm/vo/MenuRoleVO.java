package com.namyang.nyorder.comm.vo;

public class MenuRoleVO extends MenuVO{

	private String menuSeq;
	private String emplSec;
	private String menuType;
	
	//LvL
	private String lvl1;
	private String lvl2;
	private String lvl3;
	
	// 직책 권한 여부	
	private String storOwnrYn;
	private String grfrEmplYn;
	private String agentPrmtEmplYn;
	private String hoffPrmtEmplYn;
	private String agenEmplYn;
	private String wekEmplYn;
	
	private String regDtm;
	private String regSeq;
	private String updDtm;
	private String updSeq;
	private String roleSeq;
	private String roleYn;

	public String getRoleSeq() {
		return roleSeq;
	}
	public void setRoleSeq(String roleSeq) {
		this.roleSeq = roleSeq;
	}
	public String getMenuSeq() {
		return menuSeq;
	}
	public void setMenuSeq(String menuSeq) {
		this.menuSeq = menuSeq;
	}
	public String getRoleYn() {
		return roleYn;
	}
	public void setRoleYn(String roleYn) {
		this.roleYn = roleYn;
	}
	public String getEmplSec() {
		return emplSec;
	}
	public void setEmplSec(String emplSec) {
		this.emplSec = emplSec;
	}
	public String getRegDtm() {
		return regDtm;
	}
	public void setRegDtm(String regDtm) {
		this.regDtm = regDtm;
	}
	public String getRegSeq() {
		return regSeq;
	}
	public void setRegSeq(String regSeq) {
		this.regSeq = regSeq;
	}
	public String getUpdDtm() {
		return updDtm;
	}
	public void setUpdDtm(String updDtm) {
		this.updDtm = updDtm;
	}
	public String getUpdSeq() {
		return updSeq;
	}
	public void setUpdSeq(String updSeq) {
		this.updSeq = updSeq;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public String getStorOwnrYn() {
		return storOwnrYn;
	}
	public void setStorOwnrYn(String storOwnrYn) {
		this.storOwnrYn = storOwnrYn;
	}
	public String getGrfrEmplYn() {
		return grfrEmplYn;
	}
	public void setGrfrEmplYn(String grfrEmplYn) {
		this.grfrEmplYn = grfrEmplYn;
	}
	public String getAgentPrmtEmplYn() {
		return agentPrmtEmplYn;
	}
	public void setAgentPrmtEmplYn(String agentPrmtEmplYn) {
		this.agentPrmtEmplYn = agentPrmtEmplYn;
	}
	public String getHoffPrmtEmplYn() {
		return hoffPrmtEmplYn;
	}
	public void setHoffPrmtEmplYn(String hoffPrmtEmplYn) {
		this.hoffPrmtEmplYn = hoffPrmtEmplYn;
	}
	public String getAgenEmplYn() {
		return agenEmplYn;
	}
	public void setAgenEmplYn(String agenEmplYn) {
		this.agenEmplYn = agenEmplYn;
	}
	public String getWekEmplYn() {
		return wekEmplYn;
	}
	public void setWekEmplYn(String wekEmplYn) {
		this.wekEmplYn = wekEmplYn;
	}
	public String getLvl1() {
		return lvl1;
	}
	public void setLvl1(String lvl1) {
		this.lvl1 = lvl1;
	}
	public String getLvl2() {
		return lvl2;
	}
	public void setLvl2(String lvl2) {
		this.lvl2 = lvl2;
	}
	public String getLvl3() {
		return lvl3;
	}
	public void setLvl3(String lvl3) {
		this.lvl3 = lvl3;
	}
	
}
