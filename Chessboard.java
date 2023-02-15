import java.util.Map;
import java.util.*;

public class Chessboard {
	
	private static Integer num2;
	private static String value;

	public static String findEndPosition(String startPosition,int R, int C) {
		
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("a",1);
		map.put("b",2);
		map.put("c",3);
		map.put("d",4);
		map.put("e",5);
		map.put("f",6);
		map.put("g",7);
		map.put("h",0);
		
			int num=Integer.parseInt(startPosition.substring(0, 1));
			
			String ch = startPosition.substring(1, 2);
			
			map.forEach((k,v)->{
				if(k.equals(ch)) {
					num2=v;
				}
			});

			num=(num+R%8)%8;
			if(num==0) {
				num=8;
			}
			num2=(num2+C%8)%8;
			
			map.forEach((k,v)->{
				if(v==num2) {
					value=k;
				}
			});
			
		return num+""+value;
	}

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter Starting Position");
		String startPosition = sc.next();
		System.out.println("Enter Rows");
		int R = sc.nextInt();
		System.out.println("Enter Columns");
		int C = sc.nextInt();
		String endPosition= findEndPosition(startPosition,R,C);
		System.out.println("EndPosition="+endPosition);
	}
		
		
	}


