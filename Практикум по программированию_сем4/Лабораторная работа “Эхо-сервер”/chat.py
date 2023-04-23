import socket
import threading
import queue
import sys
import random
import os

d = '000'

#Client Code
def ReceiveData(sock):
    global d
    while True:
        try:
            data,addr = sock.recvfrom(1024)
            print(data.decode('utf-8'))
            d = data.decode()
        except:
            pass

def RunClient(serverIP):
    global d
    host = socket.gethostbyname(socket.gethostname())
    port = random.randint(6000, 10000)
    print('Client IP->' + str(host) + ' Port->'+str(port))
    server = (str(serverIP), 5000)
    sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    sock.bind((host, port))

    name = input('Введите имя: ')
    if name == '':
        name = 'Guest' + str(random.randint(1000,9999))
        print('Твое имя:' + name)
    sock.sendto(name.encode('utf-8'), server)
    threading.Thread(target=ReceiveData, args=(sock,)).start()
    while True:
        if d == "cstop":
            break
        data = input()
        if data == 'qqq' or data == 'stop':
            break
        elif data=='':
            continue
        data = '['+name+']' + '->'+ data
        sock.sendto(data.encode('utf-8'),server)
    sock.sendto(data.encode('utf-8'),server)
    sock.close()
    os._exit(1)

#Server Code
def RecvData(sock, recvPackets):
    while True:
        data,addr = sock.recvfrom(1024)
        recvPackets.put((data,addr))

def RunServer():
    host = socket.gethostbyname(socket.gethostname())
    port = 5000
    print('Server hosting on IP-> '+str(host))
    sock = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
    sock.bind((host,port))
    clients = set()
    recvPackets = queue.Queue()

    print('Server Running...')

    threading.Thread(target=RecvData,args=(sock, recvPackets)).start()

    while True:
        while not recvPackets.empty():
            data,addr = recvPackets.get()
            if addr not in clients:
                clients.add(addr)
                continue
            clients.add(addr)
            data = data.decode('utf-8')
            if 'stop' in data:
                for client in clients:
                    sock.sendto('cstop'.encode('utf-8'), client)
                sock.close()
                os._exit(1)
            if data.endswith('qqq'):
                clients.remove(addr)
                continue
            print(str(addr)+data)
            for client in clients:
                if client!=addr:
                    sock.sendto(data.encode('utf-8'), client)
    sock.close()


#Serevr Code Ends Here

if __name__ == '__main__':
    if len(sys.argv)==1:
        RunServer()
    elif len(sys.argv)==2:
        RunClient(sys.argv[1])
    else:
        print('Run Serevr:-> python Chat.py')
        print('Run Client:-> python Chat.py <ServerIP>')