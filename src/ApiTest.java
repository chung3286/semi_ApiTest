import java.io.BufferedInputStream;
import java.net.URL;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
public class ApiTest {
	public static int num = 1;
	public static int nationNm = 0;
    public ApiTest() throws Exception{
         
         JSONParser jsonparser = new JSONParser();
         JSONObject jsonobject = (JSONObject)jsonparser.parse(readUrl());
         /*
         JSONObject json =  (JSONObject) jsonobject.get("movieListResult");
         JSONArray array = (JSONArray)json.get("movieList");
         for(int i = 0 ; i < array.size(); i++) {
             JSONObject entity = (JSONObject)array.get(i);
             String movieNm = (String) entity.get("movieNm");
             JSONArray director = (JSONArray) entity.get("directors");
             //System.out.println(director);
             if(director.size() >= 1) {
                 JSONObject director2 = (JSONObject) director.get(0);
                 String directors = (String) director2.get("peopleNm");
            	 System.out.println("Page " + num + ", " + (i+1) + " " + movieNm + " => derectors : " + directors);
             }
             else
            	 System.out.println("Page " + num + ", " + (i+1) + " " + movieNm);           
         }     
         num++;
         */
          JSONArray array = (JSONArray) jsonobject.get("codes");
         //System.out.println(array);
         for(int i = 0 ; i < array.size(); i++) {
             JSONObject entity = (JSONObject)array.get(i);
             //System.out.println(entity);
             String korNm = (String) entity.get("korNm");
             String fullCd = (String) entity.get("fullCd");
             System.out.printf("%d %s  code : %-10s  / ", i+1, korNm, fullCd);
             if(i%5==4)
            	 System.out.println();
         }     
         num++;
         
    }
    private static String readUrl() throws Exception {
        BufferedInputStream reader = null;
        try {
        	// 영화 추천 목록
            //URL url = new URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json?key=ed31013ae071bc9449eec7b79b961036&itemPerPage=5&curPage=" + num + "&repNationCd=" + nationNm);
        	// 나라 고르기 코드 출력
            URL url = new URL("http://www.kobis.or.kr/kobisopenapi/webservice/rest/code/searchCodeList.json?key=ed31013ae071bc9449eec7b79b961036&comCode="+2204);
        	
        	reader = new BufferedInputStream(url.openStream());
            StringBuffer buffer = new StringBuffer();
            int i;
            byte[] b = new byte[4096];
            while( (i = reader.read(b)) != -1){
              buffer.append(new String(b, 0, i));
            }
            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
 
    
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        // TODO Auto-generated method stub
        try {
        	nationNm = 22042002;
        	for(int i=0; i<4; i++) {
                new ApiTest();
                
        	}
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            sc.close();
        }
        sc.close();
    }
 
}


// 위키피디아 기본 https://ko.wikipedia.org/wiki/ + 검색어      