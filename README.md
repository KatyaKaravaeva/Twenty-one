# twenty-one

> Данная программа представляет многопоточное приложение, которое имитирует игру в "21".


Правила:
На столе бесконечная случайно перемешанная колода карт.
Карты представляют собой целые числа в диапазоне от 1 до 10 (включительно). В
начальный момент времени у игроков нулевой баланс. Игроки (в программе - threads)
пытаются взять карту из колоды параллельно и положить ее к себе в стопку,
распихивая всех локтями. Взятие карты игроком приводит к увеличению баланса
этого игрока на значение этой карты. Посл взятия карты игроки отдыхают
(спят случайное время от 100 до 200 мс). В один момент времени только один игрок
может взять карту, а остальные отдыхают. Еще есть шулеры - это нечестные игроки.
Они вместо того, чтобы выполнить свой ход, могут воровать очки с баланса честных игроков
(шулера всегда играют в команде) и записывать эти очки на свой баланс.
Они выбирают такое действие с вероятностью 0.4 (40%) после отдыха. Но это действие намного сложнее,
чем просто взять карту из колоды (и растолкать других игроков локтями), поэтому им требуется больше времени
на отдых после этого (и они после этого спят случайное время от 180 до 300 мс).
Шулер может своровать в каждой попытке от 0 до 8 очков.
В конце игры пользователю выводятся количество очков у каждого игрока, а в конце пишется победитель(игрок,
который набрал самое большое количество баллов)!

Ввод данных:
В начале игры пользователю предлагается ввести количество честных игроков, а затем количество шулеров.
Всего игроков в игре не может превышать 100, а если совсем нет шулеров, то 99.
Все данные, которые ввел пользователь проверяются на корректность, в случае некорретности пользователю ввыводится
сообщение об это и запрашивается ввод заново.
