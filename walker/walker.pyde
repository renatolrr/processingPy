

class Walker(object):
    def __init__(self, tempx, tempy):
        self.x = tempx
        self.y = tempy

    def display(self):
        stroke(0)
        point(self.x, self.y)

    def step(self):
        choice = int(random(4))
        if choice == 0:
            self.x = self.x + 1
        elif choice == 1:
            self.x = self.x - 1
        elif choice == 2:
            self.y = self.y + 1
        else:
            self.y = self.x - 1

w = []

def setup():
    global w
    w = Walker(width/2, height/2)
    size(640, 360)
    background(255)

def draw():
    w.step()
    w.display()
