# Adapter For External Service Using Xml

### Description of Task

######  The task is to create an adapter for the existing banking service of customer quality assessment (QQAS). Input data data contains personal information about
customer (id, social network info, passport and living address).
Need to create such service (s), that would:
     
     1. Send file with customer data to QQAS.
     2. Get and save file, received as response from QQAS.
     3. Save response info into tables:
            REQUESTS: columns - CLIENTID, SERVICETYPE, DATE; 
            RESPONSES: columns - ID, CLIENTID, RATING, PROBABILITY, ERROR.
     4. Use logger to log errors and information about requests / responses.

###### Using technologies: Java / PostgreSQL / JAXB / JDBC / log4j. 

 ### QQAS API    

###### Request example (OK):
     <XmlRequest> 
        <customer>
            <person_id>12345</person_id>
            <social_id>123467890</social_id>
            <network_id>3</network_id>
            <email>qwerty@qwerty.ru</email>
            <surname>Butova</surname>
            <name>Luydmila</name>
            <fatherName>Shamilevna</fatherName>
            <birthdate>19991130</birthdate>
            <inn>36664564567</inn>
        </customer>
        <passport>
            <serial>1234</serial>
            <number>123456</number>
            <issueDate>2171130</issueDate>
          </passport>
        <address>
          <region>Moscow region</region>
          <city>Moscow</region>
          <street>Kutuzovskiy prospect</street>
          <house>32/1</house>
          <flat>110</flat>
        </address>
     </XmlResponse>  
     
###### Response example (OK):
     <XmlResponse> 
        <id>12345</id>
        <result>154</result>
        <probability>7.6</probability>
        <error>0</error>
     </XmlResponse>  
     
###### Response example (customer not found / ERROR):
     <XmlResponse> 
       <id>123456</id>
       <result>0</result>
       <probability>0</probability>
       <error>1</error>
     </XmlResponse>
     
     
 ### Application Launch
      1. Set database parameters in JDBC.
      2. Set logger parameters in log4j.properties.
      3. Launch main from Execute.class.