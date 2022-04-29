
public class  Look {
	myQueue requests;
	public static int[] LookMaxDirction() {
		
		int[] result= new int [myQueue.array.size()+1];
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
		
		for (int j = idx-1; j >= 0; j--) {
			System.out.println(last+"->"+sortedArr[j]);
			countResult++;
			result[countResult] = sortedArr[j];
			total += Math.abs(last-sortedArr[j]);
			last = sortedArr[j];
		}	
		
		result[countResult+1] = last;
		System.out.println("The total head movement : "+total);
		
		return result;
	}

	public static int[] LookZeroDirction() {
	
	int[] result= new int [myQueue.array.size()+1];
	int countResult=0;
	Integer[] sortedArr = myQueue.sortArray();
	int idx=myQueue.getInitialidx(sortedArr);
	int total =0 ;

	int last =myQueue.array.get(0);
	result[0] = last;
	for (int j = idx-1; j >= 0; j--) {
		System.out.println(last+"->"+sortedArr[j]);
		countResult++;
		result[countResult] = sortedArr[j];
		total += Math.abs(last-sortedArr[j]);
		last = sortedArr[j];
	}	
	
	for (int j = idx; j < sortedArr.length-1; j++) {
		System.out.println(last+"->"+sortedArr[j+1]);
		countResult++;
		result[countResult] = sortedArr[j+1];
		total += Math.abs(last-sortedArr[j+1]);
		last = sortedArr[j+1];
		
	}
	
	result[countResult+1] = last;
	System.out.println("The total head movement : "+total);
	
	return result;
}
	
	public static int[] CLookMaxDirction() {
	
		int[] result= new int [myQueue.array.size()+1];
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
		for (int j = 0; j < idx; j++) {
			System.out.println(last+"->"+sortedArr[j]);
			countResult++;
			result[countResult] = sortedArr[j];
			total += Math.abs(last-sortedArr[j]);
			last = sortedArr[j];
		}	
		result[countResult+1] = last;
		System.out.println("The total head movement : "+total);
		
		return result;
}

	public static int[] CLookZeroDirction() {
	
		int[] result= new int [myQueue.array.size()+1];
		int countResult=0;
		Integer[] sortedArr = myQueue.sortArray();
		int idx=myQueue.getInitialidx(sortedArr);
		int total =0 ;
		
		int last =myQueue.array.get(0);
		result[0] = last;
		int idxLast =0;
		for (int j = idx; j > 0; j--) {
			System.out.println(last+"->"+sortedArr[j-1]);
			countResult++;
			result[countResult] = sortedArr[j-1];
			total += Math.abs(last-sortedArr[j-1]);
			last = sortedArr[j-1];
		}
		for (int j = sortedArr.length-1; j >idx; j--) {
			System.out.println(last+"->"+sortedArr[j]);
			countResult++;
			result[countResult] = sortedArr[j];
			total += Math.abs(last-sortedArr[j]);
			last = sortedArr[j];
		}	
		result[countResult+1] = last;
		System.out.println("The total head movement : "+total);
		return result;

}
}