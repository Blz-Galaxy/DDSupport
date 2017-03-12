package org.dota.support.pojo;

public class Assist {
	private String Hero, Teammate;
	private double Assist, WinRate, AntiTimes;

	public String getHero() {
		return Hero;
	}

	public void setHero(String hero) {
		Hero = hero;
	}

	public String getTeammate() {
		return Teammate;
	}

	public void setTeammate(String teammate) {
		Teammate = teammate;
	}

	public double getAssist() {
		return Assist;
	}

	public void setAssist(double assist) {
		Assist = assist;
	}

	public double getWinRate() {
		return WinRate;
	}

	public void setWinRate(double winRate) {
		WinRate = winRate;
	}

	public double getAntiTimes() {
		return AntiTimes;
	}

	public void setAntiTimes(double antiTimes) {
		AntiTimes = antiTimes;
	}

	@Override
	public String toString() {
		return "Assist [Hero=" + Hero + ", Teammate=" + Teammate + ", Assist=" + Assist + ", WinRate=" + WinRate + ", AntiTimes=" + AntiTimes + "]";
	}
}
