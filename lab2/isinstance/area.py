import classes
from math import pi as PI

def area(shape):
    if isinstance(shape, classes.Circle):
        return (shape.radius ** 2) * PI
    if isinstance(shape, classes.Square):
        return shape.side ** 2
    if isinstance(shape, classes.Rectangle):
        return shape.width * shape.height
    raise TypeError("Unrecognized shape")