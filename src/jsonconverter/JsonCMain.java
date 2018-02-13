package jsonconverter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import com.google.gson.Gson;

/*
 * Logic:
 * - lets start now with comma seperated... key,value,key,value,key,value
 * 		- achieved.
 * - lets extend further.
 */

public class JsonCMain {
	
	//private static Scanner scan = new Scanner(System.in);
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
	
	protected void getJsonFile(HashMap<String, String> keyValueMap) {
		String json = gsonObj.toJson(keyValueMap);
		System.out.println("\nJSON String: " + json);
		
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
	
	protected void getKeyValuePairs(ArrayList<String> keyValueList) {
		System.out.println("Input: " + keyValueList);
		for (int index = 0; index < keyValueList.size(); index++) {
			this.keyValueMap.put(keyValueList.get(index), keyValueList.get(++index));
		}
		
		System.out.println("\nKey:Value mapping");
		for (Entry<String, String> kv: this.keyValueMap.entrySet()) {
			System.out.println("key: " + kv.getKey() + " || value: " + kv.getValue());
		}
		getJsonFile(keyValueMap);
	}
	
	protected void getInput() {
		System.out.println("Input the following appropriately.\nUse key,value combination..\n\n");
		System.out.println("You can start from below:\n");
		this.keyValuePairInput = "k1,v1,2,v2,k3,v3,@,#,$,%,^,&,*,~";
		this.keyValueList = new ArrayList<>(Arrays.asList(this.keyValuePairInput.split(",")));
		getKeyValuePairs(keyValueList);
	}
	
	public static void main(String[] args) {
		jsonHomePageBanner();
		
		JsonCMain jcmObj = new JsonCMain();
		jcmObj.getInput();
	}
}

/*
 *  1st version Output: (NOT BAD)
		********************************************************************************************************************************************************
																													JSON CONVERTER PAGE
		********************************************************************************************************************************************************
		Today: 2018/02/12 22:02:50
		
		Input the following appropriately.
		Use key,value combination..
		
		
		You can start from below:
		
		Input: [k1, v1, 2, v2, k3, v3, @, #, $, %, ^, &, *, ~]
		
		Key:Value mapping
		key: @ || value: #
		key: 2 || value: v2
		key: $ || value: %
		key: k1 || value: v1
		key: k3 || value: v3
		key: * || value: ~
		key: ^ || value: &
		
		JSON String: {"@":"#","2":"v2","$":"%","k1":"v1","k3":"v3","*":"~","^":"\u0026"}
		
		File: json.json was successfully exported. 
		Location: src\resources\files\json.json
*/