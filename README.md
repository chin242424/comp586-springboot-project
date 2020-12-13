# comp586-springboot-project
REST implementation of COMP 586 project in Spring boot

This repository contains only rest side of the application

Authentication is provided by OKTA

The location of POJO or Model classes - comp586-springboot-project /bluemoon-rest-spring-master-1 /src /main /java /springframework/ bluemoonrestspring /model/

The location of Repository - comp586-springboot-project /bluemoon-rest-spring-master-1 /src /main /java /springframework/bluemoonrestspring /repository/

The location of Controllers - comp586-springboot-project /bluemoon-rest-spring-master-1 /src /main /java /springframework/bluemoonrestspring /rest/

The location of Services -
comp586-springboot-project /bluemoon-rest-spring-master-1 /src /mai /java /springframework /bluemoonrestspring /service/

Data configuration location - comp586-springboot-project /bluemoon-rest-spring-master-1 /src /main /resources/

Any other classes other than this are additional and were added to solve errors arising due to application run

Maven dependencies are added in pom.xml

Functioning:

The customer can only view the default page of the application.
Upon login the admin can 
1.Add a customer 
2.Then add an appliance to customer 
3.In doing so he can select multiple appliance types from a given list of appliances
4.Select a dropoff date for that appliance 
5.Possible Operations 
	List, Add and Edit Customers, 
	List, Add, Edit and Delete an Appliance
	List, Add, Edit and Delete a Drop 
	List static pages of Technicians, Appliance Types and Expertises 

Appliance can only be added when a customer is added and drop can only be added when an appliance is added. Please check model classes for relationships between them