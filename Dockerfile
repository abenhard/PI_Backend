FROM openjdk:17
ADD target/PO2_Trabalho-0.0.1-SNAPSHOT.jar PO2_Trabalho.jar
ENTRYPOINT ["java", "-jar", "PO2_Trabalho.jar"]

EXPOSE 8080
