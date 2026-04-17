# compiler_design
# Compiler Design Programs in Java
This repository contains Java implementations of key compiler design concepts.  
---
## 📂 Topics Implemented

1. Computation of LR(0)
2. Elimination of Ambiguity
3. Storage Allocation Strategies (Heap / Stack / Static)
4. Directed Acyclic Graph (DAG) Construction
5. Intermediate Code Generation – Quadruple, Triple, Indirect Triple
6. Leading and Trailing Sets
7. Left Recursion Removal and Left Factoring
8. NFA to DFA Conversion
9. Postfix and Prefix Intermediate Code Generation
10. Predictive Parsing (LL(1) Parser)
11. Regex to NFA Conversion
12. Code Generation
13. Code Optimization
14. Single Responsibility Principle (SRP) Example
15. Lexical Analyzer
16. First and Follow Sets
17. Parsing Table Construction
18. Syntax Analysis
19. Semantic Analysis
20. Intermediate Code (General)

---
## 🚀 How to Run

All programs were compiled and executed in **IntelliJ IDEA**.

# 📑 Inputs and Outputs

## Lexical Analyzer
Input:
if x = 5 + 3

Output:
Keyword: if
Identifier: x
Operator: =
Number: 5
Operator: +
Number: 3

---

## First and Follow Sets
Input:
S -> aB | b
B -> c | dS

Output:
FIRST(S) = {a, b}
FOLLOW(S) = {$}
FIRST(B) = {c, d}
FOLLOW(B) = {a, b}

---

## Left Recursion Removal
Input:
A -> A b | c

Output:
A -> c A'
A' -> b A' | ε

---

## NFA to DFA Conversion
Input:
2
a
1 -1
1 -1
0
1

Output:
DFA States: {q0, q1}
Transitions:
q0 --a--> q1
q1 --a--> q1
Final State: q1

---

## Parsing Table Construction
Input:
S -> aB | b
B -> c | dS

Output:
M[S, a] = S -> aB
M[S, b] = S -> b
M[B, c] = B -> c
M[B, d] = B -> dS

---

## Regex to NFA Conversion
Input:
a|b

Output:
States: {q0, q1, q2, q3}
q0 --ε--> q1
q0 --ε--> q2
q1 --a--> q3
q2 --b--> q3
Final State: q3

---

## Intermediate Code Generation – Quadruple, Triple, Indirect Triple
Input:
(a+b)*c-d

Output:
Quadruples:
(+, a, b, t1)
(*, t1, c, t2)
(-, t2, d, t3)

Triples:
0: (+, a, b)
1: (*, (0), c)
2: (-, (1), d)

Indirect Triples:
Pointer 0 -> (+, a, b)
Pointer 1 -> (*, (0), c)
Pointer 2 -> (-, (1), d)

---

## Postfix and Prefix Intermediate Code
Input:
(a+b)*c-d

Output:
Postfix: ab+c*d-
Prefix: - * +abc d

---

## Leading and Trailing Sets
Input:
Grammar with E -> T A

Output:
LEADING(E): {a, b, (}
TRAILING(E): {a, b, )}

---

## Predictive Parsing (LL(1) Parser)
Input:
id + id * id $

Output:
Parsing successful

---

## DAG Construction
Input:
(a+b) - (a+b)

Output:
Node1: +
Node2: a
Node3: b
Node4: -
Reuse Node1 for both occurrences of (a+b)

---

## Code Generation
Input:
x = a + b * c

Output:
LOAD b
MUL c
ADD a
STORE x

---

## Code Optimization
Input:
x = a * 2 + a * 2

Output:
Optimized: x = 2 * (a * 2)

---

## Single Responsibility Principle (SRP)
Input:
Student data

Output:
Student: Harshan, Grade: B

---

## Syntax Analysis
Input:
if (x+5) { y=3; }

Output:
Syntax tree constructed successfully

---

## Semantic Analysis
Input:
x = true + 5

Output:
Error: Type mismatch (boolean + int)

---

## LR(0) Computation
Input:
S -> E
E -> E+T | T

Output:
I0: S -> .E
I1: E -> .E+T | .T
...

---

## Elimination of Ambiguity
Input:
Grammar with dangling else

Output:
Ambiguity removed by associating ELSE with nearest IF

