# Низкоуровневая работа с веб
### Цель работы
Освоить основные навыки обращения c Web из программы на Python, средства парсинга веб-страниц, соответствующие библиотеки.

![image](https://user-images.githubusercontent.com/70855182/141493373-6170d138-6548-4593-a780-c50d839e1f7a.png)

![image](https://user-images.githubusercontent.com/70855182/141493445-9c043f68-3dcd-4893-bb0e-0ffb0c8a8b39.png)


### Дополнительные задания
1. При ответе вашего сервера посылайте некоторые основные заголовки:
    1. Date
    2. Content-type
    3. Server
    4. Content-length
    5. Connection: close.

![image](https://user-images.githubusercontent.com/70855182/141493911-9d4d7ee2-6777-4ae2-8adc-400fcbc923a7.png)

2. Создайте файл настроек вашего веб-сервера, в котором можно задать прослушиваемый порт, рабочую директорию, максимальный объем запроса в байтах. Можете добавить собственные настройки по желанию.

![image](https://user-images.githubusercontent.com/70855182/141493983-4cec0991-0c6e-4fee-a755-bfad4a4c7616.png)

![image](https://user-images.githubusercontent.com/70855182/141496584-1a9dfeb8-2745-41f5-83c9-d09bedc764ec.png)

3. Если файл не найден, сервер передает в сокет специальный код ошибки - 404.

![image](https://user-images.githubusercontent.com/70855182/141496899-381690c1-8700-48de-b895-4c79b6057a36.png)

![image](https://user-images.githubusercontent.com/70855182/141497109-4fcbfdcc-04c3-4214-87c9-9e3e0fddf034.png)

![image](https://user-images.githubusercontent.com/70855182/141497131-e5df4934-1ab8-4b6e-ac3c-264563913d14.png)

4. Сервер должен работать в многопоточном режиме.

![image](https://user-images.githubusercontent.com/70855182/141496227-13f09709-fe01-4d8b-a9a2-6da828b8914e.png)

5. Сервер должен вести логи в следующем формате: Дата запроса. IP-адрес клиента, имя запрошенного файла, код ошибки.

![image](https://user-images.githubusercontent.com/70855182/141497253-34d542c8-9c5e-4656-a7b4-e55a47471dbf.png)

6. Добавьте возможность запрашивать только определенные типы файлов (.html, .css, .js и так далее). При запросе неразрешенного типа, верните ошибку 403.
Попробуем открыть картинку формата jpg

![image](https://user-images.githubusercontent.com/70855182/141497443-31addaa2-845d-445c-a207-e8239199df9b.png)

![image](https://user-images.githubusercontent.com/70855182/141497471-b8dbe9f4-2b18-467f-92bb-376850396139.png)

![image](https://user-images.githubusercontent.com/70855182/141497496-c123da65-f18e-48e0-b660-cabd215e27c3.png)

Помимо .html, .css, .js, можно запрашивать, например: 
png 

![image](https://user-images.githubusercontent.com/70855182/141498917-b3c96cfb-1b61-48ea-bf9e-806a0d432dcf.png)

gif

![image](https://user-images.githubusercontent.com/70855182/141498972-7046a607-a633-4061-a76c-28b9f7d24b7a.png)

7. Реализуйте поддержку постоянного соединения с несколькими запросами.

![image](https://user-images.githubusercontent.com/70855182/141492895-aebe18b2-04af-48a1-b8f9-5a02a33c01bd.png)

8. Реализуйте поддержку бинарных типов данных, в частночти, картинок.

![image](https://user-images.githubusercontent.com/70855182/141497904-79600d1b-b828-4ca9-8e8a-5a07d74f1f00.png)
