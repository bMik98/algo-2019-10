# 8. MergeSort, TimSort

## 8.1 Creating a large file array of unsigned shorts

| Array size, numbers |  File length, bytes |   Elapsed time, ms  |
|--------------------:|--------------------:|--------------------:|
|           1 000 000 |           2 000 000 |                 146 |
|          10 000 000 |          20 000 000 |                 489 |
|         100 000 000 |         200 000 000 |               4 075 |
|       1 000 000 000 |       2 000 000 000 |              15 780 |

## 8.2 External sorting a file array 

| Name                | Array size, numbers |   Elapsed time, ms  |
|-------------------- |--------------------:|--------------------:|
|  External MergeSort |          50 000 000 |              10 107 |
|    External TimSort |          50 000 000 |               8 446 |
|  External MergeSort |         500 000 000 |             130 574 |
|    External TimSort |         500 000 000 |             118 358 |
|  External MergeSort |       1 000 000 000 |             310 141 |
|    External TimSort |       1 000 000 000 |             287 385 |

## 8.3 Comparison between in-memory methods MergeSort and TimSort (32..1024), ms

| Array size, numbers | Merge  | Tim-32 | Tim-64 | Tim-128| Tim-256| Tim-512|Tim-1024|
|--------------------:|-------:|-------:|-------:|-------:|-------:|-------:|-------:|
|              50 000 |      9 |      7 |      5 |      5 |      6 |      6 |      5 |
|             100 000 |     14 |     11 |      9 |     11 |     13 |     15 |     11 |
|             500 000 |     69 |     71 |     70 |     70 |     54 |     56 |     54 |
|           1 000 000 |    152 |    126 |    106 |     94 |     89 |     90 |    102 |
|           5 000 000 |    601 |    550 |    446 |    471 |    468 |    559 |    503 |
|          10 000 000 |  1 238 |  1 088 |    939 |    951 |    989 |  1 060 |  1 067 |
|          50 000 000 |  7 234 |  5 328 |  5 413 |  5 566 |  5 804 |  5 642 |  6 266 |

