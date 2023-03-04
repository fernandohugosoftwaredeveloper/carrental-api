<h1>Car Rental CRUD API</h1>

>Status: Development

<p>This API allows the management of cars available for lease. Users can list, create, update and delete cars through the API. Authentication is required to access endpoints, using an API key in the authorization header.</p>

<h2>Documentation</h2>
<p>The API documentation is available in OpenAPI Specification (OAS) format in the API root at <code>/api-docs</code>.</p>
<h2>Available Endpoints</h2>
<p>The API has the following endpoints:</p>


<h2>Base URL</h2>
<p>The base URL for all endpoints is:</p>

http://localhost:8080/api/v1
http://localhost:8080/api/v2

<p><code>GET /cars/available</code></p>
<p>Returns a list of all available cars. Results can be filtered by model or brand using the <code>model</code> and <code>brand</code> query parameters.</p>
<h4>Parameters</h4>
<table>
  <thead>
    <tr>
      <th>Request</th>
      <th>Query Parameters</th>
      <th>Data Type</th>
      <th>Description</th>
      <th>Default</th>
      <th>Maximum</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>GET</td>
      <td>model</td>
      <td>string</td>
      <td>Filter by model</td>
      <td>Not specified</td>
      <td>N/A</td>
    </tr>
    <tr>
      <td></td>
      <td>brand</td>
      <td>string</td>
      <td>Filter by brand</td>
      <td>Not specified</td>
      <td>N/A</td>
    </tr>
    <tr>
      <td></td>
      <td>page</td>
      <td>integer</td>
      <td>The page number</td>
      <td>1</td>
      <td>N/A</td>
    </tr>
    <tr>
      <td></td>
      <td>size</td>
      <td>integer</td>
      <td>The page size</td>
      <td>10</td>
      <td>50</td>
    </tr>
  </tbody>
</table>

<h4>Answers</h4>
<table><thead><tr><th>Código</th><th>Description</th><th>Schema</th></tr></thead><tbody><tr><td>200</td><td>OK</td><td>array of car objects <a href="#car" target="_new">Car</a></td></tr><tr><td>401</td><td>Unauthorized</td><td></td></tr><tr><td>500</td><td>Internal Server Error</td><td></td></tr></tbody></table>

<h3>Create a car</h3>
<p><code>POST /cars</code></p>
<p>Create a new car with the information provided in the request body.</p>
<h4>Request body</h4>
<p><a href="#car" target="_new">Car</a> object</p>
<h4>Answers</h4>

<table><thead><tr><th>Código</th><th>Description</th><th>Schema</th></tr></thead><tbody><tr><td>201</td><td>Car created successfully</td><td><a>Car</a></td></tr><tr><td>400</td><td>Invalid request</td><td></td></tr><tr><td>401</td><td>Unauthorized</td><td></td></tr><tr><td>500</td><td>Internal Server Error</td><td></td></tr></tbody></table>

<h3>Update a car by ID</h3>
<p><code>PUT /cars/{id}</code></p>
<p>Updates information for a specific car with the given ID. The information to be updated must be provided in the body of the request.</p>

<h4>Parameters</h4>
<table><thead><tr><th>Name</th><th>Type</th><th>Description</th></tr></thead><tbody><tr></tr><tr><td>id</td><td>integer</td> <td>the ID of the car to update.</td></tr></tbody></table>

<h4>Answers</h4>
<table><thead><tr><th>Code</th><th>Description</th><th>Schema</th></tr></thead><tbody><tr><td> 200</td><td>OK</td><td><a href="#car" target="_new">Car</a></td></tr><tr><td>400 </td><td>Invalid request</td><td></td></tr><tr><td>401</td><td>Unauthorized</td><td></td </tr><tr><td>404</td><td>Car Not Found</td><td></td></tr><tr><td>500</td><td >Internal Server Error</td><td></td></tr></tbody></table>

<h3>Delete a car by ID</h3>
<p><code>DELETE /cars/{id}</code></p>
<p>Deletes a specific car with the given ID.</p>
<h4>Parameters</h4>
<p>| Name |</p>


