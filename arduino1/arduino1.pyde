
add_library('serial')
port = None
val = 0.0

def setup():
    global port
    size(440, 220)
    
    arduinoPort = Serial.list()[0]
    port = Serial(this, arduinoPort, 9600)

def draw():
    global val
    if port.avaible() > 0:
        val = port.read()
        val = map(val, 0, 255, 0, height)
    rect(40, val-10, 360, 20)