import socket
import logging

class Server:
    def __init__(self):
        self.users = {}
        self.logger = self.get_logger()
        self.sock = socket.socket()
        self.port = 9090
        self.conn = None
        self.pass_key = 0
        self.connection_active = 0

    def get_users(self):
        try:
            users = open('users.txt')
            for user in users:
                data = user.split(':')
                users_list = [data[1], data[2][:len(data[2]) - 1]]
                self.users[data[0]] = users_list
            users.close()
        except:
            self.users = {}

    def get_logger(self):
        logger = logging.getLogger('server')
        logger.setLevel(logging.INFO)
        file_handler = logging.FileHandler('server.log')
        formatter = logging.Formatter('%(asctime)s - %(name)s - %(levelname)s - %(message)s')
        file_handler.setFormatter(formatter)
        logger.addHandler(file_handler)
        return logger

    def set_port(self):
        while True:
            port = input('Введите порт -> ')
            if port == 'def':
                port = 9090
                break
            elif port.isdigit() and 0 < int(port) < 65535:
                break
            else:
                print('Ошибка!')
        self.port = int(port)
        while self.port < 65535:
            if self.port >= 65535:
                raise AssertionError('Все порты заняты')
            try:
                self.sock.bind(('', int(self.port)))
                break
            except socket.error:
                self.port += 1

    def write_users(self):
        try:
            f = open('users.txt', 'w')
            for line in self.users.items():
                f.write(str(line[0]) + ':' + str(line[1][0]) + ':' + str(line[1][1]) + '\n')
            f.close()
        except:
            pass

    def listen(self):
        while True:
            self.write_users()
            if self.connection_active == 0:
                self.conn, addr = self.sock.accept()
                user_port = addr[0]
                if user_port in self.users:
                    self.conn.send('SERVER -> Привет, '.encode() + self.users[user_port][0].encode() + '\n Введите пароль -> '.encode())
                else:
                    self.conn.send('SERVER -> Привет! Введите имя и пароль через пробел:'.encode())
                self.logger.info(f'Клиент {str(addr)} подключен')
                print(f'Клиент {str(addr)} подключен')
                self.connection_active = 1
            else:
                try:
                    data = self.conn.recv(1024)
                    if not data:
                        self.logger.info('Отключение клиента...')
                        print('Клиент отключился')
                        self.connection_active = 0
                        self.pass_key = 0
                        self.conn.close()
                    elif self.pass_key == 0:
                        if user_port in self.users:
                            if str(data.decode()[9:]) == self.users[addr[0]][1]:
                                self.conn.send('SERVER -> Пароль верен'.encode())
                                self.pass_key = 1
                            else:
                                self.pass_key = 0
                                self.connection_active = 0
                                self.conn.send('SERVER -> Пароль неверен'.encode())
                                self.conn.close()
                        else:
                            info = str(data.decode())[9:]
                            r = []
                            try:
                                r.append(info.split()[0])
                                r.append(info.split()[1])
                                self.users[addr[0]] = r
                                print('Новый пользователь ' + str(info.split()[0]))
                                self.conn.send('SERVER -> Пользователь зарегистрирован'.encode())
                                self.pass_key = 1
                            except IndexError:
                                self.conn.send('SERVER -> Введите имя и пароль через пробел!'.encode())
                                self.pass_key = 0

                    else:
                        key = input('Для выключения сервера, напишите что-то кроме enter')
                        info = data.decode()[9:]
                        print('Данные получены: ' + info)
                        print(f'Длина данных: {len(data.decode()[9:])}')
                        self.logger.info('Данные получены:')
                        self.conn.send('SERVER -> '.encode() + info.upper().encode())
                        self.logger.info('Отправка данных клиенту...')
                        if key != "":
                            break
                except ConnectionError:
                    print('Клиент отключен')
                    self.logger.info('Отключение клиента...')
                    self.connection_active = 0
                    self.pass_key = 0
                    self.conn.close()

    def main(self):
        self.get_users()
        self.set_port()
        print(f'Пользователи: {self.users}')
        print('Сервер запущен!')
        self.logger.info('Сервер запущен!')
        self.logger.info(f'Начало прослушивание порта {str(self.port)}')
        self.sock.listen(1)
        self.listen()
        self.logger.info('Соединение закрыто!')
        print('Закрыли соединение')

        self.logger.info('Остановка сервера...')
        self.conn.send('SERVER -> '.encode() + 'отключение...'.encode())
        self.conn.close()
        self.sock.close()

server = Server()
server.main()