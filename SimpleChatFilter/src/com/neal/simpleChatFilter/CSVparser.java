package com.neal.simpleChatFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CSVparser {
	
	static List<String> returnlist = new ArrayList<String>();
	
    public static List<String> parse(String filepath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filepath));
        scanner.useDelimiter(",");
        while(scanner.hasNext()){
        	returnlist.add(scanner.next().replace("\n", "").replace("\r", ""));
        }
        scanner.close();
		return returnlist;
    }

}