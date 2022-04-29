import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class myQueue {
	public static ArrayList<Integer> array = new ArrayList<Integer>();
	
	public static void getData() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("Queue.txt"));
			String line = reader.readLine();
			array.addAll(splitComma(line,reader));
			if(line != null) {
				int initial = getIntailStart(line,reader);
				array.add(0,initial);
			}
			//System.out.println(array);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Integer> splitComma(String line,BufferedReader reader) throws IOException {
		ArrayList<Integer> Requests = new ArrayList<Integer>();
		String[] values = line.split(", ");
		
		for (int i = 0; i < values.length; i++) {
			Requests.add(Integer.parseInt(values[i]));
		}
		return Requests; 
	}
	
	public static int getIntailStart(String line,BufferedReader reader) throws IOException {
		line = reader.readLine();
		String[] values = line.split("Initial head start cylinder: ");
		int initail = Integer.parseInt(values[1]);
		line = reader.readLine();
		return initail;
	}

	
	public static Integer[] sortArray() {
		Integer[] sortedArr = new Integer[array.size()];
        for (int i = 0; i < array.size(); i++)
        	sortedArr[i] = array.get(i);
		Arrays.sort(sortedArr);
		return sortedArr;
		
	}
	
	public static int getInitialidx(Integer[] sortedArr) {
		//get the new index of the intial start point
		int idx=0;
		int len = array.size();
		int i = 0;
        while (i < len) {
            if (sortedArr[i] == array.get(0)) {idx=i ;break;}
            else i=i+1;
        }
        return idx;
	}
	
	
	public static void main(String[] args) {
		   myQueue.getData();
		   System.out.println("Look Algorithm (direction of head is towards max.) : \n"+"__________");
		   Look.LookMaxDirction();
		   System.out.println("\nLook Algorithm (direction of head is towards 0.) : \n"+"__________");
		   Look.LookZeroDirction();
		   System.out.println("\nCLook Algorithm (direction of head is towards max.) : \n"+"__________");
		   Look.CLookMaxDirction();
		   System.out.println("\nCLook Algorithm (direction of head is towards 0.) : \n"+"__________");
		   Look.CLookZeroDirction();
		   System.out.println("\nFCFS Algorithm : \n"+"__________");
		   FCFS.RunFcfs();
		   System.out.println("\nSCAN Algorithm : \n"+"__________");
		   SCAN.RunSCAN();
		   
	}
}
