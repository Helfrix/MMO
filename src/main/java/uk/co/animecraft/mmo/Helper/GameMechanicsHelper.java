package uk.co.animecraft.mmo.Helper;

public class GameMechanicsHelper {

	private final int  initPlayerXP = 1000;
	private final int  initPlayerPerc = 15;

	public int getMaxXP(int level){
		int j = initPlayerXP;
		level--;
		for(int i = 0; i < level; i++)
		{
			j = (int) Math.ceil(((j / 100) * initPlayerPerc) + j);
		}
		return j;
	}

	public String getFormattedBigNumber(int num){

		String strResult = "";
		String strNum = String.valueOf(num);
		int numLen =  strNum.length();

		String f = "";
		String s = "";

		switch (numLen){
		case 4:
			f = strNum.substring(0, 1);
			s = strNum.substring(1, 2);
			if (s.equals("0"))
				strResult = f + "K";
			else
				strResult = f + "." + s + "K";
			break;
		case 5:
			f = strNum.substring(0, 2);
			s = strNum.substring(2, 3);
			if (s.equals("0"))
				strResult = f + "K";
			else
				strResult = f + "." + s + "K";
			break;
		case 6:
			f = strNum.substring(0, 3);
			s = strNum.substring(3, 4);
			if (s.equals("0"))
				strResult = f + "K";
			else
				strResult = f + "." + s + "K";
			break;
		case 7:
			f = strNum.substring(0, 1);
			s = strNum.substring(1, 2);
			if (s.equals("0"))
				strResult = f + "M";
			else
				strResult = f + "." + s + "M";
			break;
		case 8:
			f = strNum.substring(0, 2);
			s = strNum.substring(2, 3);
			if (s.equals("0"))
				strResult = f + "M";
			else
				strResult = f + "." + s + "M";
			break;
		case 9:
			f = strNum.substring(0, 3);
			s = strNum.substring(3, 4);
			if (s.equals("0"))
				strResult = f + "M";
			else
				strResult = f + "." + s + "M";
			break;
		case 10:
			f = strNum.substring(0, 1);
			s = strNum.substring(1, 2);
			if (s.equals("0"))
				strResult = f + "B";
			else
				strResult = f + "." + s + "B";
			break;
		default:
			strResult = strNum;
			break;
		}

		return strResult;
	}

}
