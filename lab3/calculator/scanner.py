from __future__ import print_function
import constants
import tokens
import char_reader

symbolList = ['(', ')', '+', '-', '*', '/', '=', '\n']

class Scanner(object):
   def __init__(self, fstr):
      self.reader = char_reader.CharReader(fstr)

   def nextToken(self):
      self.clearWhitespace()

      if self.reader.eof():
         return tokens.createToken(constants.TK_EOF)

      if self.reader.lookahead().isalpha():
         return self.buildIdentifier()
      elif self.reader.lookahead().isdigit() or self.reader.lookahead() == '.':
         return self.buildNumber()
      else:
         return self.buildSymbol()


   def buildIdentifier(self):
      chars = []
      while self.reader.lookahead().isalnum():
         chars.append(self.reader.read())
      return self.checkForKeyword(''.join(chars))


   def buildNumber(self):
      chars = []
      while self.reader.lookahead().isdigit():
         chars.append(self.reader.read())

      if self.reader.lookahead() == '.':
         chars.append(self.reader.read())
         while self.reader.lookahead().isdigit():
            chars.append(self.reader.read())

      num = ''.join(chars)

      if num == '.':
         raise InvalidNumberException()

      return tokens.createNumToken(num)


   def buildSymbol(self):
      if self.reader.lookahead() in symbolList:
         return tokens.createToken(constants.lookup(self.reader.read()))
      elif not self.reader.lookahead():
         return tokens.createToken(constants.TK_EOF)
      else:
         raise InvalidCharacterException(self.reader.read())


   def checkForKeyword(self, id):
      token = constants.lookup(id)
      if token != constants.TK_NONE:
         return tokens.createToken(token)
      else:
         return tokens.createIdToken(id)


   def clearWhitespace(self):
      while (not self.reader.eof() and self.reader.lookahead().isspace() and
         self.reader.lookahead() != '\n'):
         self.reader.read()


class InvalidCharacterException(Exception):
   def __init__(self, value):
      self.value = value
   def __str__(self):
      return "Invalid character: '" + self.value + "'"

class InvalidNumberException(Exception):
   def __str__(self):
      return "Invalid number"


import sys
if __name__ == '__main__':
   scanner = Scanner(sys.stdin)
   token = scanner.nextToken()
   while token.getCode() != constants.TK_EOF:
      print('{0} : {1}'.format(token.getCode(), token))
      token = scanner.nextToken()

