
add_library('sound')

mic = None
amp = None

def setup():
    global mic, amp
    size(440, 440)
    background(0)
    
    mic = AudioIn(this, 0)
    mic.start()
    
    amp = Amplitude(this)
    amp.input(mic)
    
def draw():
    noStroke()
    fill(26, 76, 102, 10)
    rect(0, 0, width, height)
    
    diameter = map(amp.analyze()*100, 0, 1, 10, width)
    fill(255)
    ellipse(width/2, height/2, diameter, diameter)