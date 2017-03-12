package org.dota.support.pojo;

public class Recommend implements Comparable<Recommend>{
	private String Hero;
	private double IdxValue;

	public String getHero() {
		return Hero;
	}

	public void setHero(String hero) {
		Hero = hero;
	}

	public double getIdxValue() {
		return IdxValue;
	}

	public void setIdxValue(double idxValue) {
		IdxValue = idxValue;
	}

	@Override
	public String toString() {
		return "Recommend [Hero=" + Hero + ", IdxValue=" + IdxValue + "]";
	}
	
	@Override
    public int compareTo(Recommend r) {
		if (this.IdxValue > r.getIdxValue())
			return -1;
		else
			return 1;
    }  
}
