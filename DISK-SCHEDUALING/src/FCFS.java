
public class FCFS {
	myQueue requests;
	public static int[] RunFcfs() {
		
		int[] result= new int [myQueue.array.size()+1];
		int countResult=0;
		int total =0 ;

		int last =myQueue.array.get(0);
		result[0] = last;
		for (int j = 0; j < myQueue.array.size()-1; j++) {
			System.out.println(last+"->"+myQueue.array.get(j+1));
			countResult++;
			result[countResult] = myQueue.array.get(j+1);
			total += Math.abs(last-myQueue.array.get(j+1));
			last = myQueue.array.get(j+1);
			
		}
		result[countResult+1] = last;
		System.out.println("The total head movement : "+total);
		
		return result;
	}
}
