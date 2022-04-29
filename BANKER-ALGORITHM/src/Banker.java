

import java.util.Scanner;
public class Banker{
    private static int available[];
	private static int need[][];
	private static int max[][];
	private static int allocation[][];
	private static int allocationPath[];
	private static boolean isFinished[];
	private static int n;
	private static int m;
    static int numOfProcess;

    Banker(int x, int y){
    	n = x;
        m = y;
        available = new int[y];
        max = new int[x][y];
        allocation = new int[x][y]; 
        need = new int[x][y];
        allocationPath = new int[x];
        isFinished = new boolean[x];
        for(int i = 0; i < x; i++) {
        	isFinished[i] = false;
        }
    }
    
    private static int[][] calculate(){
    	need = new int [n][m];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
        return need;
    }
    private static int getProcess(){
    	int max = 0;
    	for(int i = 0; i < n; i++){
    		boolean isReleased = true;
    		for(int j = 0; j < m; j++){
    			if(allocation[i][j] != 0) {
    				isReleased = false;
    			}	
            }
    		if(!isReleased) {
	            int sum = 0;
	            for(int j = 0; j < m; j++){
	            	sum += need[i][j];
	            }
	            //calculate();
	            if(sum > max){
	            	max = sum;
	            	numOfProcess = i;
	            }
    		}
        }
		return numOfProcess;
    }
    
    private static boolean isSafe(int safeAlloc[][], int safeMax[][], int safeAvail[]){
	    int safeNeed[][];
	    safeNeed = new int[n][m];
	    for (int i = 0; i < n; i++){
	        for (int j = 0; j < m; j++){
	            safeNeed[i][j] = safeMax[i][j] - safeAlloc[i][j];
	        }
	    }
	    
	    boolean finished[];
	    finished = new boolean[n];
	    for(int i = 0; i < n; i++) finished[i] = false;
	   
	    int safeAvailCopy[];
	    safeAvailCopy = new int[m];
	    for(int i = 0; i < m; i++) safeAvailCopy[i] = safeAvail[i];
	
	    int counter = 0;
	    while(counter != n){
	        boolean isFound = false;
	        for(int i = 0; i < n; i++){
	            if(!finished[i]){
	            	boolean valid = true;
	                for(int j = 0; j < m; j++) {
	                	if(need[i][j] > safeAvailCopy[j]) {
	                		valid = false;
	                		break;
	                	}	
	                }
	                if(valid){
	                    for(int k = 0 ; k < m; k++) safeAvailCopy[k] += safeAlloc[i][k];
	                    counter++;
	                    finished[i] = true;
	                    isFound = true;
	                }
	            }
	        }
	        if(!isFound){
	        	System.out.println("System is not in safe state");
	        	return false;
	        }
	    }
	    System.out.println("System is in safe state");
	    return true;
    }
    
   
    private static int getFinishedNum() {
    	int counter = 0;
    	for(int i = 0; i < n; i++) if(isFinished[i] == true) counter++;
    	return counter;
    }
    public static void recover(){
    	int num = getProcess();
    	for(int i = 0; i < m; i++){
    		available[i] += allocation[num][i];
    		allocation[num][i] = 0;
    	}
    	need[num] = max[num];
    	isFinished[num] = false;
    	System.out.println("Process #" + num + " released.");
    	if(isSafe(allocation, max, available)) return;
    	recover();
   }
    
    
    public static boolean request(int index, int[] request){
    		boolean valid = true;
    		for(int i = 0; i < m; i++) {
    			if(request[i] > need[index][i] || request[i] > available[i]) {
    				valid = false;
    				break;
    			}
    		}
    		if(valid){
    			for (int i = 0; i < m; i++) {
        			//available[i] -= request[i];
        			//allocation[index][i] += request[i];
        			//need[index][i] = max[index][i] - allocation[index][i];
    				allocation[index][i] += request[i];
        		}
    			if(isSafe(allocation, max, available)) {
    				for (int i = 0; i < m; i++) {
    					available[i] -= request[i];
        				need[index][i] = max[index][i] - allocation[index][i];
    				}
    				System.out.println("Request Accepted");
    				return true;
    			}
    			for (int i = 0; i < m; i++) {
    				allocation[index][i] -= request[i];
    			}
    			System.out.println("Request Denied");
    			return false;
    		}
    		System.out.println("Request Denied");
    		return false;
    }
        
	public static void releaseResources(int index, int[] release) {
		boolean valid = true;
		for(int i = 0; i < m; i++) {
			if(release[i] > allocation[index][i]) {
				valid = false;
				break;
			}
		}
		if(valid) {
			System.out.print("\n Process# " + index + " has been released. ");
			for (int i = 0; i < m; i++) System.out.print(release[i] + " ");
			
			for (int i = 0; i < m; i++) {
				available[i] += release[i];
				allocation[index][i] -= release[i];
				need[index][i] = max[index][i] + allocation[index][i];
			}
			for (int j = 0; j < m; j++) System.out.println(" New Available is >> "+available[j]);
			for (int j = 0; j < m; j++) System.out.println(" New Allocated is >> "+allocation[index][j]);
		}
	}
    
 
    
    private static boolean canAllocate(int i){
        for(int j = 0; j < m; j++) if(available[j] < need[i][j]) return false;
        return true;
    }
    public static void BankerEx(){
        calculate();
    	int count = getFinishedNum();
        while(count < n){
            for(int i = 0; i < n; i++){
                    if(canAllocate(i) && !isFinished[i]){
                        for(int j = 0; j < m; j++){
                            System.out.println(" New Available is >> " + available[j]);
                            available[j] += allocation[i][j];
                        }
                        allocationPath[count] = i;
                        count++;
                        System.out.println("P" + i);
                        isFinished[i] = true;
                    }
            }
        }
    }
    public static void main(String[] args){
       Scanner input = new Scanner(System.in);
       int x = input.nextInt();
       int y = input.nextInt();
       new Banker(x, y);
       System.out.println("Enter Allocation:");
       for(int i = 0; i < Banker.n; i++){
           for(int j = 0; j < Banker.m; j++){
               Banker.allocation[i][j] = input.nextInt();
           }
       }
       System.out.println("Enter Max:");
        for(int i = 0; i < Banker.n; i++){
            for(int j = 0; j < Banker.m; j++){
                Banker.max[i][j] = input.nextInt();
            }
        }
        System.out.println("Enter Available:");
        for (int i = 0; i < Banker.m; i++){
            Banker.available[i] = input.nextInt();
        }
        Banker.calculate();
        System.out.println(getProcess());
        System.out.println("Enter Commands:");
        String line;
        while(true){
        	line = input.nextLine();
        	if(line.contains("Quit")) break;
        	else if(line.contains("RQ")) {
        		String[] arr = line.split(" ");
        		int idx = Integer.parseInt(arr[1]);
        		int resourceArr[];
        		resourceArr = new int[m];
        		int counter = 0;
        		for(int i = 2; i < arr.length; i++) {
        			resourceArr[counter++] = Integer.parseInt(arr[i]);
        		}
        		Banker.request(idx, resourceArr);
        	}
        	else if(line.contains("RL")) {
        		String[] arr = line.split(" ");
        		int idx = Integer.parseInt(arr[1]);
        		int resourceArr[];
        		resourceArr = new int[m];
        		int counter = 0;
        		for(int i = 2; i < arr.length; i++) {
        			resourceArr[counter++] = Integer.parseInt(arr[i]);
        		}
        		Banker.releaseResources(idx, resourceArr);
        	}
        	else if(line.contains("Recover")){
        		Banker.recover();
        	}
        }
       //Banker.BankerEx();
    }
}