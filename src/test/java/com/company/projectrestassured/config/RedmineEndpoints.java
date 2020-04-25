package com.company.projectrestassured.config;

public interface RedmineEndpoints {

    String ALL_REDMINE_ISSUES_JSON = "issues.json";
    String ALL_REDMINE_ISSUES_XML = "issues.xml";

    String SINGLE_REDMINE_ISSUE_JSON = "issues/{id}.json";
    String SINGLE_REDMINE_ISSUE_XML = "issues/{id}.xml";
}