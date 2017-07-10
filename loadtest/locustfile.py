from locust import HttpLocust, TaskSet, task
import json 

class Car(TaskSet):
    @task()
    def position(self):
	position = {"lon": 11.5653, "lat": 54.454, "vin": "YV1234567890", "timestamp": 1234567890}
	headers = {"content-type": "application/json"}
	self.client.post("/send", data=json.dumps(position), headers=headers)

class CarLocust(HttpLocust):
    task_set = Car
    min_wait = 1000
    max_wait = 1000

