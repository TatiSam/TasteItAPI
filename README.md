# TasteIt API
Spring REST API Project

If you like to travel the world and eat delicious food at the same time, then this is the API for you. It contains information about the most loved and most popular dishes in different countries. In the TasteIt API you will find information about the country that interests you and about those national dishes that you should definitely try there.

### Pagination: 
Requests that return multiple items will be limited to 25 results by default. You can access other pages using the ?page paramater:
http://tasteit-env.eba-zcmqefys.us-east-1.elasticbeanstalk.com/api/1/countries/page?pageNo=1

### Get Countries:
http://tasteit-env.eba-zcmqefys.us-east-1.elasticbeanstalk.com/api/1/countries/page

Gets Countries from the API, you can apply several filters using url parameters, the available options are listed below.

<table>
  <tr>
    <th>Param</th>
    <th>Type</th>
    <th>Default</th>
    <th>Example</th>
  </tr>
  <tr>
    <td>pageNo</td>
    <td>number</td>
    <td>0</td>
    <td>?pageNo=0</td>
  </tr>
  <tr>
    <td>pageSize</td>
    <td>number</td>
    <td style="text-align:center">25</td>
    <td>?pageSize=10</td>
  </tr>
  <tr>
    <td>sortBy</td>
    <td>id/name</td>
    <td>id</td>
    <td>?sortBy=name</td>
  </tr>
  <tr>
    <td>sortDir</td>
    <td>asc/desc</td>
    <td>asc</td>
    <td>?sortDir=desc</td>
  </tr>
</table>

### Get a single Country:
http://tasteit-env.eba-zcmqefys.us-east-1.elasticbeanstalk.com/api/1/countries/1

### Get dishes by Country id:
http://tasteit-env.eba-zcmqefys.us-east-1.elasticbeanstalk.com/api/1/countries/1/dishes

### Get comments by Country id:
http://tasteit-env.eba-zcmqefys.us-east-1.elasticbeanstalk.com/api/1/countries/1/comments

## Architecture and Tech features
<ul>
  <li>Written in <a href="https://www.java.com/">Java</a> language</li>
  <li>Uses Spring Boot, Spring Data JPA, Spring Security, Rest Repositories</li>
  <li>Uses <a href="https://spring.io/projects/spring-security">Spring Security</a> for authentication and access-control</li>
  <li>Uses BCrypt password encoder for encode password</li>
  <li>Uses <a href="https://jwt.io/">JWT</a> Token for authentication</li>
  <li>Uses <a href="https://projectlombok.org/">Lombok</a> to avoid writing repetitive Java code and/or boilerplate code.</li>
 
  <li>Uses <a href="http://modelmapper.org/">ModelMapper</a> for Object mapping</li>
  <li>Uses <a href="https://maven.apache.org/">Maven</a> for dependency management and building project</li>
  <li>Uses <a href="https://www.h2database.com/">H2</a> database for testing mod</li>
  <li>Uses <a href="https://www.mysql.com/">MySQL</a> database for production mod</li>
  
  <li>Uses TDD with integration testing</li>
  
  
  
  
 
  
  <li>Uses Three Layer Architecture Pattern</li>
  
</ul>
