package jsonconverter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import com.google.gson.Gson;

import javafx.util.Pair;

/*
 * Logic:
 * - lets start now with comma seperated... key,value,key,value,key,value
 * 		- achieved.
 * - lets extend further.
 */

public class JsonCMain {
	
	private static Scanner scan = new Scanner(System.in);
	protected String keyValuePairInput;
	protected ArrayList<String> keyValueList = new ArrayList<>();
	protected HashMap<String, String> keyValueMap = new HashMap<>();
	protected ArrayList<String> keyList = new ArrayList<>();
	protected ArrayList<String> valueList = new ArrayList<>();
	protected static Gson gsonObj = new Gson();
	
	/**
	 * JSONConverter			Just a banner page. Title and stuffs.
	 */
	public static void jsonHomePageBanner() {
		System.out.println("********************************************************************************************************************************************************");
		System.out.println("\t\t\t\t\t\t\t\tJSON CONVERTER PAGE");
		System.out.println("********************************************************************************************************************************************************");
		System.out.println("Today: " + utility.Utility.getCurrentDateTime() + "\n");		
	}
	
	protected void getJsonFile(List<Pair<String, String>> dupKeyValList) {
		String json = gsonObj.toJson(dupKeyValList);
		System.out.println("\nJSON String: \n" + utility.Utility.getPrettyPrintJson(json));
		
		// Have to resolve the platform dependent path issue
		String filePath =  "src\\resources\\files\\";
		String fileName =  "json.json";

		// using try-with-resources to auto close the writer
		try (BufferedWriter bWriter = new BufferedWriter(new FileWriter (filePath + fileName))) {
			bWriter.write(json);
			System.out.println("\nFile: " + fileName + " was successfully exported. \nLocation: " + filePath + fileName);
		} catch (IOException e) {
			System.out.println(e);
			System.out.println("\nFile creation not succeeded...");
		}
		
	}

	protected void getKeyValueOut(HashMap<String, String> keyValueMap)  {
		System.out.println("\nKey:Value mapping");
		for (Entry<String, String> kv: this.keyValueMap.entrySet()) {
			System.out.println("key: " + kv.getKey() + " || value: " + kv.getValue());
		}		
	}
	
	protected void getKeyValuePairs(ArrayList<String> keyValueList) {		
		List<Pair<String, String>> dupKeyValList = new ArrayList<Pair<String, String>>();
		
		System.out.println("Input: " + keyValueList);
		
		for (int index = 0; index < keyValueList.size(); index++) {
			dupKeyValList.add(new Pair<String, String>(keyValueList.get(index), keyValueList.get(++index)));
		}
		getJsonFile(dupKeyValList);
		
	
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&");
		System.out.println(dupKeyValList.toString());
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&");
	}
	
	protected void getInput() {
		System.out.println("Input the following appropriately.\nUse key,value combination..\n\n");
		System.out.println("You can start from below:\n");
		//this.keyValuePairInput = "k1,v1,2,v2,k3,v3,@,#,$,%,^,&,*,~";
		this.keyValuePairInput = "a,s,n,m,a,z,a,q,s,w,s,x,d,e,d,x,d,f";
		this.keyValueList = new ArrayList<>(Arrays.asList(this.keyValuePairInput.split(",")));
		// a,s,n,m,a,z,a,q,s,w,s,x,d,e,d,x,d,f,f,d,f,g,f,v,g,f,g,b,g,t,h,j,h,n,h,y,h,u,j,k,j,m,j,i,k,i,k,m,l,o,l,;,l
		getKeyValuePairs(keyValueList);
	}
	
	public static void main(String[] args) {
		jsonHomePageBanner();
		
		JsonCMain jcmObj = new JsonCMain();
		jcmObj.getInput();
	}
}

/*
 2nd Version output:
 
********************************************************************************************************************************************************
JSON CONVERTER PAGE
********************************************************************************************************************************************************
Today: 2018/02/14 01:02:26

Input the following appropriately.
Use key,value combination..


You can start from below:

Input: [a, s, n, m, a, z, a, q, s, w, s, x, d, e, d, x, d, f]

JSON String: 
"[{\"key\":\"a\",\"value\":\"s\"},{\"key\":\"n\",\"value\":\"m\"},{\"key\":\"a\",\"value\":\"z\"},{\"key\":\"a\",\"value\":\"q\"},{\"key\":\"s\",\"value\":\"w\"},{\"key\":\"s\",\"value\":\"x\"},{\"key\":\"d\",\"value\":\"e\"},{\"key\":\"d\",\"value\":\"x\"},{\"key\":\"d\",\"value\":\"f\"}]"

File: json.json was successfully exported. 
Location: src\resources\files\json.json
&&&&&&&&&&&&&&&&&&&&&&&&
[a=s, n=m, a=z, a=q, s=w, s=x, d=e, d=x, d=f]
&&&&&&&&&&&&&&&&&&&&&&&&
*/