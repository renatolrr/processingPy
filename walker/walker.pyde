
def setup():
    w = Walker(width/2, height/2)
    size(640, 360)
    background(255)
    
def draw():
    w.step()
    w.display()


class Walker(object):
    def __init__(self, tempx, tempy):
        self.x = tempx
        self.y = tempy
        
    def display(self):
        stroke(0)
        point(self.x, self.y)
    def step():
        choice = int(random(4))
        if choice == 0:
            x = x + 1
        else if choice == 1:
            x = x - 1
        else if choice == 2:
            y = y + 1
        else:
            y = y - 1
            

    
