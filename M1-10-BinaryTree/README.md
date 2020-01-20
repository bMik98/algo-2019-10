# 10. Performance of Trees

# 10.1 Parameters

| Operation                      | elements   | 
|--------------------------------|-----------:|
| insert (init)                  | 150 000    |
| random search                  | 15 000     |
| random remove                  | 15 000     |

# 10.2 Results

| Tree, init type                | insert, ms | search, ms | remove, ms |
|--------------------------------|-----------:|-----------:|-----------:|
| BSTree, init by sequence       |     21 957 |      2 253 |      2 155 |
| BSTree, init by random         |         30 |          5 |         10 |
| AVLTree, init by sequence      |         52 |          6 |         25 |
| AVLTree, init by random        |        134 |          3 |          9 |

