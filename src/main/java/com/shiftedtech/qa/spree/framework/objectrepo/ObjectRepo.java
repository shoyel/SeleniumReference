package com.shiftedtech.qa.spree.framework.objectrepo;

import org.openqa.selenium.By;

import java.io.*;
import java.util.Properties;

/**
 * Created by ShiftTeacher on 12/16/2017.
 */
public class ObjectRepo {

    Properties prop = new Properties();
    OutputStream output = null;
    InputStream input = null;

    public ObjectRepo(){
        try {
            loadRepo();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public By testObject(String key){
       By by = null;

       String objectby = prop.getProperty(key);
       if(objectby != null){
          String[] part = objectby.split(":");
          String byName = part[0];
          String byValue = part[1];

          if(byName.toUpperCase().contentEquals("LINKTEXT")){
              by = By.linkText(byValue);
          }else if(byName.toUpperCase().contentEquals("XPATH")){
              by = By.xpath(byValue);
          }else if(byName.toUpperCase().contentEquals("ID")){
               by = By.id(byValue);
          }else if(byName.toUpperCase().contentEquals("NAME")){
               by = By.name(byValue);
          }else if(byName.toUpperCase().contentEquals("TAGNAME")){
              by = By.tagName(byValue);
          }else if(byName.toUpperCase().contentEquals("CSSSELECTOR")){
              by = By.cssSelector(byValue);
          }else if(byName.toUpperCase().contentEquals("CLASSNAME")){
              by = By.className(byValue);
          }else if(byName.toUpperCase().contentEquals("PARTIALLINKTEXT")){
              by = By.partialLinkText(byValue);
          }
       }else {
           throw new RuntimeException("Key does not exist in Object Repo");
       }

       return  by;
    }

    private void loadRepo() throws IOException {
        input = new FileInputStream("src/main/resources/" + "HomePageObjectRepo.properties");
        prop.load(input);
    }
}
