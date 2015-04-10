class Expression(object):
   pass


class Assignment(object):
   def __init__(self, id, src):
      self.id = id
      self.src = src


   def __str__(self):
      return 'set {0} = {1}'.format(self.id, self.src)


   def evaluate(self, bindings):
      value = self.src.evaluate(bindings)
      bindings[self.id] = value
      return value


class NegationExpression(Expression):
   def __init__(self, opnd):
      self.opnd = opnd


   def __str__(self):
      return '- {0}'.format(self.opnd)


   def evaluate(self, bindings):
      return -self.opnd.evaluate(bindings)


class IdentifierExpression(Expression):
   def __init__(self, id):
      self.id = id


   def __str__(self):
      return self.id


   def evaluate(self, bindings):
      if self.id in bindings:
         return bindings[self.id]
      else:
         raise UnboundIdentifierException(self.id)


class DoubleConstantExpression(Expression):
   def __init__(self, num):
      self.num = num


   def __str__(self):
      return str(self.num)


   def evaluate(self, bindings):
      return self.num


class AddExpression(Expression):
   def __init__(self, lft, rht):
      self.lft = lft
      self.rht = rht


   def __str__(self):
      return '({0} + {1})'.format(self.lft, self.rht)


   def evaluate(self, bindings):
      return self.lft.evaluate(bindings) + self.rht.evaluate(bindings)


class MinusExpression(Expression):
   def __init__(self, lft, rht):
      self.lft = lft
      self.rht = rht


   def __str__(self):
      return '({0} - {1})'.format(self.lft, self.rht)


   def evaluate(self, bindings):
      return self.lft.evaluate(bindings) - self.rht.evaluate(bindings)


class TimesExpression(Expression):
   def __init__(self, lft, rht):
      self.lft = lft
      self.rht = rht


   def __str__(self):
      return '({0} * {1})'.format(self.lft, self.rht)


   def evaluate(self, bindings):
      return self.lft.evaluate(bindings) * self.rht.evaluate(bindings)


class DivideExpression(Expression):
   def __init__(self, lft, rht):
      self.lft = lft
      self.rht = rht


   def __str__(self):
      return '({0} / {1})'.format(self.lft, self.rht)


   def evaluate(self, bindings):
      return self.lft.evaluate(bindings) / self.rht.evaluate(bindings)


class UnboundIdentifierException(Exception):
   def __init__(self, value):
      self.value = value
   def __str__(self):
      return 'unbound identifier: {0}'.format(self.value)

