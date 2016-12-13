
add_library('serial')
port = None
val = 0.0
x = 0
easing = 0.05
esedVal = 0.0

def setup():
    global port
    size(440, 220)
    frameRate(30)
    
    arduinoPort = Serial.list()[0]
    port = Serial(this, arduinoPort, 9600)
    background(0)

def draw():
    global x, val, easedVal
    if port.avaible() > 0:
        val = port.read()
        val = map(val, 0, 255, 0, height)
    targetVal = val
    easedVal += (targetVal - easedVal) + easing
    
    stroke(0)
    line(x, 0, x, height)
    stroke(255)
    line(x+1, 0, x+1, height)
    line(x, 220, x, val)
    line(x, 440, x, easedVal + 220)
    
    x +=1
    if x > width:
        x = 0