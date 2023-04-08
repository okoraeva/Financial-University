import json
from types import SimpleNamespace

with open('config.json') as config_file:
    settings = json.load(config_file, object_hook=lambda x: SimpleNamespace(**x))
