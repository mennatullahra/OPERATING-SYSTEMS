
public class SCAN {
	myQueue requests;
		
	public static int[] RunSCAN() {
		
		int[] result= new int [myQueue.array.size()+3];
		int countResult=0;
		Integer[] sortedArr = myQueue.sortArray();
		int idx=myQueue.getInitialidx(sortedArr);
		int total =0 ;

		int last =myQueue.array.get(0);
		result[0] = last;
		for (int j = idx; j < sortedArr.length-1; j++) {
			System.out.println(last+"->"+sortedArr[j+1]);
			countResult++;
			result[countResult] = sortedArr[j+1];
			total += Math.abs(last-sortedArr[j+1]);
			last = sortedArr[j+1];
		}
		System.out.println(last+"->200");
		countResult++;
		result[countResult] = 200;
		total += Math.abs(last-200);
		last =200;
		for (int j = idx-1; j >= 0; j--) {
			System.out.println(last+"->"+sortedArr[j]);
			countResult++;
			result[countResult] = sortedArr[j];
			total += Math.abs(last-sortedArr[j]);
			last = sortedArr[j];
		}	
		System.out.println(last+"->0");
		countResult++;
		result[countResult] = 0;
		total += Math.abs(last-0);
		result[countResult+1] = 0;
		System.out.println("The total head movement : "+total);
		
		return result;
	}

	public static int[] RunCSCAN() {
		int[] result= new int [myQueue.array.size()+3];
		int countResult=0;
		Integer[] sortedArr = myQueue.sortArray();
		int idx=myQueue.getInitialidx(sortedArr);
		int total =0 ;
		
		int last =myQueue.array.get(0);
		result[0] = last;
		for (int j = idx; j < sortedArr.length-1; j++) {
			System.out.println(last+"->"+sortedArr[j+1]);
			countResult++;
			result[countResult] = sortedArr[j+1];
			total += Math.abs(last-sortedArr[j+1]);
			last = sortedArr[j+1];
		}
		System.out.println(last+"->0");
		countResult++;
		result[countResult] = 0;
		total += Math.abs(last-0);
		result[countResult+1] = 0;
		for (int j = 0; j < idx; j++) {
			System.out.println(last+"->"+sortedArr[j]);
			countResult++;
			result[countResult] = sortedArr[j];
			total += Math.abs(last-sortedArr[j]);
			last = sortedArr[j];
		}	
		System.out.println(last+"->200");
		countResult++;
		result[countResult] = 200;
		total += Math.abs(last-200);
		last =200;
		System.out.println("The total head movement : "+total);
		
		return result;
	}
}

