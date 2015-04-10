from math import pi as PI

class Circle:
   def __init__(self, radius):
      self.radius = radius
   
   def area(self):
      return (self.radius ** 2) * PI


class Square:
   def __init__(self, side):
      self.side = side
   
   def area(self):
      return self.side ** 2


class Rectangle:
   def __init__(self, width, height):
      self.width = width
      self.height = height
   
   def area(self):
      return self.width * self.height
