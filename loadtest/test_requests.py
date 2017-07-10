import requests
import sys

url = "http://10.0.2.2:8800/send"

payload = '{"vin": "YV0987654321", "lon": 11.123, "lat": 54.1234, "timestamp": 1234567890}'
headers = {"content-type": "application/json"}

response = requests.request("POST", url, data=payload, headers=headers)
print(response.status_code)
