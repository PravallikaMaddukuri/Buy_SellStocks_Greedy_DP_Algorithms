JCC = javac

JFLAGS = -g

default: jar

jar:Stocks.class
	@echo "Manifest-Version: 1.0" >> manifest.mf
	@echo "Main-Class: Stocks" >> manifest.mf
	@echo "" >> manifest.mf
	jar cmf manifest.mf Stocks.jar *.class *.java
	@echo "#!/usr/bin/java -jar" > Stocks
	cat Stocks.jar >> Stocks
	chmod +x Stocks

Stocks.class: Stocks.java
	$(JCC) $(JFLAGS) *.java

$(info run command 'java -jar Stocks programName' programName can be any of [1,2,3a,3b,4,5,6a,6b])

clean:
	rm -f *.class
	rm -rf manifest.mf
	rm -rf *.jar