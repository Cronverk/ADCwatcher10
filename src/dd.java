import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.math.BigDecimal;
import java.math.RoundingMode;
 

public class dd {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		
		
		
		double templateDouble = 12.1354678578862;
		System.out.println("Template double: " + templateDouble);
		
		double newDouble = new BigDecimal(templateDouble).setScale(3, RoundingMode.UP).doubleValue();
		//new Double = new BigDecimal(templateDouble).setScale(3)
		System.out.println("New double: " + newDouble);
		
		
		
	int mas[] =  {0 ,0,0,0,0,0,0,0};
int cur = 0;
	int num = 11;
		for( int i =0 ; i < 8;i++){
			cur = num>>1;
			cur = cur<<1;
		mas[7-i] = num-cur;
		num = num>>1;
		}
		System.out.println("mas = "+Arrays.toString(mas));
	}
	
	

}
