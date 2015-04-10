import scanner
import tokens
import constants
import operations

class Parser(object):
   def __init__(self, scanner):
      self.scanner = scanner
      self.at_eof = False


   def parse(self):
      (op, nexttok) = self.parseOperation(self.nextToken())
      return op


   def parseOperation(self, token):
      op = None
      code = token.getCode()
      if code == constants.TK_SET:
         (op, newtok) = self.parseSet(token)
         nexttok = self.expectNewline(newtok)
      elif code == constants.TK_NEWLINE:
         nexttok = token
      elif code == constants.TK_EOF:
         nexttok = token
         self.at_eof = True
      else:
         (op, newtok) = self.parseExpression(token)
         nexttok = self.expectNewline(newtok)

      return (op, nexttok)


   def parseSet(self, token):
      tk1 = self.match(token, constants.TK_SET)
      (id, tk2) = self.matchIdentifier(tk1)
      tk3 = self.match(tk2, constants.TK_ASSIGN)
      (e, tk4) = self.parseExpression(tk3)
      return (operations.Assignment(id, e), tk4)


   def parseExpression(self, token):
      (e, nexttok) = self.parseTerm(token)
      while self.isAddOp(nexttok.getCode()):
         (tkcode, newtok) = self.parseBinaryOp(nexttok)
         (rht, nexttok) = self.parseTerm(newtok)
         e = self.newBinaryOp(tkcode, e, rht)

      return (e, nexttok)


   def parseTerm(self, token):
      (e, nexttok) = self.parseUnary(token)
      while self.isMultOp(nexttok.getCode()):
         (tkcode, newtok) = self.parseBinaryOp(nexttok)
         (rht, nexttok) = self.parseUnary(newtok)
         e = self.newBinaryOp(tkcode, e, rht)

      return (e, nexttok)


   def parseUnary(self, token):
      if token.getCode() == constants.TK_MINUS:
         newtok = self.match(token, constants.TK_MINUS)
         (e, nexttok) = self.parseUnary(newtok)
         return (operations.NegationExpression(e), nexttok)
      else:
         return self.parseFactor(token)


   def parseFactor(self, token):
      code = token.getCode()
      if code == constants.TK_LPAREN:
         tk1 = self.match(token, constants.TK_LPAREN)
         (e, tk2) = self.parseExpression(tk1)
         tk3 = self.match(tk2, constants.TK_RPAREN)
         return (e, tk3)
      elif code == constants.TK_ID:
         (id, newtok) = self.matchIdentifier(token)
         return (operations.IdentifierExpression(id), newtok)
      elif code == constants.TK_NUM:
         return (operations.DoubleConstantExpression(float(token.getLexeme())),
            self.nextToken())
      else:
         self.expected('identifier or value', token)


   def isAddOp(self, code):
      return code == constants.TK_PLUS or code == constants.TK_MINUS


   def isMultOp(self, code):
      return code == constants.TK_TIMES or code == constants.TK_DIVIDE


   def parseBinaryOp(self, token):
      code = token.getCode()
      newtok = self.nextToken()
      return (code, newtok)


   def newBinaryOp(self, code, lft, rht):
      if code == constants.TK_PLUS:
         return operations.AddExpression(lft, rht)
      elif code == constants.TK_MINUS:
         return operations.MinusExpression(lft, rht)
      elif code == constants.TK_TIMES:
         return operations.TimesExpression(lft, rht)
      elif code == constants.TK_DIVIDE:
         return operations.DivideExpression(lft, rht)

      return None


   def atEOF(self):
      return self.at_eof


   def matchIdentifier(self, token):
      if token.getCode() != constants.TK_ID:
         self.expected("identifier", token)
      else:
         id = token.getLexeme()
         newtok = self.nextToken()
         return (id, newtok)


   def match(self, token, tkcode):
      if token.getCode() != tkcode:
         self.expected("'{0}'".format(constants.getLexeme(tkcode)), token)
      return self.nextToken()


   def expected(self, msg, token):
      self.clearLine(token)
      raise InvalidOperationException(
         "expected {0}, got '{1}'".format(msg, token.getLexeme()))


   def expectNewline(self, token):
      if token.getCode() != constants.TK_NEWLINE:
         self.clearLine(token)
         raise InvalidOperationException("unexpected symbols after expression")


   def clearLine(self, token):
      code = token.getCode()
      while code != constants.TK_NEWLINE and code != constants.TK_EOF:
         try:
            code = self.nextToken().getCode()
         except:
            pass


   def nextToken(self):
      try:
         return self.scanner.nextToken()
      except scanner.InvalidNumberException:
         self.clearLine(tokens.createToken(constants.TK_NONE))
         raise InvalidOperationException("Invalid number.")
      except scanner.InvalidCharacterException:
         self.clearLine(tokens.createToken(constants.TK_NONE))
         raise InvalidOperationException("Invalid input.")


class InvalidOperationException(Exception):
   def __init__(self, value):
      self.value = value
   def __str__(self):
      return self.value


import sys
if __name__ == '__main__':
   parser = Parser(scanner.Scanner(sys.stdin))
   while not parser.atEOF():
      parser.parse()
