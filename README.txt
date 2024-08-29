Выполнить из корня команду 
 docker-compose up -d
Для завершения 
 docker-compose down 

Открыть в браузере страницу по адресу http://localhost:8083/task3/ ,
все реализовано в html, все CRUD операции можно проверить в браузере

- Добавить достопримечательность
http://localhost:8083/task3/landmark/new   метод post

- Получить все достопримечательности (передать параметр для сортировки по наименованию достопримечательности,
параметр для фильтрации по типу достопримечательности)
/landmark/sort   метод get
http://localhost:8083/task3/landmark/sort?sort=sort // сортировка по возрастанию наименования достопримечательности
http://localhost:8083/task3/landmark/sort?filtr=palace // параметр для фильтрации по типу достопримечательности
http://localhost:8083/task3/landmark/sort?sort=sort&filtr=palace // сортировка по возрастанию наименования и фильтрация по типу достопримечательности

- Получить все достопримечательности конкретного населенного пункта
/landmark/city/{city}  get
http://localhost:8083/task3/landmark/city/Moskow

- Изменение данных по достопримечательности (возможно изменение только поля Краткое описание) 
/landmark/{id}/edit patch
http://localhost:8083/task3/landmark/1/edit

- Удаление достопримечательности из справочника 
/landmark/{id}  delete
(http://localhost:8083/task3/landmark нажать на любую из достопримечательностей, откроется страница,
где можно нажать "Удалить")


- Добавить город 
http://localhost:8083/task3/city/new  post

- Изменение данных по городу (возможно изменение только полей: Численность населения, наличие метро) 
http://localhost:8083/task3/city/{id}/edit  patch
http://localhost:8083/task3/city/1/edit
