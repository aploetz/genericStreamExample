# genericStreamExample
Consumer and Producer static/void/mains for Apache Pulsar, using a Generic schema approach.

The pom.xml is set up to recognize `AutoConsumeSchemaConsumerExample.java` as the `main`.
Ensure that Apache Pulsar is running locally.  Topics will be created at runtime, if they do not exist.
This example will require you to manually produce messages which match a "payment" schema.

Running the AutoConsumeSchemaConsumerExample from _outside_ an IDE requires the following steps:

```
% mvn clean install
```

This should compile the Java classes _and_ build the `pulsarstuff-0.0.1-SNAPSHOT-jar-with-dependencies.jar` file.
Note that Maven will put the compiled jar into the `target/` directory.  Running the jar should produce output similar to this:

```
% java -jar target/pulsarstuff-0.0.1-SNAPSHOT-jar-with-dependencies.jar

SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
Jul 29, 2022 8:58:55 AM org.apache.pulsar.shade.io.netty.resolver.dns.DnsServerAddressStreamProviders <clinit>
WARNING: Can not find org.apache.pulsar.shade.io.netty.resolver.dns.macos.MacOSDnsServerAddressStreamProvider in the classpath, fallback to system defaults. This may result in incorrect DNS resolutions on MacOS.
Jul 29, 2022 8:58:55 AM org.apache.pulsar.shade.io.netty.resolver.dns.DefaultDnsServerAddressStreamProvider <clinit>
WARNING: Default DNS servers: [/2001:4860:4860:0:0:0:0:8888:53, /2001:4860:4860:0:0:0:0:8844:53] (Google Public DNS as a fallback)
key = , value = {"order_code": "", "email_id": %s, "platform": %s, "phone": %s, "state": %s, "products": %d , "username": %s}"
key = id-0, value = {"order_code": ...
```
