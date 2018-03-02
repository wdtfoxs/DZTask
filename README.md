В файле db.properties указать username, password, databaseName 
Собрать приложение mvn clean install и кидаем варник в томкат или другой контейнер сервлетов. 
Приложение запускается на 8080 порту. 
Запросы: 
1) /rest/record?user={userId}&path={pathOfPage} - Создание события посещения сайта пользователем. 
userId(Long) - идентификатор пользователя, path(String) - путь страницы 
Например, 
http://localhost:8080/rest/record?user=3&path=testPath 
2) /rest/statistic?from={from}&to={to} - Получение статистики посещения за произвольный период. 
from(LocalDate) и to(LocalDate) - дата в формате dd.MM.yyyy 
Параметры можно как указать все, так и указать по одному или ни одного. 
Например, 
http://localhost:8080/rest/statistic 
http://localhost:8080/rest/statistic?from=02.03.2018 
http://localhost:8080/rest/statistic?to=01.03.2018 
http://localhost:8080/rest/statistic?from=02.03.2018&to=02.03.2018