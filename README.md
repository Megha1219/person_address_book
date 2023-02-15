# AddressBook


API's
	
	POST   - http://localhost:8080/person
			Request Body
			 {
    				"firstName": "Megha S",
   				  "lastName": "Rampalli",
    				"phone": "3435345345",
    				"adderss": "UK"
  			 }
	GET    - http://localhost:8080/person
	PUT    - http://localhost:8080/person
			Request Body
			 {
				    "person_id": "e1162205-a870-4c9c-9c3a-a26747c82da3",
    				"firstName": "Megha S",
   				  "lastName": "Rampalli",
    				"phone": "3435345345",
    				"adderss": "UK"
  			 }
	DELETE - http://localhost:8080/person/e1162205-a870-4c9c-9c3a-a26747c82da3

------------------------------------------------------------------------------------------------
	Admin can perform all CRUD operations.
	Basic Authentication

	Username: Admin
	Password: Admin
	
	Authorization for browser
	base64 encode for Admin:Admin -> QWRtaW46QWRtaW4=
------------------------------------------------------------------------------------------------
	User can perform only perform Read operation.
	Basic Authentication

	Username: User
	Password: User
	
	Authorization for browser
	base64 encode for User:User -> VXNlcjpVc2Vy
