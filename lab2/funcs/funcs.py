def map(func, args):
    res = []
    for arg in args:
        res.append(func(arg))
    return res

def add(x):
    def g(n):
        return n + x
    return g

def create_counter(x):
    localdict = {'init' : x}
    def g():
        q = localdict['init']
        localdict['init'] += 1
        return q
    return g