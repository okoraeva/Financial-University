import socket
import datetime
import random
import json
import os
import re
import threading
import magic

def get_date():
    return datetime.datetime.utcnow().strftime(r"%a, %d %b %Y %H:%M:%S GMT")

def config_reader(filename = "config.json"):
    with open(filename, "r") as jsonfile:
        config = json.load(jsonfile)
    return config["port"], config["request_volume"], config["root"]


def socket_binder(port = 80):
    sock = socket.socket()
    while True:
        try:
            sock.bind(('', port))
            print(f"Using port {port}")
            return sock
        except OSError:
            print(f"port {port} unavailable")
            port = random.randint(1024, 65535)

def http_response(content, request_date, addr, resource, http_response_code, content_type):
    content_length = len(content)
    log = [str(item) for item in [request_date, addr, resource, http_response_code]]
    log = "; ".join(log)
    with LOCK:
        with open(LOGFILE, "a", encoding = "utf8") as logfl:
            logfl.write(log+"\n")

    response = f"""HTTP/1.1 {http_response_code}
Date: {get_date()}
Content-length: {content_length}
Server: SelfMadeServer v0.0.1
Content-type: {content_type}
Connection: retry-after
"""
    response = response.encode() + content
    return response

def resource_parser(request):
    request = request.split("\r\n")
    http_request = request[0].split()[1]
    request_path = re.split("[\\/]", http_request)
    if request_path == ["", ""]:
        request_path = ["1.html"]


    allowed = bool(re.search("\.(html|css|js|png|jpg|jpeg|pdf)$", request_path[-1]))
    return request_path, http_request, allowed


def request_reader(request, root = "."):
    resource, http_path, allowed = resource_parser(request)
    resource = os.path.join(root, *resource)
    content_type = "text/html"
    if allowed:

        try:
            with open(resource, "rb") as contentfile:
                content = contentfile.read(1024*1024*16)
            response_code = "200 OK"
            mime = magic.Magic(mime=True)
            content_type = mime.from_file(resource)
        except:
            content = b""
            response_code = "404 Not Found"
    else:
        content = b""
        response_code = "403 Forbidden"
    return content, http_path, response_code, content_type

def client_thread(conn, addr, max_vol, root):
    while True:
        request = conn.recv(max_vol).decode() #iso-8859-1
        request_date = get_date()
        if not request:
            continue
        content, resource, response_code, content_type = request_reader(request, root)
        conn.send(http_response(content, request_date, addr, resource, response_code,content_type))

LOGFILE = "log.txt"
LOCK = threading.Lock()
port, max_vol, root = config_reader()
sock = socket_binder()
sock.listen(5)
while True:
    conn, addr = sock.accept()
    with LOCK:
        print("Connected", addr)
    threading.Thread(target = client_thread, args = (conn, addr[0], max_vol, root), daemon = True).start()