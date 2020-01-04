package Coordinate;

import java.awt.MouseInfo;

public class CoordDeterm {
	private int[] xy;
	private int[] DetermXY()
	{
		xy['x'] = MouseInfo.getPointerInfo().getLocation().x;
		xy['y'] = MouseInfo.getPointerInfo().getLocation().y;
		return xy;
	}
	public int[] GetCoord()
	{
		return DetermXY();
	}
}
