### Дипломный проект по профессии «Тестировщик»

В рамках дипломного проекта необходимо автоматизировать тестирование комплексного сервиса покупки тура, взаимодействующего с СУБД и API Банка. 
Для покупки тура есть два способа: с помощью карты и в кредит. 
В приложении используются два отдельных сервиса оплаты: Payment Gate и Credit Gate.

[Ссылка на дипломное задание](https://github.com/netology-code/qa-diploma/tree/master?tab=readme-ov-file)


### Начало работы

**Для работы проекта требуется предварительная установка следующих программ:**

- Браузер - (Google Chrome)
- GitHub
- IntelliJ IDEA
- Docker Desktop
- DBeaver 


**Инструкция для запуска автотестов**


1. Склонировать проект из репозитория командой git clone
2. Открыть склонированный проект в Intellij IDEA
3. Запустить Docker Desktop командой в терминале IntelliJ IDEA docker-compose up


**Подключение SUT к MySQL**

- Запустить jar-файл с базой данных MySQL используя команду: java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar;
- Проверить запуск приложения в браузере Chrome:http://localhost:8080/
- В новой вкладке терминала запустить тесты используя команду: ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app";
- Создать отчёт Allure и открыть в браузере:./gradlew allureServe.

**Подключение SUT к PostgreSQL**

- В новой вкладке терминала запустить тестируемое приложение командой: java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar;
- Проверить запуск приложение в браузере Chrome:http://localhost:8080/
- В новой вкладке терминала запустить тесты командой: ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app";
- Создать отчёт Allure и открыть в браузере:./gradlew allureServe.


### Завершение работы

- Закрыть отчёт и остановить приложение: CTRL + C
- Остановить контейнеры: docker-compose down   
