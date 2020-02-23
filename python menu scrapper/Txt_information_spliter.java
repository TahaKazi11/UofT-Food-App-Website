
import java.io.IOException;
import java.io.OutputStream;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

import org.json.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public class Txt_information_spliter {
    private String Restaurant;
    private String File;
    private JSONObject response = new JSONObject(new LinkedHashMap());
    private JSONArray Type = new JSONArray();
    private String line;
    
    public Txt_information_spliter(String Restaurant_name,String File_name) {
   
    	this.Restaurant = Restaurant_name;
    	this.File = File_name;
    
    }
   
    public String Search_Restaurant() {
        
        
        try {
			BufferedReader in = new BufferedReader(new FileReader(File));
			while((line = in.readLine()) != null){
				if (line.contains("ame:"+Restaurant)){
					System.out.println(detect_line_type(in));
				}
		    }
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return null;
    }
    public JSONObject get_tag(String line) {
    	if (line.contains(":")) {
    		String[] v = line.split(":");
    		JSONObject Tag_body = new JSONObject();
    		try {
				Tag_body.put("tag",v[0]);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		return Tag_body;
    	}
		return null;
    	
    }

    public String[] get_dishes(String line) {
    	if (line.contains(";")) {
    		String[] v = line.replace(";", "").split(",");
    		return v;
    	}
		return null;	
    }
    
    public JSONObject detect_line_type(BufferedReader in) {
    	JSONObject tag = null;
    	JSONObject whole = new JSONObject();
    	try {
			while((line = in.readLine()) != null){
				if (line.contains("ame:")){
					whole.append("tag",tag);
//					System.out.println("tag ended");
//					System.out.println(line+" end met"); 
					return whole;
				}
				if (!line.contains("ame:") && line.contains(":")){
					//tag detected
					if (tag != null) {
						System.out.println("tag detected");
						whole.append("tag",tag);
					}
					tag = get_tag(line);
				}
				else if(line.contains(";")){
					JSONObject dish = new JSONObject();
					String[] dishes = get_dishes(line);
					for (String a : dishes) {

			            dish.put("Name", a.split("=")[0]);
			            dish.put("Price", a.split("=")[1]);
//			            System.out.println(dish); 
//			            System.out.println(tag); 
			            tag.append("dishes", dish);
					}
				}
			}


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    	
    }
    
}
