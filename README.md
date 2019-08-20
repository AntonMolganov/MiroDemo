Тестовое задание 
Mini Miro
Приложение представляет собой REST API, которое позволяет работать с виджетами. Виджеты имеют позицию(x и y), ширину, высоту, z-index(чем выше значение, тем выше лежит виджет), дату последней модификации и уникальный идентификатор. С помощью REST API запросов мы можем добавлять, удалять и менять виджеты. Также мы можем получить все виджеты, либо какой-то один виджет.
Список запросов:
— Создание виджета. При создании виджета мы должны указать его координаты, ширину и высоту. Если мы не указываем z-index, то виджет перемещается на передний план. Если указываем существующий z-index, то новый виджет сдвигает все виджеты с таким же или большим индексом в большую сторону. На выходе мы должны получить полное представление виджета, вместе со сгенерированным уникальным идентификатором.
— Получение виджета. Указав идентификатор виджета, мы можем получить его полное представление.
— Изменение виджета. Мы должны иметь возможность изменить все данные виджета, кроме его идентификатора. Важно чтобы все изменения над виджетом происходили синхронно. То есть, если мы меняем местоположение виджета с координат 50;50 на 100;100, мы не должны случайно получить координаты 50;100, если отправили запрос на чтение виджета.
— Получение списка виджетов. По умолчанию мы выдаем все виджеты отсортированные по индексу, от меньшего к большему.
— Удаление виджета. Мы можем удалить виджет по его идентификатору.
Требования:
Реализация должна быть основана Spring.


Данные должны храниться только в оперативной памяти. Запрещается использовать любые хранилища и базы данных.


Покрытие кода unit тестами должно быть не меньше 30%.


В качестве сборщика нужно использовать Maven.


Исходники предоставить в git репозитории.


Усложнение 1:
В случае, если мы добавили на доску много виджетов, нам нужно брать их по чуть-чуть, чтобы доска загружалась постепенно. Для этого на сервере нужно реализовать механизм пагинации, который по умолчанию будет выдавать 10 элементов, но с возможность указать другое количество(до 500).
Усложнение 2:
При получении виджетов, мы можем указать область на доске, из которой эти виджеты будут браться. Например, у нас есть 3 виджета, которые имеют ширину и высоту равные 100. Эти виджеты лежат в точках 50;50, 50;100 и 100;100. Мы хотим взять только виджеты, которые находятся внутри прямоугольника, левая верхняя точка которого находится в координате 0;0, а правая нижняя в 100;150. В таком случае мы должны получить первый и второй виджет.

Усложнения ты можешь делать или не делать, на свой выбор. 
 
Удачи!
