package org.dota.support.pojo;

public class Hero {
	private String ID;
	private String Ename;
	private String Cname;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getEname() {
		return Ename;
	}

	public void setEname(String ename) {
		Ename = ename;
	}

	public String getCname() {
		return Cname;
	}

	public void setCname(String cname) {
		Cname = cname;
	}

	@Override
	public String toString() {
		return "Hero [ID=" + ID + ", Ename=" + Ename + ", Cname=" + Cname + "]";
	}
}
