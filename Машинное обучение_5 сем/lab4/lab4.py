import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
from sklearn.preprocessing import PolynomialFeatures

x = pd.read_csv('data/0_x.csv', header=None)
y = pd.read_csv('data/0_y.csv', header=None)

'''1. Самостоятельно постройте парные регрессии от каждого атрибута с целевой переменной.'''

# for i in range(5):
#     binary = LinearRegression()
#     binary.fit(x[[i]], y)
#     xx = np.linspace(x[1].min(), x[1].max(), 100).reshape((-1, 1))
#     plt.plot(xx, binary.predict(xx), c='r')
#     plt.scatter(x[1], y)
#     print(binary.coef_, i)
#
# plt.show()


'''2. (*) Модифицируйте код из предыдущей работы так, чтобы модель регрессии умела работать с входными данными, 
состоящими из произвольного количества столбцов.'''


# class Model(object):
#     """Модель парной линейной регрессии"""
#
#     def __init__(self, n):
#         self.betas = np.array([0] * n)
#
#     def predict(self, X):
#         return np.dot(X, self.betas.T)
#
#     def error(self, X, Y):
#         return (np.linalg.norm(self.predict(X)[:, None] - Y) ** 2) / len(Y)
#
#     def fit(self, X, Y, alpha=0.001, accuracy=0.01, max_steps=10_000):
#         steps, errors = [], []
#         step = 0
#         for _ in range(max_steps):
#             self.betas = self.betas - alpha * ((2 * np.dot(X.T, np.dot(X, self.betas.T)[:, None] - Y) / len(Y)).T)[0]
#             new_err = self.error(X, Y)
#             step += 1
#             steps.append(step)
#             errors.append(new_err)
#         return steps, errors
#
#
# x['b0'] = np.array([1] * x.shape[0])
# x = x.astype('float64')
#
# lin_regr = Model(x.shape[1])
# steps, errors = lin_regr.fit(x, y)
#
# plt.figure()
# plt.plot(steps, errors, 'g')
# plt.show()


'''3. Для целевой переменной и каждого из признаков постройте полиномиальную регрессию второго, третьего и десятого 
порядков. Сделайте вывод о значимости регрессии.'''


def pol_reg_2(X, Y):
    reg = PolynomialFeatures(degree=2)
    X_pol = reg.fit_transform(X)
    lr = LinearRegression()
    lr.fit(X_pol, Y)

    return lr.score(X_pol, Y)


def pol_reg_3(X, Y):
    reg = PolynomialFeatures(degree=3)
    X_pol = reg.fit_transform(X)
    lr = LinearRegression()
    lr.fit(X_pol, Y)

    return lr.score(X_pol, Y)


def pol_reg_10(X, Y):
    reg = PolynomialFeatures(degree=10)
    X_pol = reg.fit_transform(X)
    lr = LinearRegression()
    lr.fit(X_pol, Y)

    return lr.score(X_pol, Y)
#
#
# for i in range(5):
#     print(f'R2_score для {i} признака:')
#     print(f'Полином 2 степени: {pol_reg_2(x[[i]], y)} \nПолином 3 степени: {pol_reg_3(x[[i]], y)} '
#           f'\nПолином 10 степени: {pol_reg_10(x[[i]], y)}')

# Регрессия наиболее значима при степени полинома = 10 для всех признаков


'''4. Постройте сводную таблицу показателей точности всех построенных моделей. Сделайте вывод, какие модели 
переобученнные, какие - недообученные.'''


def lin_reg(X, Y):
    reg = LinearRegression()
    reg.fit(X, Y)

    return reg.score(X, Y)


# table = pd.DataFrame({'Признак': [0, 1, 2, 3, 4], 'Линейная регрессия': [lin_reg(x[[0]], y), lin_reg(x[[1]], y), lin_reg(x[[2]], y), lin_reg(x[[3]], y), lin_reg(x[[4]], y)], 'Полиномиальная регрессия 2': [pol_reg_2(x[[0]], y), pol_reg_2(x[[1]], y), pol_reg_2(x[[2]], y), pol_reg_2(x[[3]], y), pol_reg_2(x[[4]], y)], 'Полиномиальная регрессия 3': [pol_reg_3(x[[0]], y), pol_reg_3(x[[1]], y), pol_reg_3(x[[2]], y), pol_reg_3(x[[3]], y), pol_reg_3(x[[4]], y)], 'Полиномиальная регрессия 10': [pol_reg_10(x[[0]], y), pol_reg_10(x[[1]], y), pol_reg_10(x[[2]], y), pol_reg_10(x[[3]], y), pol_reg_10(x[[4]], y)]})
# print(table)


'''5. Используйте другие метрики качества регрессионной модели в дополнение к коэффициенту детерминации.'''


# Функции для регрессий
def linear_predict(X, Y):
    reg = LinearRegression()
    reg.fit(X, Y)
    return reg.predict(X)

def poly_predict(X, Y):
    reg = PolynomialFeatures(degree=10)
    X_pol = reg.fit_transform(X)
    lr = LinearRegression()
    lr.fit(X_pol, Y)
    return lr.predict(X_pol)

# Функции для рассчета метрик качетсва
def tss(X, Y):
    TSS = ((Y - Y.mean())**2).sum()[0]
    return TSS

def ess(X, Y, func_reg):
    ESS = ((Y - func_reg(X, Y))**2).sum()[0]
    return ESS

def rss(X, Y, func_reg):
    RSS = ((func_reg(X, Y) - Y.mean()[0])**2).sum()
    return RSS



TSS = [tss(x[[0]], y), tss(x[[1]], y), tss(x[[2]], y), tss(x[[3]], y), tss(x[[4]], y)]
ESS = [ess(x[[0]], y, linear_predict), ess(x[[1]], y, linear_predict), ess(x[[2]], y, linear_predict), ess(x[[3]], y, linear_predict), ess(x[[4]], y, linear_predict)]
RSS = [rss(x[[0]], y, linear_predict), rss(x[[1]], y, linear_predict), rss(x[[2]], y, linear_predict), rss(x[[3]], y, linear_predict), rss(x[[4]], y, linear_predict)]


# Таблица для линейной регрессии
# table_lin = pd.DataFrame(data={'TSS': TSS, 'ESS': ESS, 'RSS': RSS})
# print(table_lin)
