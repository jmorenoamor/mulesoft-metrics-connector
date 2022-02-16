test:
	@mvn test

package:
	@mvn clean package

install:
	@mvn clean install -DskipTests
