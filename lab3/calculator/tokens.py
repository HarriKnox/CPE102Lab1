import constants

def createToken(code):
   return Token(constants.getLexeme(code), code)


def createIdToken(lexeme):
   return Token(lexeme, constants.TK_ID)


def createNumToken(lexeme):
   return Token(lexeme, constants.TK_NUM)


class Token(object):
   def __init__(self, lexeme, code):
      self.lexeme = lexeme
      self.code = code


   def __str__(self):
      return self.lexeme


   def getLexeme(self):
      return self.lexeme


   def getCode(self):
      return self.code

