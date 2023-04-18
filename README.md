Из директории fmcg-project/core выполнить сборку проекта:

mvn clean package

Запустить приложение из директории fmcg-project/core/target:

java -jar fmcg-core-1.0.0-SNAPSHOT.jar

База данных будет доступна по адресу http://localhost:8080/h2-console

Данные для заполнения:

JDBC URL: jdbc:h2:mem:dev_db

User Name: sa

Password: password

