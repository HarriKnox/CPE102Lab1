
class Account(object):
   def __init__(self, initial):
      self.balance = initial

   def getBalance(self):
      return self.balance

   def purchaseItem(self, item_cost):
      self.balance -= item_cost

   def returnItem(self, item_cost):
      self.balance += item_cost

