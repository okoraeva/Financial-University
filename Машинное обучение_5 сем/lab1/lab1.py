import numpy as np
import pandas as pd
from matplotlib import pyplot as plt
from matplotlib import colors
from matplotlib.ticker import PercentFormatter
import seaborn as sns

''' 1. С помощью массивов numpy создайте таблицу умножения. '''

# # создаем матрицу результата перемножения
# result_matrix = np.zeros((10, 10))
#
# # заполняем матрицу
# for i in range(1, 11):
#     for j in range(1, 11):
#         result_matrix[i - 1][j - 1] = i * j
#
# print(result_matrix)

''' 2. Создайте функцию, которая принимает как аргументы целое число N и первый элемент (вещественное число),
и разность (вещественное число) и создает матрицу numpy по диагонали, которой располагаются первые N членов
арифметической прогрессии. '''

# def matrix (n, first_elem, d):
#     return np.diag(np.arange(first_elem, first_elem + d*n, d))
#
# print(matrix(3, 1.5, 0.5))

''' 3. Сгенерируйте средствами numpy матрицу А 5 на 5, содержащую последовательные числа от 1 до 25. Используя 
срезы извлеките в плоский массив все нечетные элементы этой матрицы. '''

# arr = np.arange(1, 26).reshape(5, 5)
# print(arr)
# print(arr[arr % 2 == 1])

''' 4. Создайте двумерный массив, содержащий единицы на границе и нули внутри. '''

# arr = np.ones((4, 4))
# arr[1:-1, 1:-1] = 0
# print(arr)

''' 5. Создайте две матрицы размером (5,5). Одна матрица содержит 5 в шахматном порядке как в задаче домашнего задания, 
другая имеет треугольную форму содержащую 5 на основной диагонали и в позициях выше ее, а ниже все 0. 
Посчитайте их детерминант и найдите обратные матрицы.'''

# matrix1 = np.zeros((5, 5))
# matrix1[::2, ::2] = 5
# matrix1[1::2, 1::2] = 5
# matrix2 = np.tri(5, 5, 0).T * 5
# print(matrix1)
# print(matrix2)
# print(np.linalg.det(matrix1))
# #print(np.linalg.inv(matrix1))
# print(np.linalg.det(matrix2))
# print(np.linalg.inv(matrix2))

''' 6. С помощью pandas загрузите датасет для предсказания цены квартиры, прилагающийся к этой работе. '''

dataset = pd.read_csv(r"data.csv")
print(dataset)

''' 7. Выведите на экран несколько первых и несколько последний строк файла. '''

# print(dataset.head(5))
# print(dataset.tail(5))

''' 8. Выведите с помощью методов pandas основные количественные параметры датасета: количество строк и столбцов, 
тип данных каждого поля, количество значений в каждом столбце, шкала измерения каждого численного поля.'''

# print(dataset.info())

''' 9. Удалите из таблицы столбцы, содержащие идентификаторы, переименуйте все оставшиеся названия колонок 
на русском языке.'''

# dataset.drop(dataset.columns[[9, 10, 11, 12, 13, 14, 15, 16, 17, 18]], axis=1, inplace=True)
# dataset.rename(columns={'Id': 'Номер', 'DistrictId': 'Район', 'Rooms': 'Кол-во комнат', 'Square': 'Площадь',
#                         'LifeSquare': 'Жилая площадь', 'KitchenSquare' : 'Кухонная площадь', 'Floor': 'Этаж',
#                         'HouseFloor': 'Жилой этаж', 'HouseYear': 'Год дома', 'Price': 'Цена'}, inplace=True)
# print(dataset)

''' 10. Выведите отдельно столбец, содержащий цену, по номеру и названию. Выведите первую, десятую и предпоследнюю 
строку таблицы по номеру и по индексу.'''

# print(dataset["Цена"])
# print(dataset.iloc[[0, 9, -2]])
# print(dataset.sort_values("Номер").iloc[[0, 9, -2]])

''' 11. Выделите в отдельную таблицу последние десять строк. Уберите в ней столбец с ценой. Склейте ее с первоначальной
 таблицей при помощи append. Заполните отсутствующие значения цены средним по таблице.'''

# dataset_1 = dataset.tail(10).copy()
# dataset_1.drop(['Цена'], axis=1, inplace=True)
# dataset_new = dataset.append(dataset_1, ignore_index=False, verify_integrity=False, sort=None)
# dataset_new = dataset_new['Цена'].fillna(dataset_new['Цена'].mean())
# print(dataset_new)

''' 12. Выделите пять последних колонок в отдельную таблицу. Удалите в ней строки, в которых цена ниже среднего. 
Присоедините эту таблицу к изначальной (выберите самый подходящий тип соединения).'''

# dataset_2 = dataset[dataset.columns[-5:]]
# dataset_2 = dataset_2[dataset_2["Цена"] >= dataset_2["Цена"].mean()]
# dataset_new = dataset.append(dataset_2, ignore_index=False, verify_integrity=False, sort=None)
# print(dataset_new)

''' 13. Выведите таблицу, содержащую среднюю цену и количество квартир на каждом этаже из первоначального набора данных.'''

# dataset_3 = dataset.groupby("Этаж").agg({'Цена': 'mean', 'Кол-во комнат': 'sum'})
# print(dataset_3)

''' 14. Сохраните получившуюся таблицу в файлы формата csv и xlsx. Прочитайте их и убедитесь, что данные отображаются корректно.'''

# dataset_3.to_csv("floorData.csv")
# print(pd.read_csv("floorData.csv", index_col=0))

# dataset_3.to_excel("floorData.xlsx")
# print(pd.read_excel('floorData.xlsx', index_col=0))

''' 15. Создайте в Excel или другом табличном редакторе таблицу, содержащую несколько численных и текстовых полей. 
Прочитайте ее в программу при помощи pandas.'''

# print(pd.read_excel('excelData.xlsx', index_col=0))

''' 16-17. Дальнейшие задания производите используя изначальную версию датасета. Должны быть подписаны названия 
графиков, названия осей, указаны значения на осях. Оцениваться будет использование количества различных атрибутов при 
построении графиков и визуальная красота.
Постройте круговую диаграмму для признака Rooms, иллюстрирующую количество квартир в процентах в зависимости от 
количества комнат. Сделайте сектор с наибольшим числом квартир выдвинутым.'''

# dataset['Rooms'].unique()
# n1 = len(dataset[dataset['Rooms'] == 3])
# n2 = len(dataset[dataset['Rooms'] == 1])
# n3 = len(dataset[dataset['Rooms'] == 2])
# n4 = len(dataset[dataset['Rooms'] == 4])
# n5 = len(dataset[dataset['Rooms'] == 5])
# n6 = len(dataset[dataset['Rooms'] == 10])
# n7 = len(dataset[dataset['Rooms'] == 19])
# n8 = len(dataset[dataset['Rooms'] == 6])
# n9 = len(dataset[dataset['Rooms'] == 0])
#
# values = [n1, n2, n3, n4, n5, n6, n7, n8, n9]
# count_rooms = [3, 1, 2, 4, 5, 10, 19, 6, 0]
#
# wp = {'linewidth': 1, 'edgecolor': "black"}
# explode = (0.0, 0.0, 0.3, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
#
#
# def func(pct, allvalues):
#     absolute = int(pct / 100. * np.sum(allvalues))
#     return "{:.1f}%\n({:d} g)".format(pct, absolute)
#
#
# fig, ax = plt.subplots(figsize=(10, 7))
# wedges, texts, autotexts = ax.pie(values,
#                                   autopct=lambda pct: func(pct, values),
#                                   labels=count_rooms,
#                                   explode=explode,
#                                   shadow=True,
#                                   startangle=90,
#                                   wedgeprops=wp)
#
# ax.legend(wedges, values,
#           title="Квартиры",
#           loc="center left",
#           bbox_to_anchor=(1.2, 0, 0.5, 1))
#
# plt.setp(autotexts, size=8, weight="bold")
# ax.set_title("Rooms")
#
# plt.show()

''' 18. Постройте гистограмму по целевой переменной Price. Оцените визуально, по какой цене продаётся наибольшее 
количество квартир.'''

# fig, ax = plt.subplots(figsize=(10, 7))
# ax.hist(dataset['Price'])
#
# ax.grid(visible=True, color='grey', linestyle='-.', linewidth=0.5, alpha=0.5)
#
# plt.hist(dataset['Price'], color="lightblue", ec="blue")
#
# plt.xlabel("Price")
# plt.ylabel("Quantity")
# plt.title('Price statistics')
# plt.show()

''' 19. Постройте диаграммы рассеяния для признаков Rooms, Square, HouseFloor, HouseYear в зависимости от целевой
 переменной Price в одной области figure. Оцените визуально, есть ли среди них такие, на которых разброс точек близок 
 к линейной функции.'''

## Rooms
# fig, ax = plt.subplots(figsize=(10, 6))
#
# ax.scatter(x=dataset['Rooms'], y=dataset['Price'], c='blue', s=10, alpha=0.5)
# plt.xlabel("Rooms")
# plt.ylabel("Price")
#
# ax.grid(visible=True, color='grey', linestyle='-.', linewidth=0.5, alpha=0.5)
#
# plt.show()

## Square
# fig, ax = plt.subplots(figsize=(10, 6))
#
# ax.scatter(x=dataset['Square'], y=dataset['Price'], c='blue', s=10, alpha=0.5)
# plt.xlabel("Square")
# plt.ylabel("Price")
#
# ax.grid(visible=True, color='grey', linestyle='-.', linewidth=0.5, alpha=0.5)
#
# plt.show()

## HouseFloor
# fig, ax = plt.subplots(figsize=(10, 6))
#
# ax.scatter(x=dataset['HouseFloor'], y=dataset['Price'], c='blue', s=10, alpha=0.5)
# plt.xlabel("HouseFloor")
# plt.ylabel("Price")
#
# ax.grid(visible=True, color='grey', linestyle='-.', linewidth=0.5, alpha=0.5)
#
# plt.show()

## HouseYear
# fig, ax = plt.subplots(figsize=(10, 6))
#
# ax.scatter(x=dataset['HouseYear'], y=dataset['Price'], c='blue', s=10, alpha=0.5)
# plt.xlabel("HouseYear")
# plt.ylabel("Price")
#
# ax.grid(visible=True, color='grey', linestyle='-.', linewidth=0.5, alpha=0.5)
#
# plt.show()

''' 20. Постройте ядерную оценку плотности целевой переменной Price. Оцените визуально, напоминает ли полученный график
 нормальное распределение. Постройте двумерную ядерную оценку плотности для целевой переменной Price и признака
  HouseFloor, затем оцените визуально на каких этажах и по какой цене продаётся основная масса квартир.'''

# # Целевая переменная Price
# sns.displot(dataset['Price'], kind="kde", color='blue')
#
# plt.grid(visible=True, color='grey', linestyle='-.', linewidth=0.5, alpha=0.5)
# plt.show()
#
#
# # Целевая переменная Price и признак HouseFloor
# sns.displot(dataset['Price'], x=dataset['Price'], y=dataset['HouseFloor'], kind="kde", color='blue', fill=True)
#
# plt.grid(visible=True, color='grey', linestyle='-.', linewidth=0.5, alpha=0.5)

''' 21. Постройте ящиковую диаграмму признака Square. Оцените визуально имеются ли выбросы, и, если да, то начиная 
с какого размера площади значение признака можно считать выбросом.'''

# sns.boxplot(dataset.Square)
# plt.show()

''' 22. При помощи сетки графиков PairGrid визуализируйте попарные отношения признаков Rooms, Square, HouseFloor, 
HouseYear, Price следующим образом: на диагонали - гистограммы, под диагональю - ядерные оценки плотности, 
над диагональю - диаграммы рассеяния. По результатам визуализации сделайте выводы.'''

# dataset_new = dataset.sample(100)
# g = sns.PairGrid(dataset_new[['Rooms', 'Square', 'HouseFloor', 'HouseYear', 'Price']])
# g.map_upper(sns.scatterplot, color="pink")
# g.map_lower(sns.kdeplot, color="lightblue")
# g.map_diag(sns.histplot, color="purple")
# plt.show()

''' 23. Постройте тепловую карту матрицы корреляции (df.corr()) признаков Rooms, Square, HouseFloor, HouseYear, Price. 
По ней определите, какие признаки являются зависимыми (у таких признаков коэффициент корреляции близок к единице).'''

dataset_new = dataset.sample(100)
df = pd.DataFrame(dataset_new[['Rooms', 'Square', 'HouseFloor', 'HouseYear', 'Price']])

sns.heatmap(df.corr(), vmin=-1, vmax=+1, annot=True, cmap='coolwarm')
plt.show()