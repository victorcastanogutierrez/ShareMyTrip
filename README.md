# ShareMyTrip

Social network to share trips between the users.

Application following MVC pattern. Persistence using HSQLDB, EJB project and WEB project which servers SOAP and RESTFUL web services. 
On the other hand, clients developed in Java (+Android) and C# consuming the web services and JMS (Java Message Service).


#sdi-310

Enterprise Application Project: sdi3-10.EJB and sdi3-10.WEB

#sdi3-10EJB

Business and persistence logic. 

#sdi3-10WEB

RESTFUL interfaces (resteasy), JavaServer Faces. As the front-end framework, Primefaces.

#sdi3-10.Cli.C#

Client developed in C#. It consumes the data from the server through SOAP (remote ejbs).

#sdi3-10.CliEJB

Client developed in Java. It access to the application logic through the EJBs.

#sdi3-10.CliMSG

Client developed in Java. It access to the application data through the JMS, also developed in the server.

#sdi3-10.CliREST

Client developed in Java. It access to the application data through the RESTFUL web service.

#sdi3-10.CliREST-ANDROID

Client developed in Android. It access to the application data through the RESTFUL web service.

#sdi3-10.Cli-SOAP

Client developed in Java. It access to the application data through the SOAP service (local ejbs).

#AlbUtil

Util classes for types treatment

#Authors

. Víctor Castaño Gutiérrez
. Adrián Jiménez Villareal
