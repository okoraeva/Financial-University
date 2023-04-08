import os
import shutil
from tabulate import tabulate
from distutils.dir_util import copy_tree
from settings import settings

class FileManager:

    PATH = settings.path

    def __init__(self):
        self.root_directory = settings.path
        self.display_data = []
        self.allowed_files = self.list_directory()[1]  # Объекты в разрешенной для изменения директории

    def create_directory(self): # Создание папки
        directory_name = str(input("Название новой директории: "))
        if not os.path.exists(f'{self.root_directory}/{directory_name}'):
            os.makedirs(f'{self.root_directory}/{directory_name}')
            print(f'Директория "{directory_name}" успешно создана')
            self.refresh_directory()
        else:
            print("Директория уже существует")

    def create_file(self): # Создание пустого файла с указанием имени
        file_name = str(input("Имя файла: "))
        if not os.path.exists(f'{self.root_directory}/{file_name}'):
            new_file = open(f'{self.root_directory}/{file_name}', 'w')
            new_file.close()
            print(f'Файл "{file_name}" успешно создан')
            self.refresh_directory()
        else:
            print('Файл с таким названием уже существует')

    def write_file(self): # Запись текста в файл
        file_name = input('Введите название файла для записи: ')
        try:
            with open(f'{self.root_directory}/{file_name}', mode='w') as f:
                f.write(str(input('Введите текст для записи в файл: ')))
        except IsADirectoryError:
            print('Это директория')
        else:
            print('Текст успешно записан в файл')

    def read_file(self): # Просмотр содержимого текстового файла
        file_name = str(input('Введите название файла для чтения: '))
        try:
            with open(f'{self.root_directory}/{file_name}', 'r') as f:
                text = f.read()
            for line in text.splitlines():
                print(line)
        except UnicodeDecodeError:
            print('Возникли проблемы с чтением файла')
        except FileNotFoundError:
            print(f'Файл с названием "{file_name}" не найден')

    def delete_directory(self): # Удаление папки по имени
        directory_name = input('Введите название директории для удаления: ')
        try:
            shutil.rmtree(f'{self.root_directory}/{directory_name}')
        except OSError as e:
            print(f'Error: {e.filename} - {e.strerror}')
        else:
            print(f'Директория "{directory_name}" успешно удалена')
            self.refresh_directory()

    def delete_file(self): # Удаление файла по имени
        file_name = input('Введите название файла для удаления: ')
        try:
            os.remove(f"{self.root_directory}/{file_name}")
        except OSError as e:
            print(f'Error: {e.filename} - {e.strerror}')
        else:
            print(f'Файл "{file_name}" успешно удалён')
            self.refresh_directory()

    def move_between_directories(self): # Перемещение между папками (в пределах рабочей папки) - заход в папку по имени, выход на уровень вверх;
        chosen_directory = input('Введите название директории чтобы переместиться: ')
        if chosen_directory!= self.PATH+'/..':
            try:
                self.root_directory += f'/{chosen_directory}'
                self.refresh_directory()
            except FileNotFoundError:
                delete_index = self.root_directory.rfind('/')
                self.root_directory = self.root_directory[:delete_index]
                print('Указанная директория не существует')
        else:
            print('Директория недоступна')

    def rename_file(self): # Переименование файла
        file_name = input('Введите название файла для переименования: ')
        file_to_rename = f'{self.root_directory}/{file_name}'
        new_name = str(input('Введите новое название файла: '))
        try:
            os.rename(file_to_rename, f'{self.root_directory}/{new_name}')
        except OSError as e:
            print(f'Ошибка: {e.filename} - {e.strerror}')
        else:
            print('Файл успешно переименован')
            self.refresh_directory()

    def copy_files(self): # Копирование файлов из одной папки в другую
        copy_files_from = input('Название директории, из который производится копирование: ')
        start_directory = f'{self.root_directory}/{copy_files_from}'
        copy_files_to = input('Название директории, куда производится копирование: ')
        end_directory = f'{self.root_directory}/{copy_files_to}'
        try:
            copy_tree(start_directory, end_directory)
        except OSError as e:
            print(f'Error: {e.filename} - {e.strerror}')
        else:
            print('Файлы успешно скопированы')

    def move_file(self): # Перемещение файлов
        self.list_directory()
        file_name = input('Введите название файла для перемещения: ')
        file_location = f"{self.root_directory}/{file_name}"
        move_file_to = input('Название директории, куда производится копирование: ')
        end_directory = f"{self.root_directory}/{move_file_to}"
        try:
            shutil.move(file_location, end_directory)
        except OSError as e:
            print(f'Error: {e.filename} - {e.strerror}')
        else:
            print('Файл успешно перемещен')

    def show_statistics(self): # Квотирование дискового пространства и отображение занятого оставшегося места
        print(shutil.disk_usage(self.root_directory))

    def refresh_directory(self): # Обновление объектов в текущей директории
        self.allowed_files = self.list_directory()[1]

    def list_directory(self): # Поиск и запись объектов текущей директории
        self.display_data = []
        files = os.scandir(path=self.root_directory)
        for file in files:
            object_data = f'Directory - {file.name}' if file.is_dir() else f'File - {file.name}'
            object_info = object_data.split(' - ')
            self.display_data.append(object_info)
        data = tabulate((i for i in self.display_data), headers=['Type', 'Name'], tablefmt='pipe',
                        stralign='center')
        return data, self.display_data

    def show_menu(self): # Отображение меню
        print('-----------Файловый менеджер-----------\n')
        commands = [['1', 'Просмотр директории'], ['2', 'Создать папку'], ['3', 'Удалить папку'],
                    ['4', 'Переход между директориями'], ['5', 'Создать пустой файл'], ['6', 'Запись текста в файл'],
                    ['7', 'Просмотр текстового файла'], ['8', 'Удалить файл'], ['9', 'Копирование из папки в папку'],
                    ['10', 'Перемещение файлов'], ['11', 'Переименовать файл'], ['12', 'Просмотр дискового пространства']]
        help_page = tabulate((i for i in commands), headers=['ID', 'Метод'], tablefmt='github', stralign='center')
        print(help_page)
        while True:
            choose = str(input(
                '\nhelp - список команд, exit - выйти из файлового менеджера\nВведите ID команды чтобы продолжить: '))
            print('\n')
            if choose == '1':
                self.list_directory()
                print(self.list_directory()[0])
            if choose == '2':
                self.create_directory()
            if choose == '3':
                self.delete_directory()
            if choose == '4':
                self.move_between_directories()
            if choose == '5':
                self.create_file()
            if choose == '6':
                self.write_file()
            if choose == '7':
                self.read_file()
            if choose == '8':
                self.delete_file()
            if choose == '9':
                self.copy_files()
            if choose == '10':
                self.move_file()
            if choose == '11':
                self.rename_file()
            if choose == '12':
                self.show_statistics()
            if choose.lower() == 'help':
                print(f'\n{help_page}')
            if choose.lower() == 'exit':
                exit()


def main():
    manager = FileManager()
    manager.show_menu()

if __name__ == '__main__':
    main()