
public class AddHHMM {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] hr = {"9:03","9:23","7:20","7:00","8:00"}; 
		//String[] hr = {"08:48","8:01","02:59","4:00","8:31","08:15","7:20","7:10","9:01","9:01","6:19"}; //39.24
		
		//String[] hr = {"8:03","7:55","8:11","7:59","7:16","6:39","8:41","8:38","8:39","8:22"}; // 40.36
		@SuppressWarnings("unused")
		int count =0, sum_hr=0, sum_mm =0, hour =0, min=0;
		for(int i=0; i<hr.length; i++) {
			String[] hh_mm = null ;
			 if(hr[i].contains(":")) {
				 hh_mm = hr[i].split(":");  
				 hour = Integer.parseInt(hh_mm[0]);
				 min = Integer.parseInt(hh_mm[1]);
			 }
			 else
			 {
				 hour = Integer.parseInt(hr[i]);
				 min =0;
			 }
				 
				  sum_hr += hour;  // add hours
				  sum_mm += min;  // add minute
				  
				  if(sum_mm >=60) {
					  int val = sum_mm/60;
					  
					  sum_hr = sum_hr+ val;
					  sum_mm = sum_mm%60;
				  }
				  
			 
		}
		System.out.println("Sum of hr and min "+sum_hr +":"+ sum_mm);

	}

}
