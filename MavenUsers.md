To import surface into your Maven project, you have to add the repository:

```
<repository>
	<id>axeiya-snapshot</id>
	<url>http://nexus.axeiya.com/nexus/content/repositories/snapshots</url>
</repository>
```

and declare the dependency (currently a SNAPSHOT dependency) :

```
<dependency>
	<groupId>com.axeiya.stillcollab</groupId>
	<artifactId>surface</artifactId>
	<version>0.1-SNAPSHOT</version>
</dependency>
```