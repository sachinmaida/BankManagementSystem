FROM openjdk:8
COPY ./target/BankManagementSystem.jar BankManagementSystem.jar
ENTRYPOINT ["java","-jar","BankManagementSystem.jar"]