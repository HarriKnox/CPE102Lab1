from __future__ import print_function

class CharReader(object):
   def __init__(self, stream):
      self.stream = stream
      self.putback = False
      self.c = ''
      self.at_eof = False

   def read(self):
      if self.putback:
         self.putback = False
         return self.c
      else:
         c = self.stream.read(1)
         self.at_eof = c == ''
         return c

   def lookahead(self):
      if self.putback:
         return self.c
      else:
         self.c = self.stream.read(1)
         self.putback = True
         return self.c

   def eof(self):
      return self.at_eof

import sys
if __name__ == '__main__':
   cr = CharReader(sys.stdin)
   while not cr.eof():
      c = cr.read()
      print("{0} ==> {1}".format(c, cr.lookahead()))
