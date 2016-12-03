# Simpl Service

Provides a client API and other resources to support simpl.press frontend

----

## How to run the Checkout Service application locally

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

----

## Test service

The Checkout Service can be run with a stubbed implementation that has no integrations with other internal systems.
This can be used for testing client applications.

The stubbed service is enabled by setting the configuration option `service.stubbed` in the server configuration
file (e.g. `config/local.yml`) to `yes` or `true`. Other options in the `service.stubData` configuration section can be
modified.

----

## Build

### Dependency Injection

Checkout Service uses *Dagger 2* for compile-time dependency injection. Application components are configured in the
`com.moo.ecommerce.checkout.checkoutservice.config` package. When the service is compiled, Dagger will process 
injection-related annotations and generate various classes. These classes are used to provide dependencies to the 
Jersey instance of the running application.

To support this within an IDE, you may need to enable annotation processing. You may also want to install plugins to
help navigate the dependency graph.

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
