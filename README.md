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
