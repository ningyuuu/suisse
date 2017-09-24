/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.json.JSONObject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.example.Jewellery;
import com.example.JewelleryWrapper;
import com.example.EmptyArea;
import com.example.EmptyAreaWrapper;

@Controller
@SpringBootApplication
public class Main {

  @Value("${spring.datasource.url}")
  private String dbUrl;

  @Autowired
  private DataSource dataSource;

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
  }

  @RequestMapping("/")
  String index() {
    return "index";
  }

  @RequestMapping("/db")
  String db(Map<String, Object> model) {
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
      ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

      ArrayList<String> output = new ArrayList<String>();
      while (rs.next()) {
        output.add("Read from DB: " + rs.getTimestamp("tick"));
      }

      model.put("records", output);
      return "db";
    } catch (Exception e) {
      model.put("message", e.getMessage());
      return "error";
    }
  }

  @RequestMapping(value = "/ningyu", method = RequestMethod.POST, produces="application/json")
  @ResponseBody
  public String ningyu(@RequestBody InputWrapper wrapper) {
    JSONObject response = new JSONObject();
    int number = wrapper.getNumber();
    String word = wrapper.getWord();
    int[] array = wrapper.getArray();
    number += 50;
    array[2] = 30;
    // String response = "number is " + number + " , and word is " + word;
    response.put("number", number);
    response.put("myword", word);
    response.put("this is the array received", array);
      return response.toString();
  }

  @RequestMapping(value="/heist", method=RequestMethod.POST, produces="application/json")
  @ResponseBody
  public String heist(@RequestBody JewelleryWrapper wrapper) {
    Jewellery jewellery = new Jewellery(wrapper);
    jewellery.calcResult();
    double result = jewellery.getResult();
    ArrayList<Bag> vault = jewellery.getVault();
    // double[] weight = new double[4];
    // double[] value = new double[4];
    // double[] unitvalue = new double[4];
    // for(int i=0; i<4; i++){
    //   weight[i] = vault.get(i).getWeight();
    //   value[i] = vault.get(i).getValue();
    //   unitvalue[i] = vault.get(i).getUnitValue();
    // }

    JSONObject response = new JSONObject();
    response.put("heist", result);
    // response.put("sorted weight", weight);
    // response.put("sorted value", value);
    // response.put("sorted unitvalue", unitvalue);

    return response.toString();
  }

  public static class InputWrapper {
    private int number;
    private String word;
    private int[] array;
  
    public int getNumber(){
      return number;      
    }
    public String getWord(){
      return word;
    }    
    public int[] getArray(){
      return array;
    }
  }


  @RequestMapping(value = "/sort", method = RequestMethod.POST, produces="application/json")
  @ResponseBody
  public ArrayList<Integer> sort(@RequestBody ArrayList<Integer> numbers){
    // JSONObject response = new JSONObject();
    Collections.sort(numbers);
    return numbers;    
  }

  @RequestMapping(value ="/calculateemptyarea", method=RequestMethod.POST, produces="application/json")
  @ResponseBody
  public String calculateemptyarea(@RequestBody EmptyAreaWrapper wrapper){
      // EmptyArea emptyArea = new EmptyArea(wrapper);
      // double area = 0;
      // if(wrapper.getRectangle() != null){
      //   area = emptyArea.calculateAreaRect(wrapper.getContainer(), wrapper.getRectangle());
      // }
      // if(wrapper.getSquare() != null){
      //   area = emptyArea.calculateAreaSq(wrapper.getContainer(), wrapper.getSquare());
      // }
      // if(wrapper.getCircle() != null){
      //   area = emptyArea.calculateAreaCircle(wrapper.getContainer(), wrapper.getCircle());
      // }
      // return area;      
      JSONObject response = new JSONObject();
      response.put("wrapper", wrapper);      
      response.put("container", wrapper.getContainer());
      response.put("rectangleWidth", wrapper.getRectangle().getWidth());
      response.put("rectangleheight", wrapper.getRectangle().getHeight());
      response.put("rectangleArea", wrapper.getRectangle().getArea());
      response.put("rectangleX1", wrapper.getRectangle().getCoordinate().getX());
      response.put("rectangleY1", wrapper.getRectangle().getCoordinate().getY());
      response.put("rectangleX2", wrapper.getRectangle().getx2());
      response.put("rectangleY2", wrapper.getRectangle().gety2());
      response.put("containerX1", wrapper.getContainer().getx1());
      response.put("containerY1", wrapper.getContainer().gety1());
      response.put("containerX2", wrapper.getContainer().getx2());
      response.put("containerY2", wrapper.getContainer().gety2());
      return response.toString();
  }

  @Bean
  public DataSource dataSource() throws SQLException {
    if (dbUrl == null || dbUrl.isEmpty()) {
      return new HikariDataSource();
    } else {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(dbUrl);
      return new HikariDataSource(config);
    }
  }

}
