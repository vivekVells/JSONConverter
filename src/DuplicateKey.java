import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.Gson;
 
public class DuplicateKey {
 
 static Map<String, ArrayList<String>> hashMap = new HashMap<String,  ArrayList<String>>();
 static Gson gson = new Gson();
 
 public static void main(String[] args) {
  retrieveValues();
 } 
 public static void retrieveValues()
 {   
  addValues("A", "a1");
  addValues("A", "a2");
  addValues("A", "a3");
  addValues("A", "a4");
  addValues("B", "b1");
  addValues("B", "b2");
  addValues("C", "c1");   
   
  Iterator<String> it = hashMap.keySet().iterator(); 
  ArrayList<String> tempList = null;
 
       while(it.hasNext()){ 
           String key = it.next().toString();
             tempList = hashMap.get(key);
            if(tempList != null){
                for(String value: tempList){ 
                   System.out.println("Key : "+key+ " ,  Value : "+value); }
               } 
        }
       String json = gson.toJson(hashMap);
       System.out.println(json);
 }  
 //Method to add duplicate key and values
 private static void addValues(String key, String value)
 { 
  ArrayList<String> tempList = null;      
  if(hashMap.containsKey(key)){  
   tempList=hashMap.get(key); 
   if(tempList == null)    
     tempList = new ArrayList<String>();     
   tempList.add(value);    
  }
  else
  {     
   tempList = new ArrayList();  
   tempList.add(value);     
   }     
  hashMap.put(key,tempList);
 } 
}