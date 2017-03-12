package org.dota.support.pojo;

public class Anti {
	private String Hero, Enemy;
	private double Advantage, WinRate, AntiTimes;

	public String getHero() {
		return Hero;
	}

	public void setHero(String hero) {
		Hero = hero;
	}

	public String getEnemy() {
		return Enemy;
	}

	public void setEnemy(String enemy) {
		Enemy = enemy;
	}

	public double getAdvantage() {
		return Advantage;
	}

	public void setAdvantage(double advantage) {
		Advantage = advantage;
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
		return "Anti [Hero=" + Hero + ", Enemy=" + Enemy + ", Advantage=" + Advantage + ", WinRate=" + WinRate + ", AntiTimes=" + AntiTimes + "]";
	}
}
