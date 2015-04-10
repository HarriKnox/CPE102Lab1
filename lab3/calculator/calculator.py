from __future__ import print_function

import operations
import calc_parser
import scanner
import sys

class Calculator(object):
   def __init__(self, header, prompt, parser):
      self.header = header
      self.prompt = prompt
      self.parser = parser
      self.bindings = {}


   def printHeader(self):
      print(self.header)


   def printPrompt(self):
      print(self.prompt, end='')
      sys.stdout.flush()


   def calculate(self):
      self.printHeader()
      while not self.parser.atEOF():
         self.printPrompt()

         try:
            op = self.parser.parse()
            if op:
               print('{0} => {1}'.format(op, op.evaluate(self.bindings)))
         except calc_parser.InvalidOperationException as exp:
            print(exp)
         except operations.UnboundIdentifierException as exp:
            print(exp)
         except ZeroDivisionError:
            print('divide by zero')


if __name__ == '__main__':
   Calculator('Welcome to the simple calculator.\n'
      'To exit, hit <ctrl-d> in Unix or <ctrl-z> in Windows.',
      '-> ',
      calc_parser.Parser(scanner.Scanner(sys.stdin))
   ).calculate()
