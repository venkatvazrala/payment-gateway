# payment-gateway

Requirements
For building and running the application you need:

JDK 1.8
Maven 3

Running the application locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the com.payment.PaymentGatewayApplication class from your IDE.

Post Method

localhost:8080/api/v1/payment/validate

Sample Json:

{
	"amount": 1299,
	"currency": "EUR",
	"cardHolder": {
		"name": "First Last",
		"email": "abc@gmail.com"
	},
	"card": {
		"pan": "12345678903555",
		"expiry": "0624",
		"cvv": "789"
	}
}


Response:-

![image](https://user-images.githubusercontent.com/60241157/129901086-ff9a031a-f0ec-4106-89e4-238198a61c68.png)




Get Method

localhost:8080/api/v1/payment/transaction/1

Response:-
![image](https://user-images.githubusercontent.com/60241157/129901190-fe732bc5-84ab-4964-8eee-05ae2b052b5c.png)


H2 Database:-

![image](https://user-images.githubusercontent.com/60241157/129898945-ade1a8bd-4aee-427e-a028-803039e7d532.png)


![image](https://user-images.githubusercontent.com/60241157/129899093-5461dba0-32ea-4418-9a1f-4a3b247a658b.png)


