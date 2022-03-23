# Taste It API
Spring REST API Project

### Root Endpoint:
http://tasteit-env.eba-zcmqefys.us-east-1.elasticbeanstalk.com/api/1/

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
