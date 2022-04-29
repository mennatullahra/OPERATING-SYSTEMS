package cld;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.NoSuchFileException;
import java.util.Arrays;
import java.util.Scanner;

public class main {

    /**
     * @param args the command line arguments
     * @throws IOException 
     */
    public static class Parser {
    String commandName; 
    String[] args;
    String path;
    String[] inputFeilds;
    public boolean  parse(String input){

    if(input.contains("\\")){
               int index=input.indexOf(" ");
               path=input.substring(index+1,input.length());
               this.path=path;
           }

        String[] inputFeilds = input.split(" ");
        if (inputFeilds.length == 0)
			return false;

		commandName = inputFeilds[0].trim();

		int numOfArgs = inputFeilds.length - 1;
		args = new String[numOfArgs];
		for (int i = 0; i < inputFeilds.length-1; i++) {
			args[i] = inputFeilds[i + 1].trim();
		}
                if (commandName.equals("echo") && args.length == 1)
			return true;
                else if (commandName.equals("pwd") && args.length == 0)
			return true;
                else if (commandName.equals("cd") )
			return true;
                else if (commandName.equals("cd..") && args.length == 0)
			return true;
        
                else if (commandName.equals("ls") && args.length == 0)
			return true;
                else if (commandName.equals("ls") && args.length == 1&& args[0].equals("-r"))
			return true;
                else if (commandName.equals("mkdir") )
			return true;
                else if (commandName.equals("rmdir") )
			return true;
                else if (commandName.equals("touch") && args.length > 0)
			return true;
                else if (commandName.equals("cp") && args.length == 2)
			return true;
                else if (commandName.equals("cp") && args[0].equals("-r") && args.length == 3)
			return true;
                else if (commandName.equals("rm") && args.length == 1)
			return true;
                else if (commandName.equals("cat") && args.length > 0)
			return true;
                
		return false;
    };
    public String getCommandName(){
        return commandName;
    };
    public String[] getArgs(){
        return args;
    };
}


public static class Terminal {
    Parser parser;
    
    
    //public static String defaultPath="D:\\FCI\\Level 3\\Third year _term 1\\Operating System\\Assignments\\TestfFolder";
    public static String defaultPath = System.getProperty("user.dir");
    
    public void setParser(Parser parser){
          this.parser=parser; 
     };
    public Parser getParser(){
          return parser; 
     };
    
    public void echo(String arg){
          System.out.println(arg); 
     };
    public void pwd(){
         System.out.println(defaultPath); 
    }
    public void cd(){
        defaultPath= System.getProperty("user.dir");
    }
    public void cd2(){
        String newPath="";
        String [] pathParts=defaultPath.split("\\\\");
        for(int i=0;i<pathParts.length-2;i++){
            newPath+=pathParts[i]+"\\";
            
        }
        newPath+=pathParts[pathParts.length-2];
        defaultPath=newPath;
       // System.out.println("  " + defaultPath);
       
        
    }
    public void cd3(String path){
        String fullPath;
		if (new File(path).exists()) {
			fullPath = path;
			defaultPath = fullPath;

		} else if (new File(defaultPath + "\\" + path).exists()) {
			fullPath = defaultPath + "\\" + path;
			defaultPath = fullPath;

		} else {
			System.out.println("Folder not found!");
		}
    }

    public static void ls() {
		File file = new File(defaultPath);
		File[] sortedFiles = file.listFiles();
		Arrays.sort(sortedFiles);
		String sorted = "";

		for (int i=0;i<sortedFiles.length-1;i++) {
			sorted += (sortedFiles[i].getName())+"\n";
		}
                sorted += (sortedFiles[sortedFiles.length-1].getName());
		System.out.println(sorted);
	}
    public void ls_r(){
            File file = new File(defaultPath);
		File[] sortedFiles = file.listFiles();
		Arrays.sort(sortedFiles);
		String sorted = "";

		for (int i=sortedFiles.length-1;i>=0;i--) {
			sorted += (sortedFiles[i].getName())+"\n";
		}
                
		System.out.println(sorted);
    };
    public static void mkdir(String path) {
        File file = new File(defaultPath + "\\" + path);
            if (path.contains(":")) {
                    file = new File(path);

            }
		if (!file.exists()) {
			boolean result = file.mkdir();
			if (result) {
				System.out.println("Successfully created ");
                             //    + file.getAbsolutePath()
			} else {
				System.out.println("Failed creating " );
			}
		} else {
			System.out.println("Directory already exists");
		}
	}
    
	
	public static void touch(String path) {
    	File file2 ;
    	if (path.contains(":")) 
               file2 = new File(path);
        else
            file2 = new File(defaultPath + "\\" + path);
        try{
	     if (file2.createNewFile()) {
                 System.out.println("Successfully created ");
				
			} else {
				System.out.println("Failed creating");
			}
        }catch (IOException e) {
			      System.out.println("An error occurred.");
			     
			    }

};

	public static void rm(String path) {
            File file ;
            if (path.contains(":")) 
                   file = new File(path);
            else
                file = new File(defaultPath + "\\" + path);
		if (file.exists()) {
			boolean result = file.delete();
			if (result) {
				System.out.println("Successfully deleted ");
			} else {
				System.out.println("Failed deleting " );
			}
		} else {
			System.out.println("Directory not exists");
		}
	};

	public static void cat(String path) {
		  BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(defaultPath+"\\" + path));
			String line = null;
			  StringBuilder  stringBuilder = new StringBuilder();
			  try {
				while ((line = br.readLine()) != null) {
				  	stringBuilder.append(line);
				  }
			} catch (IOException e) {
				e.printStackTrace();
			}
			  try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			  System.out.println(stringBuilder);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		  
		  
	};

	public static void cat(String path1,String path2) {
		cat(path1);
		cat(path2);
	}
	
	public void cp (String first, String second) {
	    
		String data = "";
		File FirstFile= new File(defaultPath,first);
		File SecondFile=new File(defaultPath,second);
		boolean checkFirst = FirstFile.exists();
		boolean checkSecond = SecondFile.exists();
		if (checkFirst && checkSecond) {
			try {
			      
			      Scanner myReader = new Scanner(FirstFile);
			      while (myReader.hasNextLine()) {
			        data = myReader.nextLine();
			      }
			      myReader.close();
			    } catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
			try {
			      FileWriter myWriter = new FileWriter(SecondFile);
			      myWriter.write(data);
			      myWriter.close();
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		}
		else {
			System.out.println("files not found");
		}
	}
	
	public void cp_r(File sourceLocation, File targetLocation)
	        throws IOException {
	    if (sourceLocation.isDirectory()) {
	        if (!targetLocation.exists()) {
	            targetLocation.mkdir();
	        }
	            String[] children = sourceLocation.list();
	            for (String children1 : children) {
	                cp_r(new File(sourceLocation, children1), new File(targetLocation, children1));
	            }
	    } else {
	
	        OutputStream out;
	        try (InputStream in = new FileInputStream(sourceLocation)) {
	            out = new FileOutputStream(targetLocation);
	            // Copy the bits from instream to outstream
	            byte[] buf = new byte[1024];
	            int len;
	            while ((len = in.read(buf)) > 0) {
	                out.write(buf, 0, len);
	            }
	        }
	        out.close();
	    }
	}


	public void rmdir(String path) throws NoSuchFileException {
	    if (!path.contains(":")) {
	    	path = Terminal.defaultPath + "\\" + path;
	    }
	    File file = new File(path);
	    if (!file.exists()) {
	        throw new NoSuchFileException(file.getAbsolutePath(), (String)null, "doesn't exist");
	    } else {
	        if (file.isDirectory()) {
	            if (file.list().length == 0) {
	            	file.delete();
	                System.out.println("Directory is deleted Successfully");
	            } else {
	                System.out.println("Couldn't delete the directory because it's not empty");
	            }
	        } else {
	            System.out.println("couldn't delete it because This is a file not a directory");
	        }
	
	    }
	}
        public void rmdir2() throws NoSuchFileException {
	   
	    File file = new File(defaultPath);
            File[] files = file.listFiles();
            for (int i = 0; i <files.length ; i++) {
                if(files[i].isDirectory())
                {
                   rmdir(files[i].getName());
                }
            }
	}
	
	
    public void chooseCommandAction(Terminal terminal) throws IOException {
        String commandName= terminal.getParser().getCommandName();
                if (commandName.equals("echo") ){
                    String arg=terminal.getParser().args[0];
                    terminal.echo(arg);

                }
                else if (commandName.equals("pwd"))
			terminal.pwd();
                else if (commandName.equals("cd") && terminal.getParser().args.length == 0 )
			terminal.cd();
                else if (commandName.equals("cd") && terminal.getParser().args.length == 1 && terminal.getParser().args[0].equals(".."))
			terminal.cd2();
                else if (commandName.equals("cd") && terminal.getParser().args[0]!=".."){
                     String arg=terminal.getParser().path;
                    
                    String[] args=terminal.getParser().getArgs();
                    if(args.length==1)
                    {
                        terminal.cd3(args[0]);
                    }
                    else 
                        terminal.cd3(arg);
                }
                
                else if (commandName.equals("cd..") ){
                    terminal.cd2();
                   
                }
                
                else if (commandName.equals("ls") && terminal.getParser().args.length == 0)
			terminal.ls();
                else if (commandName.equals("ls") && terminal.getParser().args.length == 1&& terminal.getParser().args[0].equals("-r"))
			terminal.ls_r();
                else if (commandName.equals("mkdir") ){
                    String arg=terminal.getParser().path;
                    
                    String[] args=terminal.getParser().getArgs();
                    if(args.length==1)
                    {
                        terminal.mkdir(args[0]);
                    }
                    else 
                        terminal.mkdir(arg);
   
               }
                else if (commandName.equals("touch") ){
                    
                    String arg=terminal.getParser().path;
                    
                    String[] args=terminal.getParser().getArgs();
                    if(args.length==1)
                    {
                        terminal.touch(args[0]);
                    }
                    else 
                        terminal.touch(arg);

                    
               }
                else if (commandName.equals("rm")&& terminal.getParser().args.length == 1 ){
                    String arg=terminal.getParser().path;
                    
                    String[] args=terminal.getParser().getArgs();
                    if(args.length==1)
                    {
                        terminal.rm(args[0]);
                    }
                    else 
                        terminal.rm(arg);
               }
                else if (commandName.equals("cat")&& terminal.getParser().args.length == 1 ){
                    String[] args=terminal.getParser().getArgs();
                    String arg = args[0];
                    terminal.cat(arg);
               }
                else if (commandName.equals("cat")&& terminal.getParser().args.length == 2 ){
                    String[] args=terminal.getParser().getArgs();
                    terminal.cat(args[0]);
                    terminal.cat(args[1]);
               }
                else if (commandName.equals("rmdir")){
                     String arg=terminal.getParser().path;
                    
                    String[] args=terminal.getParser().getArgs();
                    if(args.length==1 && "*".equals(args[0]))
                        terminal.rmdir2();
                    else if(args.length==1)
                    {
                        terminal.rmdir(args[0]);
                    }
                    else 
                        terminal.rmdir(arg);
                   
               } 
                else if (commandName.equals("cp")&& terminal.getParser().args.length == 2 ){
               	 String [] args=terminal.getParser().getArgs();
               	 String arg1 =args[0];
               	 String arg2 =args[1];
               	 System.out.println(arg1+" "+arg2);
                    terminal.cp(arg1,arg2);
               }
                else if (commandName.equals("cp")&& terminal.getParser().args.length == 3&&terminal.getParser().args[0].equals("-r")) {
                    
                        File sourceLocation = new File(defaultPath+"\\"+parser.getArgs()[1]);
                        File targetLocation = new File(defaultPath+"\\"+parser.getArgs()[2]);
                        cp_r(sourceLocation, targetLocation);
                    }
    };
}




    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        
         
        Parser parser=new Parser();
        Terminal terminal=new Terminal();
        terminal.setParser(parser);
    
        System.out.println(">"); 
        String command=input.nextLine() ;
         while(!command.equals("exit")){
              boolean check =terminal.getParser().parse(command);
               
              if(check){
                  terminal.chooseCommandAction(terminal);
                  
              }
              else{
                  System.out.println("Wrong Command"); 
              }
               System.out.println(">"); 
                command=input.nextLine() ;
             
         }
        
     

    }
    
}
