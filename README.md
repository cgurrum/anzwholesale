# Getting Started

### Requirements
* [JDK 8+](https://www.oracle.com/au/java/technologies/javase/javase-jdk8-downloads.html)

### APIs
Following are the APIs available (Refer [OpenAPI definitions](swagger/wholesale-apis.yaml) for more information).
1) **/authenticate**
    * This API is used to get the customer authentication token. This API is public. To get the token, pass the customerId & password. If the credentials are valid, this returns a token that contains the customer id. 
2) **/accounts**
    * This API is used to get the customer accounts. This API is protected and requires valid token (generated by /authenticate API) with scope: SCP_ACCOUNTS. **Customer Id is retrieved from the token**. If the token is invalid or doesn't contain the right scope, then the request will be denied.
3) **/account/{accountNumber}/transactions**
    * This API is used to get the transactions on the customer's account. This API is protected and requires valid token (generated by /authenticate API) with scope: SCP_ACCOUNTS. **Customer Id is retrieved from the token and Account Number from the URL Path**. If the token is invalid or doesn't contain the right scope, then the request will be denied.

### Running the application
Use any of the following to run the application.
*   ```sh
    gradlew bootRun --args='--jasypt.encryptor.password=passwordkey'
    ```
*   ```sh
    java -Djasypt.encryptor.password=passwordkey -jar dist/wholesale-0.0.1-SNAPSHOT.jar
    ```



#### CURL commands to invoke APIs
1) Get JWT Token for the customer.
     ```sh
     curl --location --request POST 'http://localhost:8080/authenticate' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "customerId": "customer1",
        "password": "****"
    }'
    ```
2) Get Customer Accounts using the token.
    ```sh
    curl --location --request GET 'http://localhost:8080/accounts' \
    --header 'Authorization: Bearer <token>'
    ```
3) Get Account Transactions using the token and account number.
    ```sh
    curl --location --request GET \
    'http://localhost:8080/accounts/<accountNumber>/transactions' \
    --header 'Authorization: Bearer <token>'
    ```

### Test Data (Refer [DB Script](src/main/resources/data.sql) for full details)
| Customer Id | Password | Scopes |
| ------ | ------ | ------ |
| customer1 | password1 | SCP_ACCOUNTS & SCP_DUMMY |
| customer2 | password2 | SCP_ACCOUNTS |
| customer3 | password3 | SCP_DUMMY |

### Notes
* DB Password: **testpassword**
* JWT Signing Key: **testjwtsecret**
* Above secrets in the property files are encrypted using Jasypt encryptor password: **passwordkey**

