import RPi.GPIO as gpio
import socket
import time
import serial
import Adafruit_DHT
import threading

HOST = ""
PORT = 9797
PORT2 = 9798
PORT3 = 9799

ser = serial.Serial('/dev/ttyACM1',9600)
sensor = Adafruit_DHT.DHT11

gpio.setmode(gpio.BCM)
Left_moter = 26
Right_moter = 13
Rdir = 6
Ldir = 19
LED_right = 15
LED_left = 14
Sensor_pin = 2
buffer1 = "0"
buffer2 = "0"
speed = 30

gpio.setup(Left_moter, gpio.OUT)
gpio.setup(Right_moter, gpio.OUT)
gpio.setup(Rdir, gpio.OUT)
gpio.setup(Ldir, gpio.OUT)
gpio.setup(LED_right, gpio.OUT)
gpio.setup(LED_left, gpio.OUT)

LMpwm = gpio.PWM(Left_moter, 50)
RMpwm = gpio.PWM(Right_moter, 50)

LMpwm.start(0)
RMpwm.start(0)

def speed_controll(buffer):
    global speed
    speed = int(buffer)

def servo_init():
    global ser
    ser.writelines("2180")
    ser.writelines("1180")

def Sensor(client_socket2):
    global sensor
    try:
        while True:
            time.sleep(2.5)
            humidity, temperature = Adafruit_DHT.read_retry(sensor, Sensor_pin)
            humidity = int(humidity)
            temperature = int(temperature)
            data = str(humidity)+"|"+str(temperature)
            if humidity is not None and temperature is not None:
                client_socket2.sendall(data)
            else:
                client_socket2.sendall("plz Sensor Check")
    except Exception as ex:
        print(ex)
        
def server3():
    try:
        server_socket3 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server_socket3.bind((HOST, PORT3))
        server_socket3.listen(0)
        print("third socket please")
        client_socket3, addr3 = server_socket3.accept()
        print("third socket connect")
        while True:
            buffer3 = (client_socket3.recv(30).decode())
            print(buffer3)
            if buffer3 == "60":
                gpio.output(LED_right, False)
            if buffer3 == "70":
                gpio.output(LED_left, False)
            if buffer3 == "61":
                gpio.output(LED_right, True)
            if buffer3 == "71":
                gpio.output(LED_left, True)
    except Exception as ex:
        print(ex)
        
def server2():
    server_socket2 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket2.bind((HOST, PORT2))
    server_socket2.listen(0)
    print("second socket please")
    client_socket2, addr2 = server_socket2.accept()
    print("second socket connect")
    t1 = threading.Thread(target=Sensor, args=(client_socket2,))
    t1.daemon = True
    t1.start()

try:
    servo_init()
    server2 = threading.Thread(target=server2)
    server2.daemon = True
    server2.start()
    server3 = threading.Thread(target=server3)
    server3.daemon = True
    server3.start()

    server_socket1 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket1.bind((HOST, PORT))
    server_socket1.listen(0)
    print("first socket please")
    client_socket1, addr1 = server_socket1.accept()
    print("first socket connect")
   # time.sleep(2)
    client_socket1.sendall("SixSens_01")
    while True:
        buffer1 = (client_socket1.recv(65535).decode())
	print(buffer1)
        if not (len(buffer1.encode('utf-8')) == 5) and buffer1[0:1] == '1' or buffer1[0:1] == '2':
            ser.writelines(buffer1)
        if buffer1[0:1] == '4':
            speed_controll(buffer1[1:3])
        if buffer1 == "11000":
            gpio.output(Rdir, True)
            gpio.output(Ldir, True)
            LMpwm.ChangeDutyCycle(speed)
            RMpwm.ChangeDutyCycle(speed)
        if buffer1 == "10100":
            gpio.output(Rdir, False)
            gpio.output(Ldir, True)
            LMpwm.ChangeDutyCycle(speed+30)
            RMpwm.ChangeDutyCycle(speed+30)
        if buffer1 == "10001":
            gpio.output(Rdir, True)
            gpio.output(Ldir, False)
            LMpwm.ChangeDutyCycle(speed+30)
            RMpwm.ChangeDutyCycle(speed+30)
        if buffer1 == "10010":
            gpio.output(Rdir, False)
            gpio.output(Ldir, False)
            LMpwm.ChangeDutyCycle(speed)
            RMpwm.ChangeDutyCycle(speed)
        if buffer1 == "10000":
            LMpwm.ChangeDutyCycle(0)
            RMpwm.ChangeDutyCycle(0)
    LMpwm.stop()
    RMpwm.stop()
except Exception as ex:
    print(ex)
    gpio.cleanup()
    server_socket1.close()
    server_socket2.close()
    server_scoket3.close()
