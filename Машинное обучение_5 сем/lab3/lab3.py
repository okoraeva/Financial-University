import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

'''1. Проверьте работу модели с разными начальными значениями параметров. Убедитесь, что модель всегда сходится к 
оптимуму.'''


x = pd.read_csv('data/x.csv', index_col=0)['0']
y = pd.read_csv('data/y.csv', index_col=0)['0']


class Model(object):
    """Модель парной линейной регрессии"""

    def __init__(self):
        self.b0 = 0
        self.b1 = 0

    def predict(self, X):
        return self.b0 + self.b1 * X

    def error(self, X, Y):
        return sum(((self.predict(X) - Y) ** 2) / (2 * len(X)))

    def fit(self, X, Y, alpha=0.001, accuracy=0.01, max_steps=5000):
        steps, errors = [], []
        step = 0
        for _ in range(10000):
            dJ0 = sum(self.predict(X) - Y) / len(X)
            dJ1 = sum((self.predict(X) - Y) * X) / len(X)
            self.b0 -= alpha * dJ0
            self.b1 -= alpha * dJ1
            new_err = hyp.error(X, Y)
            step += 1
            steps.append(step)
            errors.append(new_err)
        return steps, errors


hyp = Model()

# steps, errors = hyp.fit(x, y, alpha=0.001, accuracy=0.00005, max_steps=500)
# J = hyp.error(x, y)
# print("error after gradient descent:", J)
#
# steps, errors = hyp.fit(x, y, alpha=0.001, accuracy=0.00000001, max_steps=1000)
# J = hyp.error(x, y)
# print("error after gradient descent:", J)
#
# steps, errors = hyp.fit(x, y, alpha=0.001, accuracy=100, max_steps=50000)
# J = hyp.error(x, y)
# print("error after gradient descent:", J)

'''2. Проверьте работу модели с другими значениями скорости обучения. Найдите значение, при котором градиентный спуск
 расходится.'''

# for alpha in list(np.linspace(1, 3, 21)):
#     steps, errors = hyp.fit(x, y, alpha)
#     J = hyp.error(x, y)
#     print(f"error after gradient descent (alpha = {alpha}) :", J)


'''3. Модифицируйте код модели таким образом, чтобы он корректно работал как с Series, так и с DataFrame. 
При этом, конечно, датафрейм не должен содержать "лишних" столбцов.'''

# x = pd.Series('data/x.csv')
# y = pd.Series('data/y.csv')
#
# print(type(x))
# print(type(y))
#
# x = pd.read_csv('data/x.csv', index_col=0)['0']
# y = pd.read_csv('data/y.csv', index_col=0)['0']
#
#
# class Model(object):
#     """Модель парной линейной регрессии"""
#
#     def __init__(self):
#         self.b0 = 0
#         self.b1 = 0
#
#     def predict(self, X):
#         if isinstance(X, pd.Series):
#             X = X.to_frame()
#         return self.b0 + self.b1 * X
#
#     def error(self, X, Y):
#         if isinstance(X, pd.Series):
#             X = X.to_frame()
#         if isinstance(Y, pd.Series):
#             Y = Y.to_frame()
#         return sum(((self.predict(X) - Y) ** 2) / (2 * len(X)))
#
#     def fit(self, X, Y, alpha=0.001, accuracy=0.01, max_steps=5000):
#         if isinstance(X, pd.Series):
#             X = X.to_frame()
#         steps, errors = [], []
#         step = 0
#         for _ in range(10000):
#             dJ0 = sum(self.predict(X) - Y) / len(X)
#             dJ1 = sum((self.predict(X) - Y) * X) / len(X)
#             self.b0 -= alpha * dJ0
#             self.b1 -= alpha * dJ1
#             new_err = hyp.error(X, Y)
#             step += 1
#             steps.append(step)
#             errors.append(new_err)
#         return steps, errors


'''4. Модифицируйте алгоритм градиентного спука таким образом, чтобы он останавливаля, если ошибка перестает снижаться. 
Для этого сравнивайте, на сколько понизилась ошибка на каждом шаге и если это снижение меньше некоторой заранее 
заданной очень маленькой величины - выходите из цикла. Эту величину можно взять одну миллионную, например.'''

# class Model(object):
#     """Модель парной линейной регрессии"""
#
#     def __init__(self):
#         self.b0 = 0
#         self.b1 = 0
#
#     def predict(self, X):
#         return self.b0 + self.b1 * X
#
#     def error(self, X, Y):
#         return sum(((self.predict(X) - Y) ** 2) / (2 * len(X)))
#
#     def fit(self, X, Y, alpha=0.001, accuracy=0.01, max_steps=5000):
#         steps, errors = [], []
#         step = 0
#         old_err = self.error(X, Y)
#         new_err = old_err - 2 * accuracy
#
#         while (abs(old_err - new_err) >= accuracy) and (step <= max_steps):
#             dJ0 = sum(self.predict(X) - Y) / len(X)
#             dJ1 = sum((self.predict(X) - Y) * X) / len(X)
#             self.b0 -= alpha * dJ0
#             self.b1 -= alpha * dJ1
#             old_err = new_err
#             new_err = hyp.error(X, Y)
#             step += 1
#             steps.append(step)
#             errors.append(new_err)
#
#         return steps, errors
#
#
# learning_rates = [0.1]
#
# for lr in learning_rates:
#     hyp = Model()
#     steps, errors = hyp.fit(x, y, alpha=lr)
#     J = hyp.error(x, y)
#     print("error after gradient descent with lr={}: {}".format(lr, J))
#     plt.plot(steps, errors, label="lr={}".format(lr))
#
#     # Вывод текущей ошибки на каждом шаге
#     for i in range(len(errors)):
#         if i > 0:
#             error_diff = errors[i - 1] - errors[i]
#             if error_diff < 0.000000000000000000001:
#                 break
#             print("Step {}: error diff = {}".format(i, error_diff))
#
# plt.legend()
# plt.show()

'''5. Убедитесь, что алгоритм градиентного спуска завершается всегда - либо если ошибка перестает существенно меняться, 
либо при достижении максимального количества шагов.'''


class Model:
    """Модель парной линейной регрессии"""

    def __init__(self):
        self.b0 = 0
        self.b1 = 0

    def predict(self, x):
        return self.b0 + self.b1 * x

    def error(self, X, Y):
        return sum((self.predict(X) - Y) ** 2) / (2 * len(X))

    def fit(self, X, Y, alpha=0.0001, accuracy=0.000001, max_steps=5000):
        steps, errors = [], []
        flag = 1
        step = 0

        old_err = self.error(X, Y)
        new_err = self.error(X, Y) - 1

        while flag > accuracy and (step < max_steps):
            dJ0 = sum(self.predict(X) - Y) / len(X)
            dJ1 = sum((self.predict(X) - Y) * X) / len(X)

            self.b0 -= alpha * dJ0
            self.b1 -= alpha * dJ1

            old_err = new_err
            new_err = self.error(X, Y)

            flag = abs(old_err - new_err)

            step += 1
            steps.append(step)
            errors.append(new_err)

        return steps, errors


'''6. Реализуйте механизм адаптивной скорости обучения. Чтобы не задавать вручную параметр alpha можно отслеживать, 
увеличивается ли ошибка после первого шага градиентного спуска. В начале задаем скорость обучения близкой к 1.
 Если ошибка после одного шага увеличивается - скорость обучения надо уменьшить (например, в 2 раза). 
 Если не увеличивается - скорость обучения можно не трогать. Еще подумайте, как после уменьшения скорости перезапускать 
 градиентный спуск заново.
 
 7. Добавьте к классу модели метод рисования графиков. Сделайте так, чтобы он был независим от масштаба переданных 
данных.'''


class Model(object):
    """Модель парной линейной регрессии"""

    def __init__(self):
        self.b0 = 0
        self.b1 = 0

    def print_graf(self, X, Y):
        plt.figure()
        plt.scatter(x, y)
        plt.show()

    def predict(self, X):
        return self.b0 + self.b1 * X

    def error(self, X, Y):
        return sum(((self.predict(X) - Y) ** 2) / (2 * len(X)))

    def fit(self, X, Y, alpha=0.001, accuracy=0.01, max_steps=5000):

        steps, errors = [], []
        step = 0
        new_err = self.error(X, Y)
        old_err = new_err
        while step < max_steps and abs(new_err - old_err) > accuracy:
            dJ0 = sum(self.predict(X) - Y) / len(X)
            dJ1 = sum((self.predict(X) - Y) * X) / len(X)
            self.b0 -= alpha * dJ0
            self.b1 -= alpha * dJ1
            old_err = new_err
            new_err = hyp.error(X, Y)
            if new_err > old_err:
                alpha /= 2
                self.b0 += alpha * dJ0
                self.b1 += alpha * dJ1
                old_err = hyp.error(X, Y)
            else:
                continue
            step += 1
            steps.append(step)
            errors.append(new_err)

        return steps, errors


hyp = Model()
hyp.print_graf(x, y)
