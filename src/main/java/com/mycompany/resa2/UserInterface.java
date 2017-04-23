/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.resa2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author bacvikto
 */
public class UserInterface {
    ArrayList<Coordinates> coordinates;
    public int []array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    double minDistance = 1000000;
    int [] optimalRoute;
    public UserInterface() throws IOException, FileNotFoundException, ParseException
    {
        menu();
    }
    
    public void menu() throws FileNotFoundException, IOException, ParseException {
        
        //Läser in JSON filen
        FileReader fr = new FileReader("waypoints.json");
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(fr);
        JSONArray ja = (JSONArray) jo.get("waypoints");

            coordinates = new ArrayList();

        for (int i = 0; i < ja.size(); i++) {
            JSONObject jsonElement = (JSONObject) ja.get(i);
            String city = jsonElement.get("city").toString();

            String latitudestring = jsonElement.get("latitude").toString();
            double latitude = Double.parseDouble(latitudestring);

            String longitudestring = jsonElement.get("longitude").toString();
            double longitude = Double.parseDouble(longitudestring);
            Coordinates coordinate = new Coordinates(city, latitude, longitude);
            coordinates.add(coordinate);
        }       
        
        //Vi vill hitta alla möjliga kombinationer i tabellen indexes mellan 2:a och 
        //sista elementet (i=1 till i=antal element) 
        permute(array, 1, array.length);
        
        //Printa kortaste rutten på rutan efter att alla ruttkombinationer gåtts igenom
        System.out.println(coordinates.get(array[0]).getCity()+"->"+coordinates.get(array[1]).getCity()+"->"+coordinates.get(array[2]).getCity()+"->"+coordinates.get(array[3]).getCity()+"->"+coordinates.get(array[4]).getCity()+"->"+coordinates.get(array[5]).getCity()+"->"+coordinates.get(array[6]).getCity()+"->"+coordinates.get(array[7]).getCity()+"->"+coordinates.get(array[8]).getCity()+"->"+coordinates.get(array[9]).getCity()+"->"+coordinates.get(array[0]).getCity());
        System.out.println("Rutten är "+minDistance+" km lång.");

        

    }

    public double getDistance(double lon1, double lat1, double lon2, double lat2) {
        lon1 = lon1 * Math.PI / 180.0;
        lat1 = lat1 * Math.PI / 180.0;
        lon2 = lon2 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;

        //haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double km = 6367 * c;

        return km;
    }

    public void permute(int[] array, int i, int length) {
        if (length == i) {
            //En ny kombination har generarats och skrivs ut
            for (int k = 0; k < array.length; k++) {
                //System.out.print(array[k]);
                //double distance = getDistance(coordinates.get(k).getLongitude(), coordinates.get(k).getLatitude(), coordinates.get(k+1).getLongitude(), coordinates.get(k+1).getLatitude());
                //System.out.println(coordinates.get(k).getLongitude());
            }
            
            //System.out.print("\n");
            routeDistance();
            
            return;
        }
        int j = i;
        for (j = i; j < length; j++) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            permute(array, i + 1, length);
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return;

    }
    public double routeDistance()
{
	double distance = 0; 
        
        //Räknar distansen mellan alla koordinater i arrayen och sedan tillbaka till h:fors

	for(int i = 0; i < coordinates.size() - 1; i++)
	{
			double lat1 = coordinates.get(array[i]).getLatitude();
			double lon1 = coordinates.get(array[i]).getLongitude();
			double lat2 = coordinates.get(array[i+1]).getLatitude();
			double lon2 = coordinates.get(array[i+1]).getLongitude();
	
			distance += getDistance(lat1, lon1, lat2, lon2);
	}
        double lat1 = coordinates.get(array[0]).getLatitude();
	double lon1 = coordinates.get(array[0]).getLongitude();
	double lat2 = coordinates.get(array[coordinates.size() - 1]).getLatitude();
	double lon2 = coordinates.get(array[coordinates.size() - 1]).getLongitude();
        distance += getDistance(lat1, lon1, lat2, lon2);

        if (distance < minDistance) 
        {
            minDistance = distance; 
            optimalRoute = array;
        }
	return distance;
}
    
}
