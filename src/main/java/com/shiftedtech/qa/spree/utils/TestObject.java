package com.shiftedtech.qa.spree.utils;

import org.openqa.selenium.By;

/**
 * Created by ShiftTeacher on 1/14/2018.
 */
public class TestObject {

    private String testObjectId;
    private String description;
    private String page;
    private String testObjectName;
    private String using;
    private String by;

    public TestObject(){
    }

    public TestObject(String testObjectId, String description, String page, String testObjectName, String using, String by) {
        this.testObjectId = testObjectId;
        this.description = description;
        this.page = page;
        this.testObjectName = testObjectName;
        this.using = using;
        this.by = by;
    }

    public String getTestObjectId() {
        return testObjectId;
    }

    public void setTestObjectId(String testObjectId) {
        this.testObjectId = testObjectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTestObjectName() {
        return testObjectName;
    }

    public void setTestObjectName(String testObjectName) {
        this.testObjectName = testObjectName;
    }

    public String getUsing() {
        return using;
    }

    public void setUsing(String using) {
        this.using = using;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public By getFindBy() {

        if (this.by.trim().toUpperCase().contentEquals("ID")) {
            return By.id(this.using);
        } else if (this.by.trim().toUpperCase().contentEquals("NAME")) {
            return By.name(this.using);
        } else if (this.by.trim().toUpperCase().contentEquals("XPATH")) {
            return By.xpath(this.using);
        } else if (this.by.trim().toUpperCase().contentEquals("CSS")) {
            return By.cssSelector(this.using);
        } else if (this.by.trim().toUpperCase().contentEquals("CLASS_NAME")) {
            return By.className(this.using);
        } else if (this.by.trim().toUpperCase().contentEquals("LINK_TEXT")) {
            return By.linkText(this.using);
        } else if (this.by.trim().toUpperCase().contentEquals("TAGNAME")) {
            return By.tagName(this.using);
        } else if (this.by.trim().toUpperCase().contentEquals("PARTIAL_LINK_TEXT")) {
            return By.partialLinkText(this.using);
        } else {
            return null;
        }
    }


    @Override
    public String toString() {
        return "TestObject{" +
                "testObjectId='" + testObjectId + '\'' +
                ", description='" + description + '\'' +
                ", page='" + page + '\'' +
                ", testObjectName='" + testObjectName + '\'' +
                ", using='" + using + '\'' +
                ", by='" + by + '\'' +
                '}';
    }
}

