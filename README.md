# TasteIt API
Spring Boot REST API Project

If you like to travel the world and eat delicious food at the same time, then this is the API for you. It contains information about the most loved and most popular dishes in different countries. In the TasteIt API you will find information about the country that interests you and about those national dishes that you should definitely try there.

### Pagination: 

Requests that return multiple items will be limited to 25 results by default. You can access other pages using the ?page paramater.

http://tasteit-env.eba-zcmqefys.us-east-1.elasticbeanstalk.com/api/1/countries/page?pageNo=0

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
  <li>Written in <a href="https://www.java.com/">Java</a> language uses <a href="https://spring.io/projects/spring-boot">Spring Boot</a></li>
  <li><a href="https://spring.io/projects/spring-data-jpa">Spring Data JPA</a> for working with database</li>
  <li><a href="https://spring.io/projects/spring-security">Spring Security</a> for authentication and access-control</li>
  <li><a href="https://en.wikipedia.org/wiki/Bcrypt">BCrypt</a> password encoder for encode password</li>
  <li><a href="https://jwt.io/">JWT</a> Token for authentication</li>
  <li><a href="https://projectlombok.org/">Lombok</a> to avoid writing repetitive Java code and/or boilerplate code</li>
  <li>Spring Boot Validation to validate Data Access Objects in request</li>
  <li><a href="http://modelmapper.org/">ModelMapper</a> for Object mapping</li>
  <li><a href="https://maven.apache.org/">Maven</a> for dependency management and building project</li>
  <li><a href="https://www.h2database.com/">H2</a> database for testing mode</li>
  <li><a href="https://www.mysql.com/">MySQL</a> database for production mode</li>
  <li>Design Patterns: Singleton, Builder, Dependency Injection, Three Layer Architecture</li>
  <li>TDD with integration testing</li>
  <li><a href="https://aws.amazon.com/">Amazon Web Services</a> for storage database and server side API</li>
</ul>

## Author

Tatiana Samoilenko

tatismoilenko@gmail.com

Israel

## TODO
<ul>
  <li>Get Country By Name</li>
  <li>Get Random Country</li>
  <li>Get Countries By Сontinent</li>
  <li>Get All Dishes</li>
  <li>Get Random Dish</li>
  <li>To fill Database</li>
</ul>

