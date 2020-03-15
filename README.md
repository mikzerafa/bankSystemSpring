# bankSystemSpring
Restful Bank system with embedded h2 database built in kotlin using spring

-ERRORS-
1)Error handling for API calls are not implemented
2)Swagger Yaml file is created detailing APIs but uncertain as to how to view Swagger-ui in browser
3)Withdrawals of quantities > than available funds are allowed
4)Beans are not correctly implemented.

Available API calls

Test Connection
eg: http://localhost:8080/api/account/test

Load available accounts
eg: http://localhost:8080/api/account/load

Create Account
key: beneficiaryName
key: pinNumber
eg: http://localhost:8080/api/account/create/?beneficiaryName="pingu"&pinNumber="0122"

View Account Data
eg: http://localhost:8080/api/account/data

Deposit
key: AccountNumber
key: amount
eg: http://localhost:8080/api/account/deposit?accountNumber=2&amount=3.10

Withdraw
key: accountNumber
key: amount
key: pinNumber
eg: http://localhost:8080/api/account/withdraw?accountNumber=2&amount=3.0&pinNumber="0122"

Transfer
key: fromAccount
key: toAccount
key: amount
key: pinNumber
eg: http://localhost:8080/api/account/transfer?fromAccount=2&toAccount=1&amount=2.2&pinNumber="0122"

Get Account Number
key: beneficiaryName
key: pinNumber
eg: http://localhost:8080/api/account/getAccountNumber?beneficiaryName="mikhail"&pinNumber="0123"
