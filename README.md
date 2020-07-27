# Nearsoft Java School Project

# nearsoftproject 1.0.0

By Engr. Armando Perea Sanchez.

[![N|Solid](https://i1.wp.com/sfnewtech.com/wp-content/uploads/directory/logo-nearsoft2.jpg)](https://nodesource.com/products/nsolid)[![Build Status](https://rgprincipal.com/es/wp-content/uploads/2018/12/Logo-de-Java-portada-250x122.jpg)](https://travis-ci.org/joemccann/dillinger)

# Development Project Comments

  - I created a new Maven Project Using SpringBoot
  - I always try to apply the SOLID principles the closest I can, I might not be an expert but I try always to deliver a       clean and understandable code so my colleagues get an easy way to modify it!
  - I added some extra operations that I thougth could be necessary while testing the app.
  - It is not necessary to restart the project to clear the url List content, you can use the "clearUrls" method specified     below.
  - The application handles duplicate entries just like a Singleton at getAlias method.
  - I decided to set specific names to each of the REST API operations since it is a better way to consume them.
   - The requeirements did  not specify to handle lower/uppercase, so this version does not handle that scenario.
   - I did not have enough time to handle every possible bad request or exception, but I'm aware of it.

# Application Run Guide

 - If you downloaded it from git , please get the master branch which must have the lastest and ultimate version.
 - Compile the project as a maven project (mvn clean install)
 - Run the project as springboot Application, there are no VM Arguments
 - Default service port is 8080
 - It is important to configure lombok at your local eclipse/intelliJ environment

 
# Endpoints to process

## createUrl
POST EXAMPLE: http://localhost:8080/nearsoft/system/url-generator/createUrl
JSON BODY: 
{
    "url": "www.yahoo.com"
}

{
    "url": "www.google.com"
}
*** Observation Here, in the Requeirements was not specified to avoid any special character for url different from google or Yahoo, so, there are defects when using getOperationsByEngine if the URL contains any % (Special Character)
{
    "url": "www.oc34n5Deep458uiodkj.com"
}

## getUrlByAlias

GET EXAMPLE FOR GOOGLE : http://localhost:8080/nearsoft/system/url-generator/getUrlByAlias/dNtQc
{
    "url": "www.google.com"
}

GET EXAMPLE FOR YAHOO : http://localhost:8080/nearsoft/system/url-generator/getUrlByAlias/25HArvF
{
    "url": "www.yahoo.com"
}

GET EXAMPLE FOR OTHERS : http://localhost:8080/nearsoft/system/url-generator/getUrlByAlias/wwwcnsSXcm
{
    "url": "www.oc34ns56ASX78#.com"
}

## getAllUrl

GET EXAMPLE: http://localhost:8080/nearsoft/system/url-generator/getAllUrl
JSON RESPONSE: 
{
    "urlContent": {
        "www.yahoo.com": "25HArvF",
        "www.oc34ns56ASX78.com": "wwwcnsSXcm",
        "www.google.com": "dNtQc"
    }
}

## deleteUrlByEngine

DELETE EXAMPLE: http://localhost:8080/nearsoft/system/url-generator/deleteUrlByEngine/www.oc34ns56ASX78.com
RESPONSE:
URL WAS REMOVED SUCCESSFULY 

** Important, since there are no validations for urls special characters content, if the url was: www.oc34ns56AS%X78#.com
The response would be:
URL IS NOT ON THE LIST
Since the REST operation struggles with % # or any special character that affects it.

## clearUrls

DELETE EXAMPLE: http://localhost:8080/nearsoft/system/url-generator/clearUrls
RESPONSE:
URL WAS REMOVED SUCCESSFULY

## deleteUrlByAlias

DELETE EXAMPLE: http://localhost:8080/nearsoft/system/url-generator/deleteUrlByAlias/wwwcnsSXcm
RESPONSE:
URL CONTENT WAS CLEARED SUCCESSFULY


Please let me know if my development was accordingly and If there was a way to do it better, I would Appreciate it, no matter if I don't full fill your expectations, I'm always learning new things. Thanks for the opportunity!
