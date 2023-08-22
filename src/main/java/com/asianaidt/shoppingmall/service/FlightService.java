package com.asianaidt.shoppingmall.service;

import com.asianaidt.shoppingmall.dto.FlightDTO;
import com.asianaidt.shoppingmall.dto.FligthSearchDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {
    static String serviceKey ="qge9JDqMuQUjmge01aHeNYquWGG5H%2BA2DypEX68lCU0nCI%2FnWjBb4gokG69jYcEGtQxHcUu9snYTK6L1l%2FnH2A%3D%3D";
    
    public List<FlightDTO>  flight(FligthSearchDTO dto) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://openapi.airport.co.kr/service/rest/FlightScheduleList/getIflightScheduleList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=qge9JDqMuQUjmge01aHeNYquWGG5H%2BA2DypEX68lCU0nCI%2FnWjBb4gokG69jYcEGtQxHcUu9snYTK6L1l%2FnH2A%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("schArrvCityCode", "UTF-8") + "=" + dto.getSchDeptCityCode());
        urlBuilder.append("&" + URLEncoder.encode("schDeptCityCode", "UTF-8") + "=" + dto.getSchArrvCityCode());
        urlBuilder.append("&" + URLEncoder.encode("schDate", "UTF-8") + "=" + dto.getSchDate());
        urlBuilder.append("&" + URLEncoder.encode("schAirLine", "UTF-8") + "=" + URLEncoder.encode("OZ", "UTF-8"));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd= new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));;
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        List<FlightDTO> list = new ArrayList<>();

        String response = sb.toString();
        JSONObject jobj = XML.toJSONObject(response);
        JSONObject jobj1 = jobj.getJSONObject("response").getJSONObject("body");
        int totalCount = jobj1.getInt("totalCount");
        if( totalCount>1) {      	
        	JSONArray jarr = jobj1.getJSONObject("items").getJSONArray("item");
            for (int i = 0; i < jarr.length(); i++) {
                String internationalIoType= jarr.getJSONObject(i).getString("internationalIoType");
                if(internationalIoType.equals("OUT")) continue;
                String internationalNum=jarr.getJSONObject(i).getString("internationalNum");
                String airport=jarr.getJSONObject(i).getString("airport");
                String city=jarr.getJSONObject(i).getString("city");
                String internationalTime= Integer.toString(jarr.getJSONObject(i).getInt("internationalTime"));
                FlightDTO flightDTO = new FlightDTO(internationalNum, airport, city, internationalTime, internationalIoType);
                list.add(flightDTO);
                }
        }else if(totalCount==1){
        		JSONObject jarr = jobj1.getJSONObject("items").getJSONObject("item");
                String internationalIoType= jarr.getString("internationalIoType");
                if(internationalIoType.equals("IN")) {
	                String internationalNum=jarr.getString("internationalNum");
	                String airport=jarr.getString("airport");
	                String city=jarr.getString("city");
	                String internationalTime= Integer.toString(jarr.getInt("internationalTime"));
	                FlightDTO flightDTO = new FlightDTO(internationalNum, airport, city, internationalTime, internationalIoType);
	                list.add(flightDTO);
                }
        }
        
        return list;
        }
}

