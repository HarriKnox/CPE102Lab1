LEXEME_FIRST      =        0  # should match the first entry in _lexemes
TK_LPAREN         =        0
TK_RPAREN         =        1
TK_ASSIGN         =        2
TK_SET            =        3
TK_PLUS           =        4
TK_MINUS          =        5
TK_TIMES          =        6
TK_DIVIDE         =        7
TK_NEWLINE        =        8
TK_EOF            =        9  # last in _lexemes
LEXEME_LAST       =       TK_EOF
         # the following correspond to sets of tokens
TK_ID             =       10
TK_NUM            =       11
TK_NONE           =       12

_lexemes = {'(':TK_LPAREN, ')':TK_RPAREN, '=':TK_ASSIGN, 'set':TK_SET,
   '+':TK_PLUS, '-':TK_MINUS, '*':TK_TIMES, '/':TK_DIVIDE, '\n':TK_NEWLINE,
   '*EOF*':TK_EOF}

def lookup(str):
   if str in _lexemes:
      return _lexemes[str]
   else:
      return TK_NONE


def getLexeme(code):
   for lexeme, value in _lexemes.items():
      if code == value:
         return lexeme
   return "<invalid>"
