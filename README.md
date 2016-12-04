# Simpl Service

Provides a client API and other resources to support simpl.press frontend

----


From the root directory of this codebase:

1. Run `mvn clean compile` to compile the application.
1. Run `mvn exec:java` to start the server. The server will use the `config/local.yml` configuration file.
1. Visit URL `http://localhost:9000` in your browser for the service homepage.
 
Alternatively:

1. Run `mvn clean install` to build your application.
1. Start application with `java -jar target/checkout-service-X-Y.jar server config/local.yml`.
1. Visit URL `http://localhost:9000` in your browser for the service homepage.

Within your IDE:

1. See `Build` section below for notes on required annotation processing when running the service within an IDE. 
1. Run the service as a regular Java application: the main class is
`SimplPressServiceApplication`. Provide the arguments `server config/local.yml`
(or specify another configuration file).

The port the service runs on is configured within the configuration file passed to the application. 
##
#### Intellij IDEA

To enable annotation processing (correct for version 15):

1. Open preferences dialog.
1. Navigate to `Build, Execution, Deployment` section.
1. Navigate to `Compiler` subsection.
1. Navigate to `Annotation Processors` subsection.
1. Tick `Enable annotation processing`.
1. Make sure `Obtain processors from classpath` is selected.

Optional Dagger support:

1. Plugin: https://github.com/square/dagger-intellij-plugin
