� ����� db.properties ������� username, password, databaseName
������� ���������� mvn clean install � ������ ������ � ������ ��� ������ ��������� ���������.
���������� ����������� �� 8080 �����.
�������:
1) /rest/record?user={userId}&path={pathOfPage} - �������� ������� ��������� ����� �������������.
userId(Long) - ������������� ������������, path(String
) - ���� ��������
��������, http://localhost:8080/rest/record?user=3&path=testPath
2) /rest/statistic?from={from}&to={to} - ��������� ���������� ��������� �� ������������ ������. 
from � to - ���� � ������� dd.MM.yyyy
��������� 
����� ��� ������� ���, ��� � ������� �� ������ ��� �� ������.
��������,
http://localhost:8080/rest/statistic
http://localhost:8080/rest/statistic?from=02.03.2018
http://localhost:8080/rest/statistic?to=01.03.2018
http://localhost:8080/rest/statistic?from=02.03.2018&to=02.03.2018