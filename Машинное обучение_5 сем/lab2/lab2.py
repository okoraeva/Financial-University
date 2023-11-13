import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import re

''' 1. Загрузите данные по вариантам в ноутбук. '''


def excel_input(path):
    data = pd.read_excel(path)
    data.columns = data.iloc[1]
    data.drop(labels=[0, 1], axis=0, inplace=True)
    data = data.set_index("№")
    return data


data1 = excel_input(r'/home/olya/Рабочий стол/python/tasks5/lab2/data/Вариант 1.xlsx')
data2 = excel_input(r'/home/olya/Рабочий стол/python/tasks5/lab2/data/Вариант 2.xlsx')
data3 = excel_input(r'/home/olya/Рабочий стол/python/tasks5/lab2/data/Вариант 3.xlsx')
data4 = excel_input(r'/home/olya/Рабочий стол/python/tasks5/lab2/data/Вариант 4.xlsx')

''' 2. Сделайте описательную статистику полученных данных. '''

# print(data1)
# print(data2)
# print(data3)
# print(data4)

''' 3. Найдите процент учащихся, выполнивших работу ниже среднего. '''


def find_percentage_below_average(data):
    return len(data.loc[data['Первичный балл'] < data['Первичный балл'].mean()])*100/len(data)


percentage_below_avg_1 = find_percentage_below_average(data1)
percentage_below_avg_2 = find_percentage_below_average(data2)
percentage_below_avg_3 = find_percentage_below_average(data3)
percentage_below_avg_4 = find_percentage_below_average(data4)

# print(f'1 - {round(percentage_below_avg_1, 2)}%')
# print(f'2 - {round(percentage_below_avg_2, 2)}%')
# print(f'3 - {round(percentage_below_avg_3, 2)}%')
# print(f'4 - {round(percentage_below_avg_4, 2)}%')


''' 4. Найти процент учащихся не сдавших экзамен. '''


def find_percentage_failed(data):
    return len(data.loc[data['Балл'] < data['Минимальный балл']])*100/len(data)


percentage_failed_1 = find_percentage_failed(data1)
percentage_failed_2 = find_percentage_failed(data2)
percentage_failed_3 = find_percentage_failed(data3)
percentage_failed_4 = find_percentage_failed(data4)

# print(f'1 - {round(percentage_failed_1, 2)}%')
# print(f'2 - {round(percentage_failed_2, 2)}%')
# print(f'3 - {round(percentage_failed_3, 2)}%')
# print(f'4 - {round(percentage_failed_4, 2)}%')

''' 5. Постройте круговую диаграмму, показывающую распределение сдавших и не сдавших экзамен. '''


def pie_pass_distr(data):
    passed_count = len(data.loc[data["Балл"] > data['Минимальный балл']])
    failed_count = len(data) - passed_count
    fig, ax = plt.subplots()
    ax.pie([passed_count, failed_count], labels=["Сдали", "Не сдали"], autopct='%1.1f%%')
    return fig, ax


# fig, ax = pie_pass_distr(data1)
# plt.show()

''' 6. Постройте ядерную оценку плотности распределению баллов за экзамен. '''


def kde_distr(data):
    fig, ax = plt.subplots()
    ax = data["Балл"].plot.kde()
    return fig, ax

# fig, ax = kde_distr(data1)
# plt.show()


''' 7. Найдите процентное соотношение учащихся, сдавших экзамен на «отлично», «хорошо», «удовлетворительно», 
«неудовлетворительно». '''


def find_ratio_marks(data):
    unsatisfiyng = round(len(data.loc[data['Балл'] <= 26])*100/len(data), 2)
    satisfiyng = round(len(data.loc[(data['Балл'] >= 27) & (data['Балл'] <= 49)])*100/len(data), 2)
    good = round(len(data.loc[(data["Балл"] >= 50) & (data['Балл'] <= 67)])*100/len(data), 2)
    excellent = round(len(data.loc[data['Балл'] >= 68])*100/len(data), 2)
    print(f'Отлично - {excellent}%, Хорошо - {good}%, Удовлетворительно - {satisfiyng}%, Неудовлетворительно - {unsatisfiyng}%')
    # return un, sa, go, ex

# ratio_marks_1 = find_ratio_marks(data1)
# ratio_marks_2 = find_ratio_marks(data2)
# ratio_marks_3 = find_ratio_marks(data3)
# ratio_marks_4 = find_ratio_marks(data4)


''' 8. Какое процентное соотношение юношей и девушек писало данный экзамен? '''


def find_ratio_sex(data):
    male = round(len(data.loc[data['Пол'] == "М"])*100/len(data), 2)
    fem = round(len(data.loc[data['Пол'] == "Ж"])*100/len(data), 2)
    print(f'male - {male}%, female - {fem}%')
    return male, fem

# ratio_sex_1 = find_ratio_sex(data1)
# ratio_sex_2 = find_ratio_sex(data2)
# ratio_sex_3 = find_ratio_sex(data3)
# ratio_sex_4 = find_ratio_sex(data4)


''' 9. Сколько школ принимало участие в экзамене? '''

def find_school_quantity(data):
    return data.groupby('№ школы').count().shape[0]


sch_quan_1 = find_school_quantity(data1)
sch_quan_2 = find_school_quantity(data2)
sch_quan_3 = find_school_quantity(data3)
sch_quan_4 = find_school_quantity(data4)

# print(f'кол-во школ представленных на экзамене:\n#1 - {sch_quan_1}\n#2 - {sch_quan_2}\n#3 - {sch_quan_3}\n#4 - {sch_quan_4}')


''' 10. Сколько всего заданий с кратким ответом? С развернутым ответом? '''


def find_short_ans_qu(data):
    vars_quan = data.groupby('Номер варианта').count().shape[0]
    return vars_quan * len(data['Задания с кратким ответом'].iloc[0])


def find_ext_ans_qu(data):
    vars_quan = data.groupby('Номер варианта').count().shape[0]
    num = list(data['Задания с развёрнутым ответом'].iloc[0]).count('(')
    return vars_quan * num


short_1, ex_1 = find_short_ans_qu(data1), find_ext_ans_qu(data1)
short_2, ex_2 = find_short_ans_qu(data2), find_ext_ans_qu(data2)
short_3, ex_3 = find_short_ans_qu(data3), find_ext_ans_qu(data3)
short_4, ex_4 = find_short_ans_qu(data4), find_ext_ans_qu(data4)

# print(f'кол-во уникальных заданий(с кратким ответом/развернутым ответом):\n{short_1}||{ex_1}\n{short_2}||{ex_2}\n{short_3}||{ex_3}\n{short_4}||{ex_4}')


''' 11. Пусть задания с кратким ответом будут задания типа В. Соответственно всего по экзамену вопросов класса В: В
, ... Вк. Посчитайте процент выполненных и невыполненных заданий по каждому вопросу класса В.'''


def countB(data):
    done = 0
    undone = 0
    for item in data['Задания с кратким ответом'].tolist():
        if isinstance(item, str):
            for char in item:
                if char == '+' or (char.isdigit() and char != '0'):
                    done += 1
                else:
                    undone += 1
    overall = done + undone
    # print(f'выполн - {round(done*100/overall, 2)}%, не выполнен - {round(undone*100/overall, 2)}%')
    return done / overall, undone / overall


# B_ratio_1 = countB(data1)
# B_ratio_2 = countB(data2)
# B_ratio_3 = countB(data3)
# B_ratio_4 = countB(data4)


''' 12. Аналогично и с типом С (ответы с развернутым ответом). '''


def countC(data):
    done = 0
    undone = 0
    for item in data['Задания с развёрнутым ответом'].tolist():
        if isinstance(item, str):
            ind = 0
            while ind < (len(item)):
                if item[ind].isdigit() and item[ind]!='0':
                    done += 1
                if item[ind] == '0':
                    undone += 1
                ind += 4
    overall = done + undone
    # print(f'выполн - {round(done*100/overall, 2)}%, не выполнен - {round(undone*100/overall, 2)}%')
    return done / overall, undone / overall


# C_ratio_1 = countC(data1)
# C_ratio_2 = countC(data2)
# C_ratio_3 = countC(data3)
# C_ratio_4 = countC(data4)


''' 13. Сделайте анализ по двум школам:
I. по всем выполненным заданиям типа В
II. по заданиям типа С больше 50%
III. по среднему баллу юношей и девушек. 
Для каждого задания добавьте соответствующий раздел в ноутбук и текстовые пояснения. '''


def b_comparison(data, sch_num1, sch_num2):
    sch_1_df = data[data["№ школы"] == sch_num1]
    sch_2_df = data[data["№ школы"] == sch_num2]
    sch_1_rat = countB(sch_1_df)
    sch_2_rat = countB(sch_2_df)
    return sch_1_rat, sch_2_rat

sch_comp = b_comparison(data1, 117, 148)
# print(f'\nшкола #1 выполнила {round(sch_comp[0][0]*100, 2)}% заданий B-типа\nшкола #2 выполнила {round(sch_comp[1][0]*100, 2)}% заданий B-типа')

